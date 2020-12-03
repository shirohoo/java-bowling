package bowling.domain.score;

import bowling.domain.score.exception.InvalidScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static bowling.domain.score.ScoreType.GUTTER;
import static bowling.domain.score.ScoreType.ORDINARY;
import static bowling.domain.score.ScoreType.SPARE;
import static bowling.domain.score.ScoreType.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("점수 테스트")
public class ScoreTest {
    private static Stream<Integer> getScores() {
        return IntStream.range(1, 11).boxed();
    }

    @DisplayName("점수 생성")
    @ParameterizedTest
    @MethodSource("getScores")
    public void createScore(int number) {
        Score score = Score.ordinary(number);

        assertThat(score.getScore()).isEqualTo(number);
    }

    @DisplayName("잘못된 숫자로 점수 생성")
    @ParameterizedTest
    @CsvSource(value = {"-1", "11"})
    public void invalidScore(int number) {
        assertThatThrownBy(() -> {
            Score.ordinary(number);
        }).isInstanceOf(InvalidScoreException.class);
    }

    @DisplayName("스트라이크 점수")
    @Test
    public void strike() {
        Score score = Score.strike();

        assertThat(score.getScore()).isEqualTo(10);
        assertThat(score.getType()).isEqualTo(STRIKE);
        assertThat(score.isStrike()).isEqualTo(true);
    }

    @DisplayName("스페어 점수")
    @Test
    public void spare() {
        Score score = Score.spare(4);

        assertThat(score.getScore()).isEqualTo(4);
        assertThat(score.getType()).isEqualTo(SPARE);
        assertThat(score.isSpare()).isEqualTo(true);
    }

    @DisplayName("거터 점수")
    @Test
    public void gutter() {
        Score score = Score.gutter();

        assertThat(score.getScore()).isEqualTo(0);
        assertThat(score.getType()).isEqualTo(GUTTER);
    }

    @DisplayName("일반 점수")
    @Test
    public void ordinary() {
        Score score = Score.ordinary(3);

        assertThat(score.getScore()).isEqualTo(3);
        assertThat(score.getType()).isEqualTo(ORDINARY);
        assertThat(score.isOrdinary()).isEqualTo(true);
    }

}