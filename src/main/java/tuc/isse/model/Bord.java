/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 * */

package tuc.isse.model;

import java.util.*;

/**
 * Die Klasse Bord stellt das Spielfeld des Spiels "Vier gewinnt" dar. Sie verwaltet:
 * - ein zweidimensionales Array von Zellen
 * - das Tokenlager (je 21 Spielsteine pro Farbe)
 * - die Spielregeln inkl. Sieg- und Gleichstandserkennung
 * Funktionen:
 * - Token setzen (dropToken)
 * - Lagerverwaltung (getToken, hasToken)
 * - Überprüfung von Siegen in Zickzack-Mustern
 * - Gleichstandserkennung
 * - Ausgabe des Spielbretts
 *
 * @author Landry Gotier Kuete Yemgie und Carine Ngon Diboma
 * @version 3.0
 */
public class Bord extends GameObjekt {
    private final int rows = 6;
    public final int cols = 7;
    private final Cell[][] board;
    private final Map<Color, Queue<Token>> tokenLager;
    private boolean spielBeendet;

    /**
     * Konstruktor initialisiert das Spielfeld mit leeren Zellen
     * und legt jeweils 21 Tokens je Spielerfarbe ins Lager.
     */
    public Bord() {
        board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell();
            }
        }

        tokenLager = new HashMap<>();
        for (Color color : Color.values()) {
            if (color != null) {
                Queue<Token> tokens = new LinkedList<>();
                for (int i = 0; i < 21; i++) {
                    tokens.add(new Token(color));
                }
                tokenLager.put(color, tokens);
            }
        }

        spielBeendet = false;
    }

    /**
     * Gibt einen Token der übergebenen Farbe aus dem Lager zurück und entfernt ihn.
     * Gibt null zurück, wenn keiner mehr vorhanden ist.
     *
     * @param color RED oder BLUE
     * @return Token der Farbe oder null
     */
    public Token getToken(Color color) {
        Queue<Token> queue = tokenLager.get(color);
        return (queue != null && !queue.isEmpty()) ? queue.poll() : null;
    }

    /**
     * Gibt an, ob noch Token der übergebenen Farbe im Lager verfügbar sind.
     *
     * @param color RED oder BLUE
     * @return true wenn Token vorhanden, sonst false
     */
    public boolean hasToken(Color color) {
        Queue<Token> queue = tokenLager.get(color);
        return queue != null && !queue.isEmpty();
    }

    /**
     * Lässt einen Token in die angegebene Spalte fallen.
     *
     * @param token Token, der gesetzt werden soll
     * @param columnIndex Spaltenindex (0-6)
     * @throws ColumnFullException wenn die Spalte voll ist
     * @throws IllegalMoveException wenn das Spiel bereits beendet wurde
     */
    public void dropToken(Token token, int columnIndex)
            throws ColumnFullException, IllegalMoveException {
        if (spielBeendet) {
            throw new IllegalMoveException("Das Spiel ist bereits beendet.");
        }
        if (!canDrop(columnIndex)) {
            throw new ColumnFullException("Spalte: " + columnIndex + " ist voll.");
        }

        for (int row = rows - 1; row >= 0; row--) {
            if (board[row][columnIndex].isEmpty()) {
                board[row][columnIndex].setToken(token);
                break;
            }
        }

        Winner winner = testVictory();
        if (winner != Winner.NONE) {
            spielBeendet = true;
        }
    }

    /**
     * Prüft, ob in der Spalte noch ein Token gesetzt werden kann.
     *
     * @param columnIndex Spaltenindex
     * @return true wenn Platz vorhanden ist, sonst false
     */
    public boolean canDrop(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= cols) return false;
        return board[0][columnIndex].isEmpty();
    }

    /**
     * Gibt die Farbe des Tokens an einer bestimmten Position zurück.
     *
     * @param row Zeile
     * @param col Spalte
     * @return Farbe des Tokens oder null
     */
    public Color getTokenColor(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            Token t = board[row][col].getToken();
            return t != null ? t.getColor() : null;
        }
        return null;
    }


    /**
     * Prüft Siegbedingungen mit Zickzack-Mustern über Zeilen.
     *
     * @param color Spielerfarbe
     * @return true wenn Gewinn vorliegt
     */
    private boolean isRowVictory(Color color) {
        for (int row = 0; row <= rows - 2; row++) {
            for (int col = 0; col <= cols - 4; col++) {
                Color a = getTokenColor(row + 1, col);
                Color b = getTokenColor(row, col + 1);
                Color c = getTokenColor(row + 1, col + 2);
                Color d = getTokenColor(row, col + 3);

                if (color == a && color == b && color == c && color == d) return true;

                Color e = getTokenColor(row, col);
                Color f = getTokenColor(row + 1, col + 1);
                Color g = getTokenColor(row, col + 2);
                Color h = getTokenColor(row + 1, col + 3);

                if (color == e && color == f && color == g && color == h) return true;
            }
        }
        return false;
    }

    /**
     * Prüft Siegbedingungen mit Zickzack-Mustern über Spalten.
     *
     * @param color Spielerfarbe
     * @return true wenn Gewinn vorliegt
     */
    private boolean isColumnVictory(Color color) {
        for (int row = 0; row <= rows - 4; row++) {
            for (int col = 0; col <= cols - 2; col++) {
                Color a = getTokenColor(row, col);
                Color b = getTokenColor(row + 1, col + 1);
                Color c = getTokenColor(row + 2, col);
                Color d = getTokenColor(row + 3, col + 1);

                if (color == a && color == b && color == c && color == d) return true;

                Color e = getTokenColor(row, col + 1);
                Color f = getTokenColor(row + 1, col);
                Color g = getTokenColor(row + 2, col + 1);
                Color h = getTokenColor(row + 3, col);

                if (color == e && color == f && color == g && color == h) return true;
            }
        }
        return false;
    }

    /**
     * Prüft, ob Gleichstand herrscht (keine Tokens mehr verfügbar und kein Sieger).
     *
     * @return true wenn Unentschieden
     */
    private boolean isTie() {
        return !hasToken(Color.RED) && !hasToken(Color.BLUE)
                && testVictory() == Winner.NONE;
    }

    /**
     * Gibt den aktuellen Spielstand zurück.
     *
     * @return Gewinnerfarbe, TIE oder NONE
     */
    public Winner testVictory() {
        if (isRowVictory(Color.RED) || isColumnVictory(Color.RED)) {
            return Winner.RED;
        } else if (isRowVictory(Color.BLUE) || isColumnVictory(Color.BLUE)) {
            return Winner.BLUE;
        } else if (isTie()) {
            return Winner.TIE;
        }
        return Winner.NONE;
    }

    /**
     * Gibt das Spielfeld als Zeichenkette zurück.
     * Jede Zelle wird über ihre toString-Methode dargestellt.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                sb.append(board[row][col].toString());
            }
            sb.append("\n");
        }
        sb.append("(0)(1)(2)(3)(4)(5)(6)\n");
        return sb.toString();
    }
}
