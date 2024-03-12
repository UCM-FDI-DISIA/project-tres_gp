package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.util.StringUtils;

public class StringUtilsTest {

    @Test
    void testRepeat() {
        assertEquals("aaa", StringUtils.repeat("a", 3));
        assertEquals("", StringUtils.repeat("a", 0));
    }

    @Test
    void testCenterWithDefaultPadding() {
        assertEquals("  abc  ", StringUtils.center("abc", 7));
        assertEquals(" abc ", StringUtils.center("abc", 5));
        assertEquals("  ", StringUtils.center("", 2));
    }

    @Test
    void testCenterWithCustomPadding() {
        assertEquals("##abc###", StringUtils.center("abc", 8, '#'));
        assertEquals("##abc##", StringUtils.center("abc", 7, '#'));
        assertEquals("###", StringUtils.center("", 3, '#'));
    }
}

