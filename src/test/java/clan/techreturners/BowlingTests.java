package clan.techreturners;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingTests {
    private final int TOTAL_FRAMES = 10;

    @ParameterizedTest(name = "{index}) Two knocks of {0} and {1} will produce a score of {2}")
    @CsvSource({"3,4,7", "2,2,4", "6,3,9"})
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

    @ParameterizedTest(name = "A game with 10 frames of two knocks of {0} and {1} will produce a final score of {2}")
    @CsvSource({"5,2,70", "9,0,90", "2,4,60"})
    public void checkFinalScoreForOpenFramesGameWithSameKnocksPerFrame(int firstThrow, int secondThrow, int expectedFinalScore) {
        // Arrange
        Bowling game = new Bowling();

        // Act
        for (int frames = 1; frames <= TOTAL_FRAMES; frames++) {
            game.knock(firstThrow);
            game.knock(secondThrow);
        }
        int score = game.getScore();

        // Assert
        assertEquals(expectedFinalScore, score);
    }

    @ParameterizedTest(name = "A game with 10 frames of two knocks of {0} and {1} will produce a final score of {2}")
    @CsvSource({"5,5,150"})
    public void checkFinalScoreForSpareFramesGameWithSameKnocksPerFrame(int firstThrow, int secondThrow, int expectedFinalScore) {
        // Arrange
        Bowling game = new Bowling();

        // Act
        for (int frames = 1; frames <= TOTAL_FRAMES; frames++) {
            game.knock(firstThrow);
            game.knock(secondThrow);
        }
        game.knock(firstThrow);
        int score = game.getScore();

        // Assert
        assertEquals(expectedFinalScore, score);
    }

    @ParameterizedTest(name = "A game with all frames being \"strikes\" will produce a final score of {1}")
    @CsvSource({"10,300"})
    public void checkFinalScoreForAllStrikesGame(int firstThrow, int expectedFinalScore) {
        // Arrange
        Bowling game = new Bowling();

        // Act
        for (int frames = 1; frames <= TOTAL_FRAMES; frames++) {
            game.knock(firstThrow);
        }
        game.knock(firstThrow);
        game.knock(firstThrow);
        int score = game.getScore();

        // Assert
        assertEquals(expectedFinalScore, score);
    }
}