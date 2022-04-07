package clan.techreturners;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingTests {
    private final Integer STRIKE = 10;
    private Bowling game;

    @BeforeEach
    void Init() {
        game = new Bowling();
    }

    @ParameterizedTest(name = "{index}) A game with frames {0}, will produce a score of {1}")
    @MethodSource
    void checkScoreForBowlingGameWithMixedFrames(String[] frames, int expectedScore) {
        for (String frame : frames) {
            String[] pins = frame.split(",");
            int pinsFirstThrow = Integer.parseInt(pins[0]);
            roll(pinsFirstThrow, Integer.parseInt(pins[1]), pinsFirstThrow == STRIKE ? 1 : 2);
        }
        assertEquals(expectedScore, game.getScore());
    }

    @ParameterizedTest(name = "{index}) A game with {3} throws in frames of {0}, {1}, will produce a score of {2}")
    @MethodSource
    void checkScoreForBowlingGameWithFixedFrames(int pinsFirstThrow, int pinsSecondThrow, int expectedScore, int totalThrows) {
        roll(pinsFirstThrow, pinsSecondThrow, totalThrows);
        assertEquals(expectedScore, game.getScore());
    }

    private void roll(int pinsFirstThrow, int pinsSecondThrow, int totalThrows) {
        for (int i = 1; i <= totalThrows; i++) {
            game.knock(i % 2 == 1 || pinsFirstThrow == STRIKE ? pinsFirstThrow : pinsSecondThrow);
        }
    }

    public static Stream<Arguments> checkScoreForBowlingGameWithFixedFrames() {
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

    public static Stream<Arguments> checkScoreForBowlingGameWithMixedFrames() {
        return Stream.of(
                Arguments.of(new String[]{"5,2", "6,4", "1,0", "10,0", "7,3", "10,0", "10,0", "0,4", "5,5", "3,3"}, 116),
                Arguments.of(new String[]{"10,0", "8,0", "5,5", "10,0", "1,0", "10,0", "9,1", "0,0", "10,0", "3,7", "10,0"}, 128),
                Arguments.of(new String[]{"1,1", "10,0", "10,0", "10,0", "4,6", "2,8", "10,0", "6,0", "2,0", "10,0", "10,0", "5,0"}, 157)
        );
    }
}