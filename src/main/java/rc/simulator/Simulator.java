package rc.simulator;

import java.util.List;

public class Simulator {

    // Collecting inputs and starts simulation
    public static void runSimulator() {
        Communication.showEnterRoom();
        Room room = InputHandler.enterRoom();
        Communication.showEnterCar();
        Car car = InputHandler.enterCar(room);
        Communication.showEnterCommands();
        List<String> commandList = InputHandler.enterCommands();
        final boolean result = Simulator.startSimulation(room, car, commandList);
        Communication.showResult(car, result);
    }

    // Processing all inputs and returns result
    public static boolean startSimulation(final Room room, final Car car, final List<String> commandList) {
        Communication.showStartSimulation(room, car, commandList);

        for (String command : commandList) {
            car.handleCommand(command);
            if (checkIfCrashed(car, room)) {
                return false;
            }
        }
        return true;
    }

    // Verifies if car drove into a wall
    private static boolean checkIfCrashed(final Car car, final Room room) {
        var x = car.getCurrentXCoordinate();
        var y = car.getCurrentYCoordinate();

        if (x < 0 || y < 0) {
            return true;
        }

        return x == room.getWidth() || y == room.getLength();
    }

    public static void exit() {
        InputHandler.closeScanner();
        System.exit(0);
    }
}
