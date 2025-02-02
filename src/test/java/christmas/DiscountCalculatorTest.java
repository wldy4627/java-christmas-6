package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountCalculatorTest {

    @Test
    @DisplayName("크리스마스 디데이 할인 적용 확인")
    void christmasDdayDiscount() {
        assertThat(DiscountCalculator.calculateChristmasDdayDiscount(1)).isEqualTo(1000);
        assertThat(DiscountCalculator.calculateChristmasDdayDiscount(10)).isEqualTo(1900);
        assertThat(DiscountCalculator.calculateChristmasDdayDiscount(25)).isEqualTo(3400);
    }

    @Test
    @DisplayName("평일 할인 적용 확인")
    void weekdayDiscount() {
        Map<String, Integer> order = Map.of("초코케이크", 2, "아이스크림", 1);
        assertThat(DiscountCalculator.calculateWeekdayDiscount(order, "월")).isEqualTo(2023 * 3);
        assertThat(DiscountCalculator.calculateWeekdayDiscount(order, "수")).isEqualTo(2023 * 3);
        assertThat(DiscountCalculator.calculateWeekdayDiscount(order, "금")).isEqualTo(0); // 금요일(주말) 할인 없음
    }

    @Test
    @DisplayName("주말 할인 적용 확인")
    void weekendDiscount() {
        Map<String, Integer> order = Map.of("티본스테이크", 2, "시저샐러드", 2);
        assertThat(DiscountCalculator.calculateWeekendDiscount(order, "금")).isEqualTo(2023 * 4);
        assertThat(DiscountCalculator.calculateWeekendDiscount(order, "토")).isEqualTo(2023 * 4);
        assertThat(DiscountCalculator.calculateWeekendDiscount(order, "일")).isEqualTo(0); // 일요일은 할인 없음
    }

    @Test
    @DisplayName("특별 할인 적용 확인")
    void specialDiscount() {
        assertThat(DiscountCalculator.calculateSpecialDiscount(3).isEqualTo(1000));
        assertThat(DiscountCalculator.calculateSpecialDiscount(18).isEqualTo(0));
        assertThat(DiscountCalculator.calculateSpecialDiscount(31).isEqualTo(1000));
    }
}
