///////////////////////////////////////////
/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 */

package tuc.isse.model;

/**
 * Abstrakte Oberklasse aller Spielobjekte im Vier-Gewinnt-Spiel.
 * Erzwingt, dass alle abgeleiteten Klassen eine eigene String-Darstellung implementieren.
 *
 * @author Landry Gotier Kuete Yemgie und Carine Ngon Diboma
 * @version 1.0
 */
public abstract class GameObjekt {

    /**
     * Wandelt das Spielobjekt und seine Bestandteile in einen String um.
     *
     * @return String-Repräsentation des Objekts
     */
    public abstract String toString();
}
