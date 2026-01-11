/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 */

package tuc.isse.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import tuc.isse.model.Bord;

/**
 * JUnit‑Tests für das Spiel „Vier gewinnt“ mit aktueller Spiellogik.
 */
public class BordTest {

    /** Test 1: Leeres Spielfeld */
    @Test
    public void testEmptyBoardToString() {
        Bord board = new Bord();

        String expected =
                "( )( )( )( )( )( )( )\n" +
                        "( )( )( )( )( )( )( )\n" +
                        "( )( )( )( )( )( )( )\n" +
                        "( )( )( )( )( )( )( )\n" +
                        "( )( )( )( )( )( )( )\n" +
                        "( )( )( )( )( )( )( )\n" +
                        "(0)(1)(2)(3)(4)(5)(6)\n";

        assertEquals(expected, board.toString());
        assertEquals(Winner.NONE, board.testVictory());
    }

    /** Test 2: Teilbefülltes Spielfeld ohne Sieger */
    @Test
    public void testPartialBoardToString() throws ColumnFullException, IllegalMoveException {
        Bord board = new Bord();

        board.dropToken(board.getToken(Color.BLUE), 6);
        board.dropToken(board.getToken(Color.RED), 6);
        board.dropToken(board.getToken(Color.RED), 4);
        board.dropToken(board.getToken(Color.RED), 2);
        board.dropToken(board.getToken(Color.BLUE), 5);
        board.dropToken(board.getToken(Color.BLUE), 2);
        board.dropToken(board.getToken(Color.BLUE), 4);
        board.dropToken(board.getToken(Color.RED), 5);
        board.dropToken(board.getToken(Color.RED), 4);
        board.dropToken(board.getToken(Color.BLUE), 6);
        board.dropToken(board.getToken(Color.BLUE), 4);

        String expected =
                "( )( )( )( )( )( )( )\n" +
                        "( )( )( )( )( )( )( )\n" +
                        "( )( )( )( )(O)( )( )\n" +
                        "( )( )( )( )(X)( )(O)\n" +
                        "( )( )(O)( )(O)(X)(X)\n" +
                        "( )( )(X)( )(X)(O)(O)\n" +
                        "(0)(1)(2)(3)(4)(5)(6)\n";

        assertEquals(expected, board.toString());
        assertEquals(Winner.NONE, board.testVictory());
    }

    /** Test 3: Komplexes Spielfeld – BLUE gewinnt, danach weiterer Zug -> IllegalMoveException */
    @Test
    public void testComplexBoardToString() throws ColumnFullException, IllegalMoveException {
        Bord board = new Bord();

        board.dropToken(board.getToken(Color.RED), 0);   // X unten
        board.dropToken(board.getToken(Color.BLUE), 0);  // O darüber

        //   Spalte 1 -> O, X, O
        board.dropToken(board.getToken(Color.BLUE), 1);  // O unten
        board.dropToken(board.getToken(Color.RED), 1);   // X
        board.dropToken(board.getToken(Color.BLUE), 1);  // O

        //   Spalte 2 -> X, O, X   (der letzte O wird erst am Ende gesetzt!)
        board.dropToken(board.getToken(Color.RED), 2);   // X unten
        board.dropToken(board.getToken(Color.BLUE), 2);  // O
        board.dropToken(board.getToken(Color.RED), 2);   // X

        //   Spalte 3 -> X
        board.dropToken(board.getToken(Color.RED), 3);   // X unten

        //   Spalte 4 -> X, X, X, O
        board.dropToken(board.getToken(Color.RED), 4);   // X unten
        board.dropToken(board.getToken(Color.RED), 4);   // X
        board.dropToken(board.getToken(Color.RED), 4);   // X
        board.dropToken(board.getToken(Color.BLUE), 4);  // O oben

        //   Spalte 5 -> O, O, O
        board.dropToken(board.getToken(Color.BLUE), 5);  // O unten
        board.dropToken(board.getToken(Color.BLUE), 5);  // O
        board.dropToken(board.getToken(Color.BLUE), 5);  // O

        //   Spalte 6 -> O, O
        board.dropToken(board.getToken(Color.BLUE), 6);  // O unten
        board.dropToken(board.getToken(Color.BLUE), 6);  // O

        /* Spielstand vor letztem Stein überprüfen */
        assertEquals(Winner.NONE, board.testVictory(), "Vor dem letzten Zug darf es keinen Sieger geben.");

        /* ---------- Letzter Zug: BLUE in Spalte 2 ---------- */
        board.dropToken(board.getToken(Color.BLUE), 2);

// letzter gültiger Zug – BLUE gewinnt

        /* Erwartetes Brett NACH dem Sieg */
        String expected =
                "( )( )( )( )( )( )( )\n" +
                        "( )( )( )( )( )( )( )\n" +
                        "( )( )(O)( )(O)( )( )\n" +
                        "( )(O)(X)( )(X)(O)( )\n" +
                        "(O)(X)(O)( )(X)(O)(O)\n" +
                        "(X)(O)(X)(X)(X)(O)(O)\n" +
                        "(0)(1)(2)(3)(4)(5)(6)\n";

        assertEquals(expected, board.toString());
        assertEquals(Winner.BLUE, board.testVictory());

        /* ----- Versuch eines weiteren Zugs: muss IllegalMoveException werfen ----- */
        assertThrows(
                IllegalMoveException.class,
                () -> board.dropToken(board.getToken(Color.RED), 0),
                "Nach Spielende darf kein Zug mehr möglich sein."
        );
    }
}
