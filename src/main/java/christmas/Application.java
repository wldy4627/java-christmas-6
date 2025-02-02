package christmas;

import christmas.controller.ChristmasController;
import christmas.service.ChristmasService;
import christmas.view.Input;
import christmas.view.Output;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ChristmasService christmasService = new ChristmasService();
        Input input = new Input();
        Output output = new Output();

        ChristmasController christmasController = new ChristmasController(christmasService, input, output);
        christmasController.run();
    }
}
