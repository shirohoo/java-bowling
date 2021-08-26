package bowling.frame;

import bowling.dto.StateDtos;
import bowling.pin.Pin;

import java.util.List;

public interface Frame {
    boolean hasTurn();

    void play(final Pin pin);

    void proceed(final List<Frame> frames);

    StateDtos convertToStateDtos();

    boolean isLastFrame();
}
