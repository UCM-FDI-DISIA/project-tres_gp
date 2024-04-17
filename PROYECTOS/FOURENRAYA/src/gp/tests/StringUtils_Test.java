package gp.tests;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import gp.util.*;

public class StringUtils_Test {

    @Test
    void testRepeat() {
        String repeated = StringUtils.repeat("abc", 3);
        assertEquals("abcabcabc", repeated);
    }

    @Test
    void testCenter() {
        String centered = StringUtils.center("Hello", 10);
        assertEquals("  Hello   ", centered);

        String centered2 = StringUtils.center("World", 10, '*');
        assertEquals("**World***", centered2);

        String centered3 = StringUtils.center("Hello", 5);
        assertEquals("Hello", centered3);

        String centered4 = StringUtils.center("Test", 3);
        assertEquals("Tes", centered4);
    }
}

