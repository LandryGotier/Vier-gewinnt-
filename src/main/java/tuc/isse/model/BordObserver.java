/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 * */


package tuc.isse.model;


/**
 * Ein Beobachter des Spielbretts (Bord), der benachrichtigt wird,
 * wenn sich das Spielfeld ändert (z. B. nach einem gesetzten Token).
 */
public interface BordObserver {
    /**
     * Diese Methode wird aufgerufen, wenn das beobachtete Spielfeld aktualisiert wurde.
     *
     * @param bord das beobachtete Spielbrett
     */
    void update(ObservableBord bord);
}
