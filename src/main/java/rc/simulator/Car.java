package rc.simulator;

public class Car {
    private final int[] currentPosition;
    private String heading;
    private String latestCommand;

    public Car(final int x, final int y, final String heading) {
        this.currentPosition = new int[] { x, y };
        this.heading = heading;
    }

    public void handleCommand(final String command) {
        this.latestCommand = command;
        if ("F".equalsIgnoreCase(command) || "B".equalsIgnoreCase(command)) {
            drive(command);
        } else {
            rotate(command);
        }
    }

    public void drive(final String direction) {
        if ("F".equalsIgnoreCase(direction)) {
            forward();
        } else {
            backwards();
        }
    }

    private void rotate(final String direction) {
        switch (heading) {
            case "N" -> this.heading = "L".equals(direction) ? "W" : "E";
            case "S" -> this.heading = "L".equals(direction) ? "E" : "W";
            case "W" -> this.heading = "L".equals(direction) ? "S" : "N";
            case "E" -> this.heading = "L".equals(direction) ? "N" : "S";
        }
    }

    private void forward() {
        switch (heading) {
            case "N" -> this.currentPosition[1]++;
            case "S" -> this.currentPosition[1]--;
            case "W" -> this.currentPosition[0]--;
            case "E" -> this.currentPosition[0]++;
        }
    }

    private void backwards() {
        switch (heading) {
            case "N" -> this.currentPosition[1]--;
            case "S" -> this.currentPosition[1]++;
            case "W" -> this.currentPosition[0]++;
            case "E" -> this.currentPosition[0]--;
        }
    }

    public int getCurrentXCoordinate() {
        return currentPosition[0];
    }

    public int getCurrentYCoordinate() {
        return currentPosition[1];
    }

    public String getLatestCommand() {
        return this.latestCommand;
    }

    @Override
    public String toString() {
        return "[" + this.currentPosition[0] + "," + this.currentPosition[1] + "] Heading: " + this.heading;
    }
}
