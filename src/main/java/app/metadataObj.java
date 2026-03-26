package app;

//Andy

public class metadataObj {
    String field;
    String description;

    public metadataObj() {
        this.field = "";
        this.description = "";
    }

    public metadataObj(String field, String description) {
        this.field = field;
        this.description = description;
    }

    public String getField() {
        return this.field;
    }

    public String getDescription() {
        return this.description;
    }
}
