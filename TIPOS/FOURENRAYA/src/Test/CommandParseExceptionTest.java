package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandParseException;

public class CommandParseExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Test message";
        CommandParseException exception = new CommandParseException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new Throwable("Test cause");
        CommandParseException exception = new CommandParseException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new Throwable("Test cause");
        CommandParseException exception = new CommandParseException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageCauseSuppressionEnableStackTrace() {
        String message = "Test message";
        Throwable cause = new Throwable("Test cause");
        boolean enableSuppression = true;
        boolean writableStackTrace = true;
        CommandParseException exception = new CommandParseException(message, cause, enableSuppression, writableStackTrace);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

