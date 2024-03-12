package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.FullColumnException;

public class FullColumnExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Test message";
        FullColumnException exception = new FullColumnException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new Throwable("Test cause");
        FullColumnException exception = new FullColumnException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new Throwable("Test cause");
        FullColumnException exception = new FullColumnException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageCauseSuppressionEnableStackTrace() {
        String message = "Test message";
        Throwable cause = new Throwable("Test cause");
        boolean enableSuppression = true;
        boolean writableStackTrace = true;
        FullColumnException exception = new FullColumnException(message, cause, enableSuppression, writableStackTrace);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
