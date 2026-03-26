package app;

public class dateTimeObj {
    String date;
    String dayOfWeek;
    int week;
    String month;
    int monthNum;
    String quarter;
    String half;
    int year;
    int halfDecade;
    int decade;

    public dateTimeObj() {
        this.date = "";
        this.dayOfWeek = "";
        this.week = 0;
        this.month = "";
        this.monthNum = 0;
        this.quarter = "";
        this.half = "";
        this.year = 0;
        this.halfDecade = 0;
        this.decade = 0;
    }

    public dateTimeObj(String date, String dayOfWeek, int week, String month, int monthNum, String quarter, String half, int year, int halfDecade, int decade) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.week = week;
        this.month = month;
        this.monthNum = monthNum;
        this.quarter = quarter;
        this.half = half;
        this.year = year;
        this.halfDecade = halfDecade;
        this.decade = decade;
    }

    public String getDate() {
        return this.date;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public int getWeek() {
        return this.week;
    }
    
    public String getMonth() {
        return this.month;
    }

    public int getMonthNum() {
        return this.monthNum;
    }

    public String getQuarter() {
        return this.quarter;
    }

    public String getHalf() {
        return this.half;
    }

    public int getYear() {
        return this.year;
    }
    
    public int getHalfDecade() {
        return this.halfDecade;
    }

    public int getDecade() {
        return this.decade;
    }
    
}
    

