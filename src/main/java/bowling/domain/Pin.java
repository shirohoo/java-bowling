package bowling.domain;

import java.util.Objects;

public class Pin {
    public static final int MAX_PIN_COUNT = 10;

    private final int count;

    public Pin(int count) {
        this.validate(count);
        this.count = count;
    }

    private void validate(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("쓰러트린 핀의 개수는 0이상 이어야 합니다.");
        }

        if (count > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("쓰러트린 핀의 개수는 10이하 이어야 합니다.");
        }
    }

    public boolean isEnd() {
        return count == MAX_PIN_COUNT;
    }

    public boolean isSpare(Pin fallenPins) {
        return this.count + fallenPins.getCount() == MAX_PIN_COUNT;
    }

    public boolean validate(Pin fallenPins) {
        return this.count + fallenPins.getCount() <= MAX_PIN_COUNT;
    }

    public int sum(Pin otherPins) {
        return count + otherPins.count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return count == pin.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}