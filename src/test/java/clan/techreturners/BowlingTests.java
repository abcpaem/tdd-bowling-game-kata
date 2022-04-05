package clan.techreturners;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingTests {
    @Test
    public void checkScoreForOpenFramesGameWithOneFrame() {
        // Arrange
        Bowling game = new Bowling();

        // Act
        game.knock(3);
        game.knock(4);
        int score = game.getScore();

        // Assert
        assertEquals(7, score);
    }

    @Test
    public void checkScoreForOpenFramesGame2WithOneFrame() {
        // Arrange
        Bowling game = new Bowling();

        // Act
        game.knock(2);
        game.knock(2);
        int score = game.getScore();

        // Assert
        assertEquals(4, score);
    }
}