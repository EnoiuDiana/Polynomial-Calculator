import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJunit5 {
    @Test
    void TestMethod() {
        System.out.println("first test");
        assertEquals(2,2);
    }
}
