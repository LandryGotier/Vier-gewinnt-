package tuc.isse.view;

import tuc.isse.model.BordObserver;
import tuc.isse.model.Color;
import tuc.isse.model.ObservableBord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Grafische Oberfläche für das Spiel "Vier gewinnt".
 * Beobachtet das Spielbrett und aktualisiert sich bei jeder Änderung.
 */
public class VierGewinntFrame extends JFrame implements BordObserver {

    private final ObservableBord bord;
    private final JButton[] columnButtons = new JButton[7];
    private final JLabel[][] cellLabels = new JLabel[6][7];

    /**
     * Konstruktor. Initialisiert das Fenster und erstellt GUI-Komponenten.
     * @param bord das beobachtete Spielbrett
     */
    public VierGewinntFrame(ObservableBord bord) {
        this.bord = bord;
        this.bord.addObserver(this);

        setTitle("Vier gewinnt – GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Button-Leiste oben
        JPanel buttonPanel = new JPanel(new GridLayout(1, 7));
        for (int col = 0; col < 7; col++) {
            JButton btn = new JButton("↓");
            btn.setActionCommand(String.valueOf(col));
            columnButtons[col] = btn;
            buttonPanel.add(btn);
        }
        add(buttonPanel, BorderLayout.NORTH);

        // Spielbrett (Zellen)
        JPanel gridPanel = new JPanel(new GridLayout(6, 7));
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(java.awt.Color.LIGHT_GRAY);
                label.setBorder(BorderFactory.createLineBorder(java.awt.Color.DARK_GRAY));
                cellLabels[row][col] = label;
                gridPanel.add(label);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Fügt allen Spaltenbuttons einen gemeinsamen ActionListener hinzu.
     * @param listener ActionListener
     */
    public void addButtonListener(ActionListener listener) {
        for (JButton button : columnButtons) {
            button.addActionListener(listener);
        }
    }

    /**
     * Entfernt den angegebenen ActionListener von allen Buttons.
     * @param listener ActionListener
     */
    public void removeButtonListener(ActionListener listener) {
        for (JButton button : columnButtons) {
            button.removeActionListener(listener);
        }
    }

    /**
     * Aktualisiert die Darstellung des Spielbretts auf Grundlage des aktuellen Zustands.
     * Diese Methode wird durch das Observer-Muster automatisch aufgerufen.
     * @param bord das beobachtete Spielbrett
     */
    @Override
    public void update(ObservableBord bord) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                Color gameColor = bord.getTokenColor(row, col);
                if (gameColor == Color.RED) {
                    cellLabels[row][col].setBackground(java.awt.Color.RED);
                } else if (gameColor == Color.BLUE) {
                    cellLabels[row][col].setBackground(java.awt.Color.BLUE);
                } else {
                    cellLabels[row][col].setBackground(java.awt.Color.LIGHT_GRAY);
                }
            }
        }
    }
}
