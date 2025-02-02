package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BenefitCalculatorTest {

    @Test
    @DisplayName("총 혜택 금액 계산")
    void calculateTotalBenefitAmount() {
        assertThat(ChristmasCalculator.calculateTotalBenefit(5000, "샴페인 1개")).isEqualTo(30000); // 5000 + 샴페인(25,000)
        assertThat(ChristmasCalculator.calculateTotalBenefit(8000, "없음")).isEqualTo(8000);
        assertThat(ChristmasCalculator.calculateTotalBenefit(12000, "샴페인 1개")).isEqualTo(37000); // 12000 + 25000
    }

    @Test
    @DisplayName("이벤트 배지 부여")
    void assignEventBadge() {
        assertThat(ChristmasCalculator.getEventBadge(4000)).isEqualTo("없음");
        assertThat(ChristmasCalculator.getEventBadge(5000)).isEqualTo("별");
        assertThat(ChristmasCalculator.getEventBadge(9999)).isEqualTo("별");
        assertThat(ChristmasCalculator.getEventBadge(10000)).isEqualTo("트리");
        assertThat(ChristmasCalculator.getEventBadge(19999)).isEqualTo("트리");
        assertThat(ChristmasCalculator.getEventBadge(20000)).isEqualTo("산타");
    }
}
