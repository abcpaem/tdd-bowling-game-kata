package clan.techreturners;

import java.util.ArrayList;

public class Bowling {
    private final Integer MAX_FRAMES = 10;
    private ArrayList<Frame> frames = new ArrayList<>();
    private Integer currentFrameIndex = 0;

    public Bowling() {
        frames.add(new Frame());
    }

    public void knock(int pins) {
        Frame currentFrame = frames.get(currentFrameIndex);
        if (frames.get(currentFrameIndex).getType() == Frame.FrameType.NONE) {
            currentFrame.addPins(pins);
        } else {
            currentFrame = new Frame(pins);
            frames.add(currentFrame);
            currentFrameIndex++;
        }
    }

    public int getScore() {
        updateBonusPoints();
        return frames.stream().limit(MAX_FRAMES).mapToInt(f -> f.getScore()).sum();
    }

    private void updateBonusPoints() {
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            if (frame.getType() == Frame.FrameType.SPARE && i + 1 < frames.size()) {
                frame.setBonusPoints(frames.get(i + 1).getFirstKnock());
            } else if (frame.getType() == Frame.FrameType.STRIKE && i + 2 < frames.size()) {
                frame.setBonusPoints(frames.get(i + 1).getScore() + frames.get(i + 2).getScore());
            }
        }
    }
}