package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DateValidateTest {

    private ChristmasService christmasService;

    @Test
    @DisplayName("유효한 날짜 입력 시 정상적으로 변환")
    void validDateInput() {
        assertThat(christmasService.validateDate("1")).isEqualTo(1);
        assertThat(christmasService.validateDate("15")).isEqualTo(15);
        assertThat(christmasService.validateDate("31")).isEqualTo(31);
    }

    @Test
    @DisplayName("0 이하 또는 32 이상 입력 시 예외 발생")
    void invalidOutOfRangeDate() {
        assertThatThrownBy(() -> christmasValidator.validOutofRangeDate("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

        assertThatThrownBy(() -> christmasValidator.validOutofRangeDate("32"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("숫자가 아닌 값 입력 시 예외 발생")
    void invalidNonNumericDate() {
        assertThatThrownBy(() -> christmasValidator.isNumericDate("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

        assertThatThrownBy(() -> christmasValidator.isNumericDate("-"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

        assertThatThrownBy(() -> christmasValidator.isNumericDate(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("빈 문자열 또는 null 입력 시 예외 발생")
    void invalidEmptyOrNullDate() {
        assertThatThrownBy(() -> christmasService.validateDate(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

        assertThatThrownBy(() -> christmasService.validateDate(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

}
