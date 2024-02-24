import Alloys.Alloy;
import Alloys.Nickel;
import Elements.*;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAlloy {

    @Test
    public void testcase(){
        Element Cr = new Chromium(14.5,22,0.5);
        Element No = new Niobium(0,1.5,0.1);
        Element Co = new Cobalt(0,25,1);
        Element Mo = new Molybdenum(1.5,6,0.5);

        Alloy Ni = new Nickel(Cr,No,Co,Mo);
        List<Map<String, Double>> testList = new ArrayList<>();
        Class<?> listType = testList.getClass();

        // round to 6 sf
        DecimalFormat df = new DecimalFormat("0.#####E0");
        double roundedValue = Double.parseDouble(df.format(Ni.getMaxCreepR(18.0)));

        // compare
        assertEquals(Double.valueOf(roundedValue),1.72999E18);
        assertEquals(Ni.getOutputMap().getClass(),listType);
    }
}
