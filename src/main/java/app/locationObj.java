package app;

public class locationObj {
    int site;
    String name;
    double lat;
    double longitude; //long doesn't work, prolly already a thing
    String state;
    String region;

    public locationObj(int site, String name, double lat, double longitude, String state, String region) {
        this.site = site;
        this.name = name;
        this.lat = lat;
        this.longitude = longitude;
        this.state = state;
        this.region = region;
    }

    public locationObj() {
        this.site = 0;
        this.name = "";
        this.lat = 0.0;
        this.longitude = 0.0;
        this.state = "";
        this.region = "";
    }
}
