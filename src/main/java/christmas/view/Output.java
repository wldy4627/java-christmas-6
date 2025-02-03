package christmas.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public class Output {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printWelcomeMessage() {
        System.out.println(Message.WELCOME_MESSAGE.getMessage());
    }

    public void printEventsMessage(int date) {
        System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void printOrderMenu(Map<String, Integer> orderMap) {
        System.out.println("<주문 메뉴>");

        for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + "개");
        }

        System.out.println();
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        String formattedTotalPrice = NumberFormat.getInstance().format(totalPrice) + "원\n";
        System.out.println(formattedTotalPrice);
    }

    public void printGift(int giftCnt) {
        System.out.println("<증정 메뉴>");
        if (giftCnt == 0) {
            System.out.println("없음\n");
        } else {
            System.out.println("샴페인" + giftCnt + "개\n");
        }
    }

    public void printBenefit(List<String> benefits) {
        System.out.println("<혜택 내역>");

        if (benefits.isEmpty()) {
            System.out.println("없음");
        } else {
            benefits.forEach(System.out::println);
        }

        System.out.println();
    }

    public void printBenefitAmount(int totalBenefitAmount) {
        System.out.println("<총혜택 금액>");

        if (totalBenefitAmount == 0)  {
            System.out.println("0원\n");
        } else {
            String formattedBenefitAmount = "-" + NumberFormat.getInstance().format(totalBenefitAmount) + "원\n";
            System.out.println(formattedBenefitAmount);
        }
    }

    public void printTotalPayment(int totalPayment) {
        System.out.println("<할인 후 예상 결제 금액>");
        String formattedTotalPayment = NumberFormat.getInstance().format(totalPayment) + "원\n";
        System.out.println(formattedTotalPayment);
    }

    public void printEventBadge(String badgeName) {
        System.out.println("<12월 이벤트 배지>\n" + badgeName + "\n");
    }
}
