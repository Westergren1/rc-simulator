package rc.simulator;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class InputHandler {

    private static final Scanner scanner = new Scanner(System.in);

    // Creating the simulated Room from user input
    public static Room enterRoom() {
        List<String> inputList;

        do {
            inputList = getInputList();
        } while (!isRoomValid(inputList));

        List<Integer> integerList = inputList.stream()
            .map(Integer::parseInt)
            .toList();

        final Room room = new Room(integerList.get(0), integerList.get(1));
        Communication.confirmRoom(room);
        return room;
    }

    // Creating the simulated Car from user input
    public static Car enterCar(final Room room) {
        List<String> inputList;

        do {
            inputList = getInputList();
        } while (!isCarValid(inputList, room));

        final Car car = new Car(Integer.parseInt(inputList.get(0)), Integer.parseInt(inputList.get(1)), inputList.get(2)
            .toUpperCase());
        Communication.confirmCar(car);
        return car;
    }

    // Collecting commands from user input
    public static List<String> enterCommands() {
        String input;

        do {
            input = getInputString().replace(" ", "")
                .toUpperCase();
        } while (!areCommandsValid(input));

        final List<String> commandList = List.of(input.toUpperCase()
                                                     .split(""));
        Communication.confirmCommands(commandList);
        return commandList;
    }

    // Validates the rooms width and length
    public static boolean isRoomValid(final List<String> inputList) {
        if (inputList.size() != 2) {
            Communication.showInvalidInput("Enter 2 numbers");
            return false;
        }

        if (isNotNumbersOrIsNegative(inputList)) {
            Communication.showInvalidInput("Only positive numbers allowed");
            return false;
        }

        return true;
    }

    // Validates the cars starting position and heading
    public static boolean isCarValid(final List<String> inputList, final Room room) {
        List<String> validHeadings = List.of("N", "S", "W", "E");

        if (inputList.size() != 3) {
            Communication.showInvalidInput("Enter 2 numbers and heading of the car (N, S, W or E)");
            return false;
        }

        if (isNotNumbersOrIsNegative(inputList.subList(0, 2))) {
            Communication.showInvalidInput("Only non-negative numbers allowed for the cars starting position");
            return false;
        }

        if (positionOutOfBounds(inputList.subList(0, 2), room)) {
            Communication.showInvalidInput(
                "The starting position is not inside of the entered room: " + room);
            return false;
        }

        if (!validHeadings.contains(inputList.get(2)
                                        .toUpperCase())) {
            Communication.showInvalidInput("Valid headings of the car is N, S, W or E");
            return false;
        }

        return true;
    }

    // Validates the commands
    public static boolean areCommandsValid(final String input) {
        var validLetters = input.matches("[FBRL]+");
        if (!validLetters) {
            Communication.showInvalidInput("Only letters F, B, R or L allowed for the commands");
            return false;
        }
        return true;
    }

    public static boolean isNotNumbersOrIsNegative(final List<String> inputList) {
        return inputList.stream()
            .anyMatch(i ->  i.matches("\\b\\D+\\b") || Integer.parseInt(i) < 0);
    }

    public static boolean positionOutOfBounds(final List<String> inputList, final Room room) {
        var intList = inputList.stream()
            .map(Integer::parseInt)
            .toList();
        return intList.get(0) >= room.getWidth() || intList.get(1) >= room.getLength();
    }

    private static List<String> getInputList() {
        return Stream.of(getInputString().split(" "))
            .filter(Predicate.not(String::isBlank))
            .toList();
    }

    public static String getInputString() {
        return scanner.nextLine();
    }

    public static void closeScanner() {
        scanner.close();
    }
}
