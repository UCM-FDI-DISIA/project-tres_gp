package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandExecuteException;

public class CommandExecuteExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Test message";
        CommandExecuteException exception = new CommandExecuteException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new Throwable("Test cause");
        CommandExecuteException exception = new CommandExecuteException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new Throwable("Test cause");
        CommandExecuteException exception = new CommandExecuteException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageCauseSuppressionEnableStackTrace() {
        String message = "Test message";
        Throwable cause = new Throwable("Test cause");
        boolean enableSuppression = true;
        boolean writableStackTrace = true;
        CommandExecuteException exception = new CommandExecuteException(message, cause, enableSuppression, writableStackTrace);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

