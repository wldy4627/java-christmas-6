package christmas.controller;

import christmas.service.ChristmasService;
import christmas.view.Input;
import christmas.view.Output;

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
    }
}
