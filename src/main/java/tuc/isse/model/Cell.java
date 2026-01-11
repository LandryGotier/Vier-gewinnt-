///////////////////////////////////////////
/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 */

package tuc.isse.model;

/**
 * Eine Zelle auf dem Spielbrett, die entweder leer ist oder einen Token enthält.
 *
 * @author Landry Gotier Kuete Yemgie und Carine Ngon Diboma
 * @version 1.0
 */
public class Cell extends GameObjekt {
    private Token token;



    /**
     * Prüft, ob die Zelle leer ist, also keinen Token enthält.
     *
     * @return true, wenn die Zelle leer ist, sonst false
     */
    public boolean isEmpty() {
        return token == null;
    }

    /**
     * Setzt einen Token in die Zelle, wenn die Zelle leer ist und der Token nicht null ist.
     *
     * @param token Der Spielstein, der in die Zelle gesetzt wird
     */
    public void setToken(Token token) {
        if (token != null && isEmpty()) {
            this.token = token;
        }
    }

    /**
     * Gibt den Spielstein (Token) in der Zelle zurück.
     *
     * @return der Token oder null, falls die Zelle leer ist
     */
    public Token getToken() {
        return token;
    }

    /**
     * Gibt eine Textdarstellung der Zelle zurück.
     * Leere Zellen werden als "( )" dargestellt,
     * belegte Zellen mit dem String des Tokens in Klammern.
     *
     * @return String-Repräsentation der Zelle
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "( )";
        } else {
            return "(" + token.toString() + ")";
        }
    }
}
