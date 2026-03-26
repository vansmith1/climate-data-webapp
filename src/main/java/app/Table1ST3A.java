package app;

public class Table1ST3A {
    String stationName;
    Double aveMetric1;
    Double aveMetric2;
    Double changePer;
    Double diffReference;

    public Table1ST3A(String stationName, Double aveMetric1, Double aveMetric2, Double changePer, Double diffReference) {
        this.stationName = stationName;
        this.aveMetric1 = aveMetric1;
        this.aveMetric2 = aveMetric2;
        this.changePer = changePer;
        this.diffReference = diffReference;
    }

    public String getStationName() {
        return stationName;
    }

    public double getAveMetricOne() {
        return aveMetric1;
    }

    public double getAveMetricTwo() {
        return aveMetric2;
    }
    public double getChange() {
        return changePer;
    }
    public double getDifference() {
        return diffReference;
    }    
}