package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.*;

public class InvalidWinException_Test {

    @Test
    public void testDefaultConstructor() {
        InvalidWinException exception = new InvalidWinException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testMessageConstructor() {
        String message = "Custom message";
        InvalidWinException exception = new InvalidWinException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testMessageCauseConstructor() {
        String message = "Custom message";
        Throwable cause = new RuntimeException("Cause of the exception");
        InvalidWinException exception = new InvalidWinException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testCauseConstructor() {
        Throwable cause = new RuntimeException("Cause of the exception");
        InvalidWinException exception = new InvalidWinException(cause);
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testFullConstructor() {
        String message = "Custom message";
        Throwable cause = new RuntimeException("Cause of the exception");
        boolean enableSuppression = true;
        boolean writableStackTrace = true;
        InvalidWinException exception = new InvalidWinException(message, cause, enableSuppression, writableStackTrace);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals(enableSuppression, exception.getSuppression());
        assertEquals(writableStackTrace, exception.getStackTrace().length > 0);
    }
}
