package christmas.service;

public class ChristmasService {

    private final ChristmasValidator validator = new ChristmasValidator();

    public int validateDate(String date) {
        // 1. 1 ~ 31의 숫자인지 확인
        validator.validOutOfRangeDate(date);
        // 2. 입력된 값이 숫자인지 확인
        validator.isNumericDate(date);

        return Integer.parseInt(date);
    }
}
