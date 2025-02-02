package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderValidatorTest {

    @Test
    @DisplayName("유효한 주문 입력 시 정상적으로 반환")
    void validOrderInput() {
        String input = "타파스-1,제로콜라-1";
        Map<String, Integer> orders = christmasValidator.validateOrder(input);

        assertThat(orders).hasSize(2);
        assertThat(orders.get("타파스")).isEqualTo(1);
        assertThat(orders.get("제로콜라")).isEqualTo(1);
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴 입력 시 예외 발생")
    void invalidMenuInput() {
        String input = "초밥-2,제로콜라-1";

        assertThatThrownBy(() -> christmasValidator.validateOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("중복된 메뉴 입력 시 예외 발생")
    void duplicateMenuInput() {
        String input = "티본스테이크-2,티본스테이크-1";

        assertThatThrownBy(() -> christmasValidator.validateOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("개수가 숫자가 아닐 경우 예외 발생")
    void invalidQuantityFormat() {
        String input = "초코케이크-abc,제로콜라-2";

        assertThatThrownBy(() -> christmasValidator.validateOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("개수가 1 미만일 경우 예외 발생")
    void invalidQuantityLessThanOne() {
        String input = "바비큐립-0,제로콜라-2";

        assertThatThrownBy(() -> christmasValidator.validateOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("최대 20개를 초과하는 주문 시 예외 발생")
    void exceedMaxOrderLimit() {
        String input = "티본스테이크-10,초코케이크-6,제로콜라-5";

        assertThatThrownBy(() -> christmasValidator.validateOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}
