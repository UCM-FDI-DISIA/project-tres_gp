package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.OffWorldException;

public class OffWorldExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Test message";
        OffWorldException exception = new OffWorldException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new Throwable("Test cause");
        OffWorldException exception = new OffWorldException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new Throwable("Test cause");
        OffWorldException exception = new OffWorldException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageCauseSuppressionEnableStackTrace() {
        String message = "Test message";
        Throwable cause = new Throwable("Test cause");
        boolean enableSuppression = true;
        boolean writableStackTrace = true;
        OffWorldException exception = new OffWorldException(message, cause, enableSuppression, writableStackTrace);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

