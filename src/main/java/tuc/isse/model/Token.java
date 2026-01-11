///////////////////////////////////////////
/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 */

package tuc.isse.model;

/**
 * Repräsentiert einen Spielstein (Token), der eine Farbe (RED oder BLUE) besitzt.
 * RED wird als "X", BLUE als "O" dargestellt.
 *
 * @author Landry Gotier Kuete Yemgie und Carine Ngon Diboma
 * @version 1.0
 */
public class Token extends GameObjekt {
    private final Color color;

    /**
     * Erstellt einen neuen Spielstein mit der angegebenen Farbe.
     *
     * @param color Die Farbe des Spielsteins (RED oder BLUE)
     */
    public Token(Color color) {
        this.color = color;
    }

    /**
     * Gibt die Farbe des Tokens zurück.
     *
     * @return RED oder BLUE
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gibt eine Textdarstellung des Tokens zurück.
     * "X" für RED, "O" für BLUE.
     *
     * @return "X" oder "O"
     */
    @Override
    public String toString() {
        if (color == Color.RED) {
            return "X";
        } else {
            return "O";
        }
    }
}
