package christmas.service;

import christmas.Menu;

import java.util.HashMap;
import java.util.Map;

public class ChristmasService {

    private final ChristmasValidator validator = new ChristmasValidator();
    Menu menu = Menu.getInstance();

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
}
