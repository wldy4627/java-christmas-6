package christmas.service;

import christmas.Menu;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChristmasService {

    private final ChristmasValidator validator = new ChristmasValidator();
    Menu menu = Menu.getInstance();

    private final Map<String, Integer> discountAmountMap = new HashMap<>();

    public int validateDate(String date) {
        // 1. 1 ~ 31의 숫자인지 확인
        validator.validOutOfRangeDate(date);
        // 2. 입력된 값이 숫자인지 확인
        validator.isNumericDate(date);

        return Integer.parseInt(date);
    }

    public Map<String, Integer> convertToMap(String str) {

        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
        }

        Map<String, Integer> orderMap = new HashMap<>();
        String[] items = str.split(",");

        for (String item : items) {
            String[] parts = item.split("-");

            if (parts.length != 2) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 형식입니다.");
            }

            String menuName = parts[0].trim();
            String quantityStr = parts[1].trim();

            int quantity;
            // 개수가 숫자가 아닐 경우 예외 발생
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            // 중복된 메뉴 입력 시 예외 발생
            if (orderMap.containsKey(menuName)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            orderMap.put(menuName, quantity);
        }

        return orderMap;
    }

    public void validateOrder(Map<String, Integer> orderMap) {

        int totalQuantity = 0;

        for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();

            totalQuantity += quantity;
            // 1. 메뉴판에 없는 메뉴 입력 시 예외 발생
            if (!menu.isValidMenuName(menuName)) {
                throw new IllegalArgumentException("[ERROR1] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            // 2. 개수가 1 미만일 경우 예외 발생
            if (quantity < 1) {
                throw new IllegalArgumentException("[ERROR3] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            // 3. 최대 20개를 초과하는 주문 시 예외 발생
            if (totalQuantity > 20) {
                throw new IllegalArgumentException("[ERROR4] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }

    }

    public Map<String, Integer> calculateOrderCategory(Map<String, Integer> orderMap) {
        Map<String, Integer> categoryMap = new HashMap<>();

        categoryMap.put("에피타이저", 0);
        categoryMap.put("메인", 0);
        categoryMap.put("디저트", 0);
        categoryMap.put("음료", 0);

        for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            String categoryName = menu.getCategory(entry.getKey());
            categoryMap.put(categoryName, categoryMap.getOrDefault(categoryName, 0) + entry.getValue());
        }

        return categoryMap;
    }

    public int calculateTotalPrice(Map<String, Integer> orderMap) {
        int totalPrice = 0;

        for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            int price = menu.getMenuPrice(menuName);

            totalPrice += price * quantity;
        }

        return totalPrice;
    }

    public int calculateGiftCnt(int totalPrice) {
        return totalPrice / 120000;
    }

    private void initializeDiscountAmountMap() {
        discountAmountMap.put("디데이", 0);
        discountAmountMap.put("주말", 0);
        discountAmountMap.put("평일", 0);
        discountAmountMap.put("특별", 0);
    }

    public Map<String, Integer> calculateDiscountAmount(int date, Map<String, Integer> orderCategoryMap) {
        initializeDiscountAmountMap();

        // 크리스마스 디데이 할인
        if (date <= 25) {
            discountAmountMap.put("디데이", 1000 + 100 * (date - 1));
        }

        // 주말 할인
        if (date % 7 == 1 || date % 7 == 2) {
            discountAmountMap.put("주말", 2023 * orderCategoryMap.getOrDefault("메인", 0));
        } else {  // 평일 할인
            discountAmountMap.put("평일", 2023 * orderCategoryMap.getOrDefault("디저트", 0));
        }

        // 특별 할인
        if (date % 7 == 3 || date == 25) {
            discountAmountMap.put("특별", discountAmountMap.getOrDefault("총 금액", 0) + 1000);
        }

        return discountAmountMap;
    }

    public List<String> generateBenefitMap(Map<String, Integer> discountAmountMap, int giftCnt) {
        List<String> benefits = new ArrayList<>();
        String formattedBenefit;

        if (discountAmountMap.getOrDefault("디데이", 0) > 0) {
            formattedBenefit = NumberFormat.getInstance().format(discountAmountMap.get("디데이")) + "원";
            benefits.add("크리스마스 디데이 할인: -" + formattedBenefit);
        }
        if (discountAmountMap.getOrDefault("평일", 0) > 0) {
            formattedBenefit = NumberFormat.getInstance().format(discountAmountMap.get("평일")) + "원";
            benefits.add("평일 할인: -" + formattedBenefit);
        }
        if (discountAmountMap.getOrDefault("주말", 0) > 0) {
            formattedBenefit = NumberFormat.getInstance().format(discountAmountMap.get("주말")) + "원";
            benefits.add("주말 할인: -" + formattedBenefit);
        }
        if (discountAmountMap.getOrDefault("특별", 0) > 0) {
            formattedBenefit = NumberFormat.getInstance().format(discountAmountMap.get("특별")) + "원";
            benefits.add("특별 할인: -" + formattedBenefit);
        }
        if (giftCnt > 0) {
            formattedBenefit = NumberFormat.getInstance().format(25000 * giftCnt) + "원";
            benefits.add("증정 이벤트: -" + formattedBenefit);
        }

        return benefits;
    }

    public int calculateTotalBenefitsAmount(Map<String, Integer> discountAmountMap, int giftCnt) {
        int totalBenefit = 0;

        for (Map.Entry<String, Integer> entry : discountAmountMap.entrySet()) {
            totalBenefit += entry.getValue();
        }

        totalBenefit += 25000 * giftCnt;

        return totalBenefit;
    }

    public int calculateTotalPayment(int totalPrice, Map<String, Integer> discountAmountMap) {
        int totalPayment = totalPrice;

        for (Map.Entry<String, Integer> entry : discountAmountMap.entrySet()) {
            totalPayment -= entry.getValue();
        }

        return totalPayment;
    }

    public String calculateEventBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= 20000) {
            return "산타";
        } else if (totalBenefitAmount >= 10000) {
            return "트리";
        } else if (totalBenefitAmount >= 5000) {
            return "별";
        } else {
            return "없음";
        }
    }
}
