package rc.simulator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class InputHandlerTest {

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void isRoomValidTrueTest() {
        var validList = List.of("4", "4");
        var validList2 = List.of("44", "44");

        var actual = InputHandler.isRoomValid(validList);
        var actual2 = InputHandler.isRoomValid(validList2);

        Assert.assertTrue(actual, "Must be true");
        Assert.assertTrue(actual2, "Must be true");
    }

    @Test
    public void isRoomValidFalseTest() {
        var tooMany = List.of("4", "4", "2");
        var tooFew = List.of("3");
        var letters = List.of("1", "B");
        var negative = List.of("-2", "-2");

        var actualTooMany = InputHandler.isRoomValid(tooMany);
        var actualTooFew = InputHandler.isRoomValid(tooFew);
        var actualLetters = InputHandler.isRoomValid(letters);
        var actualNegative = InputHandler.isRoomValid(negative);

        Assert.assertFalse(actualTooMany, "Must be false");
        Assert.assertFalse(actualTooFew, "Must be false");
        Assert.assertFalse(actualLetters, "Must be false");
        Assert.assertFalse(actualNegative, "Must be false");
    }

    @Test
    public void isCarValidTrueTest() {
        var inputList = List.of("2", "2", "N");
        var room = new Room(4, 4);

        var actual = InputHandler.isCarValid(inputList, room);

        Assert.assertTrue(actual, "Must be true");
    }

    @Test
    public void isCarValidFalseTest() {
        var tooMany = List.of("2", "2", "2", "F");
        var tooFew = List.of("3", "F");
        var noDirection = List.of("3", "2", "2");
        var outOfBounds = List.of("6", "6", "F");
        var notNumbers = List.of("F", "6", "F");
        var room = new Room(4, 4);

        var actualTooMany = InputHandler.isCarValid(tooMany, room);
        var actualTooFew = InputHandler.isCarValid(tooFew, room);
        var actualNoDirection = InputHandler.isCarValid(noDirection, room);
        var actualOutOfBounds = InputHandler.isCarValid(outOfBounds, room);
        var actualnotNumbers = InputHandler.isCarValid(notNumbers, room);

        Assert.assertFalse(actualTooMany, "Must be false");
        Assert.assertFalse(actualTooFew, "Must be false");
        Assert.assertFalse(actualNoDirection, "Must be false");
        Assert.assertFalse(actualOutOfBounds, "Must be false");
        Assert.assertFalse(actualnotNumbers, "Must be false");
    }

    @Test
    public void areCommandsValidTrueTest() {
        var input = "FFRRLLBB";

        var actual = InputHandler.areCommandsValid(input);

        Assert.assertTrue(actual, "Must be true");
    }

    @Test
    public void areCommandsValidFalseTest() {
        var input = "FFFWWF22";

        var actual = InputHandler.areCommandsValid(input);

        Assert.assertFalse(actual, "Must be false");
    }

    @Test
    public void isNotNumbersTrueTest() {
        var inputList = List.of("F", "B", "0", "5");

        var actual = InputHandler.isNotNumbersOrIsNegative(inputList);

        Assert.assertTrue(actual, "Must be true");
    }

    @Test
    public void isNotNumbersFalseTest() {
        var inputList = List.of("1", "2", "3", "4");

        var actual = InputHandler.isNotNumbersOrIsNegative(inputList);

        Assert.assertFalse(actual, "Must be false");
    }
}
