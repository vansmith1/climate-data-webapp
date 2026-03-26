package app;

public class Table2ST2A {
    String region;
    int stationCount;
    double metric; 

    public Table2ST2A() {
    }    
    public Table2ST2A(String region, int stationCount, double metric) {
        this.region = region;
        this.stationCount = stationCount;
        this.metric = metric;
    }

    public String getRegion() {
        return region;
    }

    public int getStationCount() {
        return stationCount;
    }

    public double getMetric() {
        return metric;
    }
} 
