package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentCalculatorTest {

    @Test
    @DisplayName("할인 후 예상 결제 금액 계산")
    void calculateFinalPaymentAmount() {
        assertThat(ChristmasCalculator.calculateFinalPayment(50000, 5000)).isEqualTo(45000);
        assertThat(ChristmasCalculator.calculateFinalPayment(100000, 25000)).isEqualTo(75000);
        assertThat(ChristmasCalculator.calculateFinalPayment(500000, 50000)).isEqualTo(450000);
    }

    @Test
    @DisplayName("여러 개의 할인 적용 시 최종 결제 금액 계산 확인")
    void multipleDiscountsFinalPayment() {
        assertThat(ChristmasCalculator.calculateFinalPayment(150000, 3400 + 4046 + 1000))
                .isEqualTo(141554); // 150000 - (크리스마스 할인 3400 + 평일 할인 4046 + 특별 할인 1000)

        assertThat(ChristmasCalculator.calculateFinalPayment(90000, 1900 + 4046))
                .isEqualTo(84054); // 90000 - (크리스마스 할인 1900 + 평일 할인 4046)

        assertThat(ChristmasCalculator.calculateFinalPayment(200000, 3400 + 6069))
                .isEqualTo(190531); // 200000 - (크리스마스 할인 3400 + 주말 할인 6069)
    }

    @Test
    @DisplayName("할인 금액이 총 주문 금액보다 큰 경우 결제 금액은 최소 0원")
    void discountExceedsOrderTotal() {
        assertThat(ChristmasCalculator.calculateFinalPayment(10000, 12000)).isEqualTo(0);
        assertThat(ChristmasCalculator.calculateFinalPayment(5000, 6000)).isEqualTo(0);
    }
}
