///////////////////////////////////////////
/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 *
 */
package tuc.isse.model;

/**
 * Die Klasse  wird verwendet, um anzuzeigen,
 * dass ein Spielzug ungültig ist, weil die gewählte Spalte bereits voll ist.
 */
public class ColumnFullException extends Exception {
    /**
     * Konstruktor
     * @param msg  Nachricht
     */
    public ColumnFullException(String msg){
        super(msg);
    }
}
