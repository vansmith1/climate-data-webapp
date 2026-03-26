package app;

public class WeatherStation {
    int site;
    String name;
    String region;
    double latitude;

    public WeatherStation() {
    }

    public WeatherStation(int site, String name, String region, double latitude) {
        this.site = site;
        this.name = name;
        this.region = region;
        this.latitude = latitude;
    }

    public int getSite() {
        return site;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public double getLatitude() {
        return latitude;
    }

    public static void add(WeatherStation weatherStation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
}