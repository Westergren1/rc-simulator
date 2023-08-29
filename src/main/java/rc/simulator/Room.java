package rc.simulator;

public class Room {
    private final int width;
    private final int length;

    public Room(final int width, final int length) {
        this.width = width;
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Width: " + this.width + " Length: " + this.length;
    }
}
