package clan.techreturners;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingTests {
    private final Integer STRIKE = 10;
    private Bowling game = new Bowling();

    @ParameterizedTest(name = "{index}) A game with {3} throws in frames of {0}, {1}, will produce a score of {2}")
    @MethodSource
    void checkScoreForBowlingGameInFrames(int pinsFirstThrow, int pinsSecondThrow, int expectedScore, int totalThrows) {
        for (int i = 1; i <= totalThrows; i++) {
            game.knock(i % 2 == 1 || pinsFirstThrow == STRIKE ? pinsFirstThrow : pinsSecondThrow);
        }
        assertEquals(expectedScore, game.getScore());
    }

    public static Stream<Arguments> checkScoreForBowlingGameInFrames() {
        return Stream.of(
                // One frame
                Arguments.of(3, 4, 7, 2),
                Arguments.of(2, 2, 4, 2),
                Arguments.of(6, 3, 9, 2),
                // Open Frames
                Arguments.of(5, 2, 70, 20),
                Arguments.of(9, 0, 90, 20),
                Arguments.of(2, 4, 60, 20),
                // Spare Frames
                Arguments.of(5, 5, 150, 21),
                Arguments.of(4, 6, 140, 21),
                Arguments.of(9, 1, 190, 21),
                // Strike Frames
                Arguments.of(10, 0, 300, 12)
        );
    }
}