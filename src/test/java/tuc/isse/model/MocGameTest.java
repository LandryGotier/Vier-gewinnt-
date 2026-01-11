package tuc.isse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse zur Überprüfung des Observer-Musters beim Bord.
 */
public class MocGameTest implements BordObserver {

    private boolean updateCalled = false;
    private ObservableBord receivedBord = null;

    @Override
    public void update(ObservableBord bord) {
        updateCalled = true;
        receivedBord = bord;
    }

    @Test
    public void testObserverWirdBenachrichtigt() throws Exception {
        // Arrange
        ObservableBord bord = new ObservableBord();
        bord.addObserver(this);
        Token token = new Token(Color.RED);

        // Act
        bord.dropToken(token, 0);

        // Assert
        assertTrue(updateCalled, "Observer wurde nicht benachrichtigt.");
        assertNotNull(receivedBord, "Observer hat kein Bord erhalten.");
        assertEquals(Color.RED, receivedBord.getTokenColor(5, 0), "Token wurde nicht korrekt gesetzt.");
    }

    @Test
    public void testObserverEntfernen() throws Exception {
        // Arrange
        ObservableBord bord = new ObservableBord();
        bord.addObserver(this);
        bord.removeObserver(this);
        updateCalled = false;

        // Act
        bord.dropToken(new Token(Color.BLUE), 1);

        // Assert
        assertFalse(updateCalled, "Observer wurde trotz Entfernen benachrichtigt.");
    }
}