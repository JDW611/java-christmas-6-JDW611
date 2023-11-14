package christmas;

import christmas.controller.EventController;

public class Application {
    public static void main(String[] args) {
        final EventController game = new EventController();

        game.run();
    }
}
