package clan.techreturners;

public class Bowling {
    private int score;

    public void knock(int pins) {
        score += pins;
    }

    public int getScore() {
        return score;
    }
}