import Elements.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestElement {
    @Test
    public void testcase(){
        Element Cr = new Chromium(14.5,22,0.5);

        assertEquals(Cr.getPercRange().getClass(),Double[].class);
        assertEquals(Cr.getCreepR().getClass(),Double[].class);
        assertEquals(Cr.getCost().getClass(),Double[].class);
    }
}
