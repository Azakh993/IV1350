package se.kth.iv1350.posSystem.startup;

import se.kth.iv1350.posSystem.controller.Controller;
import se.kth.iv1350.posSystem.view.View;

/**
 * The main class that starts up the program
 */
public class Main {

    /**
     * Main method responsible for starting up the program
     *
     * @param args The supplied command-lined arguments, stored in an array of String objects
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);

        view.sampleSale();
        view.sampleSale();
    }
}