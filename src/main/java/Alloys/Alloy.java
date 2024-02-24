/*
* SuperClass Summary:
* Takes in the specific cost of alloy type
* Takes in any number of elements with any composition ranges
* */
package Alloys;

import Elements.Element;

import java.util.*;

public class Alloy {
    protected final double cost;
    protected final ArrayList<Element> elements = new ArrayList<>();
    final private Map<Integer, Double[]> costs = new HashMap<>();
    final private Map<Integer, Double[]> creepr = new HashMap<>();
    final private Map<Integer, Double[]> percentages = new HashMap<>();
    private List<Map<String, Double>> output;

    public Alloy(double cost) {
        this.cost = cost;
    }

    protected void initialize() {
        int elenum = 0;
        for (Element element : elements) {
            this.costs.put(elenum, element.getCost());
            this.creepr.put(elenum, element.getCreepR());
            this.percentages.put(elenum, element.getPercRange());
            elenum = elenum + 1;
        }
    }

    /* returns Creep Resistance and Cost pair in format shown below
     * List<Map<["creepr", Double],["cost", Double]>> */
    public List<Map<String, Double>> getOutputMap() {
        return this.output;
    }

    /*returns the max CreepR at the maximum cost*/
    public Double getMaxCreepR(Double max_cost) {
        sort("costs");
        List<Double> crlist = new ArrayList<>();
        for (Map<String, Double> item : this.output) {
            if (item.get("costs") <= max_cost) {
                crlist.add(item.get("creepr"));
            } else {
                break;
            }
        }
        Optional<Double> maxOptional = crlist.stream().max(Comparator.naturalOrder());
        Double max;
        max = maxOptional.orElse(0.0);
        return max;
    }

    /*
    * Main calculation for the sum fo Creep Resistance and Costs in all different compositions
    * composition = [elem1, elem2, elem3 ...] for all specified percentages
    * */
    protected void calculate() {
        int ml, cm;  //meta length , current meta
        this.output = new ArrayList<>();
        List<Map<String, Integer>> meta = getMeta();
        cm = ml = meta.size() - 1;

        // Iterates through all the combinations and updates output
        while (cm >= 0) {
            addNow(this.creepr, this.costs, this.percentages, meta, this.output);
            cm = ml;

            while (cm >= 0) {
                int current = meta.get(cm).get("current");
                current++;
                meta.get(cm).put("current", current);
                if (meta.get(cm).get("current") >= meta.get(cm).get("len")) {
                    meta.get(cm).put("current", 0);
                    cm--;
                } else {
                    break;
                }
            }

        }
    }

    private void sort(String key) {
        this.output.sort(new Comparator<Map<String, Double>>() {
            public int compare(final Map<String, Double> o1, final Map<String, Double> o2) {
                return o1.get(key).compareTo(o2.get(key));
            }
        });
    }

    private List<Map<String, Integer>> getMeta() {
        List<Map<String, Integer>> meta = new ArrayList<>();
        for (Map.Entry<Integer, Double[]> entry : this.creepr.entrySet()) {
            Integer key = entry.getKey();
            Double[] values = entry.getValue();

            Map<String, Integer> metaItem = new HashMap<>();
            metaItem.put("key", key);            // key name
            metaItem.put("len", values.length);  //length of value array
            metaItem.put("current", 0);          //current index

            meta.add(metaItem);
        }
        return meta;
    }

    private void addNow(Map<Integer, Double[]> creepr, Map<Integer, Double[]> costs, Map<Integer, Double[]> perc,
                        List<Map<String, Integer>> meta, List<Map<String, Double>> output) {
        Map<String, Double> add = new HashMap<>();
        double sumCR = 0.0;
        double sumC = 0.0;
        double sumP = 0.0;

        for (Map<String, Integer> metaItem : meta) {
            Double CR = creepr.get(metaItem.get("key"))[metaItem.get("current")];
            Double C = costs.get(metaItem.get("key"))[metaItem.get("current")];
            Double P = perc.get(metaItem.get("key"))[metaItem.get("current")];
            // Sum the values
            sumCR = sumCR + CR;
            sumC = sumC + C;
            sumP = sumP + P;
        }
        sumC = sumC + (100 - sumP) * this.cost / 100;

        add.put("creepr", sumCR);
        add.put("costs", sumC);
        add.put("NiPerc", 100 - sumP);
        output.add(add);
    }
}
