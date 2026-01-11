/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 * */
package tuc.isse.model;


import java.util.HashSet;
import java.util.Set;

/**
 * Erweiterung der Klasse Bord, die ein Observer-Muster implementiert.
 * Beobachter können sich registrieren und werden benachrichtigt,
 * wenn ein Token gesetzt wird.
 */
public class ObservableBord extends Bord {

    private final Set<BordObserver> observers = new HashSet<>();

    /**
     * Fügt einen Observer hinzu, der bei Änderungen benachrichtigt werden soll.
     */
    public void addObserver(BordObserver observer) {
        observers.add(observer);
    }

    /**
     * Entfernt einen Observer.
     */
    public void removeObserver(BordObserver observer) {
        observers.remove(observer);
    }

    /**
     * Benachrichtigt alle registrierten Observer über eine Änderung.
     */
    private void notifyObservers() {
        for (BordObserver observer : observers) {
            observer.update(this);
        }
    }

    /**
     * Überschreibt dropToken, um nach jedem Spielzug die Observer zu benachrichtigen.
     */
    @Override
    public void dropToken(Token token, int columnIndex)
            throws ColumnFullException, IllegalMoveException {
        super.dropToken(token, columnIndex);
        notifyObservers();
    }
}
