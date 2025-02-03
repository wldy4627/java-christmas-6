package christmas.controller;

import christmas.service.ChristmasService;
import christmas.view.Input;
import christmas.view.Output;

import java.util.HashMap;
import java.util.Map;

public class ChristmasController {

    private final ChristmasService christmasService;
    private final Input input;
    private final Output output;

    public ChristmasController(ChristmasService christmasService, Input input, Output output) {
        this.christmasService = christmasService;
        this.input = input;
        this.output = output;
    }

    public void run() {
        output.printWelcomeMessage();

        // 날짜 입력받기
        int date = 1;
        try {
            date = christmasService.validateDate(input.readDate());
        } catch (IllegalArgumentException e) {
            output.printErrorMessage(e.getMessage());
        }

        // 주문 메뉴와 개수 입력받기
        Map<String, Integer> orderMap = new HashMap<>();
        try {
            orderMap = christmasService.convertToMap(input.readOrder());
            christmasService.validateOrder(orderMap);
        } catch (IllegalArgumentException e) {
            output.printErrorMessage(e.getMessage());
        }

        Map<String, Integer> orderCategoryMap = christmasService.calculateOrderCategory(orderMap);

        // 혜택 계산 및 출력
        output.printEventsMessage(date);
        // 1. 주문 메뉴 출력
        output.printOrderMenu(orderMap);
        // 2. 할인 전 총 주문 금액 출력
        int totalPrice = christmasService.calculateTotalPrice(orderMap);
        output.printTotalPrice(totalPrice);
        // 3. 증정 메뉴 출력
        int giftCnt = christmasService.calculateGiftCnt(totalPrice);
        output.printGift(giftCnt);
        // 4. 혜택 내역 출력
        Map<String, Integer> discountAmountMap = christmasService.calculateDiscountAmount(date, orderCategoryMap);
        output.printBenefit(christmasService.generateBenefitMap(discountAmountMap, giftCnt));
        // 5. 총 혜택 금액 출력
        int totalBenefitAmount = christmasService.calculateTotalBenefitsAmount(discountAmountMap, giftCnt);
        output.printBenefitAmount(totalBenefitAmount);
        // 6. 할인 후 예상 결제 금액 출력
        output.printTotalPayment(christmasService.calculateTotalPayment(totalPrice, discountAmountMap));
        // 7. 이벤트 뱃지 출력
        output.printEventBadge(christmasService.calculateEventBadge(totalBenefitAmount));
    }
}
