/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 */
package tuc.isse.controler;

import tuc.isse.model.Bord;
import tuc.isse.model.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Spielerklasse mit Konsoleneingabe.
 * Fragt den Benutzer nach dem nächsten Spielzug.
 *
 * @author Landry Gotier Kuete Yemgie und Carine Ngon Diboma
 * @version 1.0
 */
public class ConsolePlayer extends Player {

    private final BufferedReader consoleReader;

    /**
     * Konstruktor initialisiert Spieler mit Farbe und Spielfeld.
     *
     * @param color Spielerfarbe
     * @param bord Spielfeld
     */
    public ConsolePlayer(Color color, Bord bord) {
        super(color, bord);
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Führt einen Spielzug durch, indem der Benutzer über Konsole eine Spalte eingibt.
     */
    @Override
    public void doTurn() {
        while (true) {
            System.out.print("Spieler " + color + ", gib eine Spalte (0-6) ein: ");
            try {
                String input = consoleReader.readLine();
                int column = Integer.parseInt(input.trim());

                if (column < 0 || column >= bord.cols) {
                    System.out.println("Ungültige Spalte. Bitte Zahl zwischen 0 und 6 eingeben.");
                    continue;
                }

                if (!bord.canDrop(column)) {
                    System.out.println("Spalte ist voll. Wähle eine andere.");
                    continue;
                }

                doDrop(column); // gültiger Zug
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte eine ganze Zahl eingeben.");
            } catch (IOException e) {
                System.out.println("Fehler beim Lesen der Eingabe.");
            }
        }
    }
}
