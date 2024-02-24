package Alloys;

import Alloys.Alloy;
import Elements.Element;

import java.util.*;

public class Nickel extends Alloy {

    public Nickel(Element... elements) {
        super(8.9);
        this.elements.addAll(Arrays.asList(elements));
        initialize();
        calculate();
    }

}
