package christmas.view;

import java.util.Scanner;

public class Input {

    Scanner sc = new Scanner(System.in);

    public String readDate() {
        System.out.println(Message.READ_DATE_MESSAGE.getMessage());
        return sc.nextLine();
    }
}
