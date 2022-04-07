package clan.techreturners;

import java.util.ArrayList;

public class Frame {
    public enum FrameType {
        NONE,
        OPEN,
        SPARE,
        STRIKE;
    }

    private FrameType type = FrameType.NONE;
    private ArrayList<Integer> knocks = new ArrayList<>();
    private Integer bonusPoints = 0;

    public Frame(int pins) {
        knocks.add(pins);
        setFrameType();
    }

    public Frame() {
    }

    public FrameType getType() {
        return type;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public Integer getFirstKnock() {
        return knocks.size() > 0 ? knocks.get(0) : -1;
    }

    public void addPins(int pins) {
        if (knocks.size() < 2 && getScore() < 10) {
            knocks.add(pins);
            setFrameType();
        }
    }

    private void setFrameType() {
        if (knocks.get(0) == 10) {
            type = FrameType.STRIKE;
        } else if ((knocks.size() == 2 && getScore() == 10)) {
            type = FrameType.SPARE;
        } else if (knocks.size() == 2 && getScore() < 10) {
            type = FrameType.OPEN;
        }
    }

    public int getScore() {
        return knocks.stream().reduce(0, Integer::sum) + bonusPoints;
    }
}
