/*
* Extra example for adding now types of alloys
* */
package Alloys;

import Elements.Element;
import java.util.Arrays;

public class Aluminium extends Alloy {

    public Aluminium(Element... elements) {
        super(2.5);
        this.elements.addAll(Arrays.asList(elements));
        initialize();
        calculate();
    }

}
