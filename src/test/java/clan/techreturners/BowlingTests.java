package clan.techreturners;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingTests {
    @ParameterizedTest(name = "{index}) Two knocks of {0} and {1} will produce a score of {2}")
    @CsvSource(textBlock = """
            3,4, 7
            2,2, 4
            6,3, 9
            """)
    public void checkScoreForOpenFramesGameWithOneFrame(int firstThrow, int secondThrow, int expectedScore) {
        // Arrange
        Bowling game = new Bowling();

        // Act
        game.knock(firstThrow);
        game.knock(secondThrow);
        int score = game.getScore();

        // Assert
        assertEquals(expectedScore, score);
    }
}