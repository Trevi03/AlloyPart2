import Alloys.Alloy;
import Alloys.Aluminium;
import Alloys.Nickel;
import Elements.*;

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        Element Cr = new Chromium(14.5,22,0.5);
        Element No = new Niobium(0,1.5,0.1);
        Element Co = new Cobalt(0,25,1);
        Element Mo = new Molybdenum(1.5,6,0.5);

        // create Alloy
        Alloy Ni = new Nickel(Cr,No,Co,Mo);

        // round the creepr to 6 sf
        DecimalFormat df = new DecimalFormat("0.#####E0");
        double roundedValue = Double.parseDouble(df.format(Ni.getMaxCreepR(18.0)));

        // print max creepr value for max cost 18
        System.out.println("Rounded value: "+roundedValue);
    }
}
