package christmas.service;

public class ChristmasValidator {

    public void validOutOfRangeDate(String strDate) {
        int date = Integer.parseInt(strDate);

        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void isNumericDate(String strDate) {

        if (strDate == null || strDate.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }

        try {
            Integer.parseInt(strDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
