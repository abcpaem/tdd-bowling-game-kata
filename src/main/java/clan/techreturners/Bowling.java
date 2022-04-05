package clan.techreturners;

import java.util.ArrayList;

public class Bowling {
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
        return frames.stream().mapToInt(f -> f.getScore()).sum();
    }

    private void updateBonusPoints() {
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            if (frame.getType() == Frame.FrameType.SPARE && i + 1 < frames.size() - 1) {
                frame.setBonusPoints(frames.get(i + 1).getFirstKnock());
            }
        }
    }
}