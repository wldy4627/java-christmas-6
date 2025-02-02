package christmas;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private static final Menu INSTANCE = new Menu();

    private final Map<String, Map<String, Integer>> menu;

    private Menu() {
        menu = new HashMap<>();
        initialize();
    }

    public static Menu getInstance() {
        return INSTANCE;
    }

    private void initialize() {
        // 에피타이저
        menu.put("에피타이저", Map.of(
                "양송이수프", 6000,
                "타파스", 5500,
                "시저샐러드", 8000
        ));

        // 메인 요리
        menu.put("메인", Map.of(
                "티본스테이크", 55000,
                "바비큐립", 54000,
                "해산물파스타", 35000,
                "크리스마스파스타", 25000
        ));

        // 디저트
        menu.put("디저트", Map.of(
                "초코케이크", 15000,
                "아이스크림", 5000
        ));

        // 음료
        menu.put("음료", Map.of(
                "제로콜라", 3000,
                "레드와인", 60000,
                "샴페인", 25000
        ));
    }

    public boolean isValidMenuName(String menuName) {
        for (Map<String, Integer> category : menu.values()) {
            if (category.containsKey(menuName)) {
                return true;
            }
        }
        return false;
    }
}
