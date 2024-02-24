/*
* SuperClass Summary:
* Creates element according to the specific alphas and costs
* getPercRange() - list of time values generated by (min,max,step)
* getCreepR() - list of Creep Resistance according to each percentage
* getCost() - list of Costs according to percentage
* */
package Elements;

public abstract class Element {
    final protected double alpha;
    final protected double cost;
    private Double[] prange;
    private Double[] vectorCR;
    private Double[] vectorC;
    private int arrsize;


    public Element(double alpha,double cost) {
        this.alpha = alpha;
        this.cost = cost;
    }

    protected void setPercRange(double min, double max, double step) {
        this.arrsize = (int) ((max - min) / step) + 1;
        this.prange = new Double[arrsize];
        for (int i = 0; i < arrsize; i++) {
            this.prange[i] = min + i * step;
        }
        setCreepRnCost();
    }

    private void setCreepRnCost(){
        Double[] vectorCR = new Double[arrsize];
        Double[] vectorC = new Double[arrsize];
        for (int i = 0; i < arrsize; i++) {
            vectorCR[i] = this.prange[i] * this.alpha;
            vectorC[i] = this.prange[i] * this.cost/100;
        }
        this.vectorCR = vectorCR;
        this.vectorC = vectorC;
    }

    /*list of time values generated by (min,max,step)*/
    public Double[] getPercRange(){
        return this.prange;
    }

    /*list of Creep Resistance according to each percentage*/
    public Double[] getCreepR(){
        return this.vectorCR;
    }

    /*list of Costs according to percentage*/
    public Double[] getCost(){
        return this.vectorC;
    }
}