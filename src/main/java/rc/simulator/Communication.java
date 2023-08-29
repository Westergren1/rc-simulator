package rc.simulator;

import java.util.List;

public class Communication {
    public static void showEnterRoom() {
        System.out.println("\nWelcome to RC-Simulator\n");
        System.out.println("Please enter the size of the room (Two non-negative numbers separated with a space): ");
    }

    public static void showEnterCar() {
        System.out.println(
            "\nPlease enter the starting position and heading (N, S, W, E) of your car (Two non-negative numbers and one letter separated with spaces): ");
    }

    public static void showEnterCommands() {
        System.out.println(
            "\nPlease enter the commands the car will execute, F (forward), B (backwards), R (rotate right) or L (rotate left): ");
    }

    public static void confirmRoom(final Room room) {
        System.out.println("Entered room: " + room);
    }

    public static void confirmCar(final Car room) {
        System.out.println("Entered car: " + room);
    }

    public static void confirmCommands(final List<String> commandList) {
        System.out.println("Entered commands: " + commandList);
    }

    public static void showStartSimulation(final Room room, final Car car, final List<String> commandList) {
        System.out.println("\nStarting simulation with following inputs:");
        System.out.println("Room: " + room + "\nCar: " + car + "\nCommands: " + commandList);
    }

    public static void showResult(final Car car, final boolean success) {
        System.out.println("\nRESULT:");
        if (success) {
            printSuccessful(car);
        } else {
            printUnsuccessful(car);
        }
        runAgain();
    }

    private static void printSuccessful(final Car car) {
        System.out.println("The simulation was successful! The end position of the car was: " + car);
    }

    private static void printUnsuccessful(final Car car) {
        System.out.println("The simulation failed! Car collided with a wall when tried to execute command: " +
                           car.getLatestCommand() + " into position: " + car);
    }

    private static void runAgain() {
        System.out.println("\nRun simulator again? Enter Yes/Y");
        final String input = InputHandler.getInputString();
        if ("Yes".equalsIgnoreCase(input) || "Y".equalsIgnoreCase(input)) {
            Simulator.runSimulator();
        } else {
            Simulator.exit();
        }
    }

    public static void showInvalidInput(final String errorMessage) {
        System.out.println("\nInvalid input: " + errorMessage + "\nPlease try again.\n");
    }
}
