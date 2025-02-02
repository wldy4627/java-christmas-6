package christmas.view;

public class Output {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printWelcomeMessage() {
        System.out.println(Message.WELCOME_MESSAGE.getMessage());
    }
}
