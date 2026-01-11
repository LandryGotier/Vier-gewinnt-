package tuc.isse.controler;

import tuc.isse.model.*;
import tuc.isse.view.VierGewinntFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI-Spieler, der auf Mausklicks reagiert, um einen Spielzug auszuführen.
 * Registriert sich bei der Benutzeroberfläche und wertet Klicks aus.
 */
public class FramePlayer extends Player implements ActionListener {

    private final VierGewinntFrame frame;

    /**
     * Konstruktor für den FramePlayer.
     * @param color Spielerfarbe
     * @param bord Referenz auf das Spielbrett
     * @param frame Referenz auf das GUI-Fenster
     */
    public FramePlayer(Color color, Bord bord, VierGewinntFrame frame) {
        super(color, bord);
        this.frame = frame;
    }

    /**
     * Wird vom Spiel aufgerufen, wenn dieser Spieler an der Reihe ist.
     * Der Player registriert sich bei der GUI als ActionListener.
     */
    @Override
    public void doTurn() {
        frame.addButtonListener(this);
    }

    /**
     * Wird aufgerufen, wenn der Spieler auf einen Spaltenbutton klickt.
     * Liest die Spalte aus dem Button, setzt den Token und deregistriert sich wieder.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int col = Integer.parseInt(e.getActionCommand());
        try {
            Token token = getBord().getToken(getColor());
            if (token != null) {
                getBord().dropToken(token, col);
            }
        } catch (Exception ex) {
            // Optional: Fehler behandeln oder anzeigen (z. B. Spalte voll)
            ex.printStackTrace();
        } finally {
            frame.removeButtonListener(this);
        }
    }



}
