package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GiftEventTest {

    @Test
    @DisplayName("증정 이벤트 확인 O")
    void giftEventEligible() {
        assertThat(ChristmasCalculator.getGiftMenu(120000)).isEqualTo("샴페인 1개");
        assertThat(ChristmasCalculator.getGiftMenu(150000)).isEqualTo("샴페인 1개");
    }

    @Test
    @DisplayName("증정 이벤트 확인 X")
    void giftEventNotEligible() {
        assertThat(ChristmasCalculator.getGiftMenu(50000)).isEqualTo("없음");
        assertThat(ChristmasCalculator.getGiftMenu(119999)).isEqualTo("없음");
    }
}
