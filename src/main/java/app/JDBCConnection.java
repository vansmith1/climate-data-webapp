package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.Comparator;
import java.util.Arrays;
import javax.management.Query;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 * @editor David Eccles, 2025 email: david.eccles@rmit.edu
 */

public class JDBCConnection {

    // Name of database file (contained in database folder)
    public static final String DATABASE = "jdbc:sqlite:database/climate.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the Flag Descriptions
     */
    public ArrayList<FLAG> getFlags() {
        // Create the ArrayList of FlagQuality objects to return
        // Create an array called flags
        ArrayList<FLAG> flags = new ArrayList<FLAG>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC database
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            // put in a timeout incase the db is not running
            statement.setQueryTimeout(30);

            // The SQL Query to be executed 
            String query = "SELECT * FROM FlagQuality";
            
            // Put the SQL results into a result set
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String flagtype     = results.getString("flag");
                String description  = results.getString("description");

                // Create an FLAG Object
                FLAG flagsObj = new FLAG(flagtype, description);

                // Add the FLAG object to the flags array
                flags.add(flagsObj);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the countries
        return flags;
    }

    //get all student names-james
    public String[] getStudentNames() {

        String[] students = new String[3];
        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query           
            String query = "SELECT DISTINCT sname FROM student_names";
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            int i = 0;
            while (results.next()) {
                // student names
                students[i] = results.getString("sname");
                ++i;
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
         // Finally we return all of the students        
        return students;
    }
    //student aquirer-james
    public String getStudentNumber(String sname) {
        String studentnum = "";

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM student_names WHERE sname = '" + sname + "'";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                studentnum= results.getString("snumb") + " and level is: " + results.getString("level");
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return studentnum;
    }

    //get all persona names-james
    public String[] getPersonaNames() {

        String[] personas = new String[3];
        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query           
            String query = "SELECT DISTINCT name FROM personas";
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            int i = 0;
            while (results.next()) {
                // student names
                personas[i] = results.getString("name");
                ++i;
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
         // Finally we return all of the students        
        return personas;
    }
    //climate metric getter
    public ArrayList<climateMetrics> getClimateMetric(String metric, int id1, int id2, String startDate, String endDate) {
        ArrayList<climateMetrics> metricList = new ArrayList<climateMetrics>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT location, DMY," + metric + " FROM StationReport WHERE location >= '" + id1 + "' AND location <= '" + id2 + "' AND DMY >= '" + startDate + "' AND DMY <= '" + endDate + "';";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                climateMetrics metrics = new climateMetrics();

                metrics.metric = results.getString(metric);
                metrics.location = results.getInt("Location");
                metrics.date = results.getString("DMY");

                metricList.add(metrics);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the metrics
        return metricList;
    }
    //grouped climate metric
    public ArrayList<groupedClimateMetrics> getGroupedClimateMetric(String metric, int id1, int id2, String startDate, String endDate, String groupByDate, String groupByLocation, String avgOrSum, String focus) {
        ArrayList<groupedClimateMetrics> metricList = new ArrayList<groupedClimateMetrics>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String metricSaver = metric;
            if(metric.equals("Humid")){
                metric = "round(((Humid00 + Humid03 + Humid06 + Humid09 + Humid15 + Humid18 + Humid21)/7), 2)";
            }
            if(metric.equals("Okta")){
                metric = "round(((Okta00 + Okta03 + Okta06 + Okta09 + Okta15 + Okta18 + Okta21)/7), 2)";
            }
            

            
            // The Query
            String query = "";
            
            if(focus.equals("Location")){
                query = "SELECT "+ groupByLocation +", DMY, round(" + avgOrSum + "(" + metric + "), 2) AS AVG FROM StationReport JOIN Location JOIN datetime WHERE location = site AND DMY = date AND DMY >= '" + startDate + "' AND DMY <= '" + endDate + "' GROUP BY state ORDER BY site;";
                System.out.println(query);         
            }
            if(focus.equals("Date")){
                query = "SELECT location, " + groupByDate + ", round(" + avgOrSum + "(" + metric + "), 2) AS AVG FROM StationReport JOIN Location JOIN datetime WHERE location = site AND DMY = date AND location >= '" + id1 + "' AND location <= '" + id2 + "' AND DMY >= '" + startDate + "' AND DMY <= '" + endDate + "' GROUP BY " + groupByDate + " ORDER BY date;";
                System.out.println(query);
            }
            if(focus.equals("Both")) {
                query = "SELECT "+ groupByLocation +", " + groupByDate + ", round(" + avgOrSum + "(" + metric + "), 2) AS AVG FROM StationReport JOIN Location JOIN datetime WHERE location = site AND DMY = date AND DMY >= '" + startDate + "' AND DMY <= '" + endDate + "' GROUP BY " + groupByDate + ", " + groupByLocation + " ORDER BY site;";
                System.out.println(query);
            }
            
            metric = metricSaver;
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                groupedClimateMetrics metrics = new groupedClimateMetrics();
                
                metrics.metricTotal = results.getString(avgOrSum);
                if(focus.equals("Location")){
                    metrics.stateRange = results.getString(groupByLocation);
                }
                if(focus.equals("Date")){
                    metrics.timeRange = results.getString(groupByDate);
                }
                if(focus.equals("Both")){
                    metrics.timeRange = results.getString(groupByDate);
                    metrics.stateRange = results.getString(groupByLocation);
                }
                

                metricList.add(metrics);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        // Finally we return all of the metrics
        return metricList;
    }

        public ArrayList<WeatherStation> getWeatherStation(String state, double startLat, double endLat, String metric) {
            ArrayList<WeatherStation> weatherStations = new ArrayList<WeatherStation>();

            Connection connection = null;

            try {
                connection = DriverManager.getConnection(JDBCConnection.DATABASE);

                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                String query = "SELECT DISTINCT SITE, NAME, REGION, LAT FROM LOCATION JOIN STATIONREPORT ON STATIONREPORT.LOCATION = LOCATION.SITE WHERE STATE = '" + state + "' AND CAST(LAT AS REAL) BETWEEN " + startLat + " AND " + endLat + " ORDER BY LAT DESC;";
                System.out.println(query);
                
                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    int site = results.getInt("site");
                    String name = results.getString("name");
                    String region = results.getString("region");
                    double latitude = results.getDouble("lat");

                    WeatherStation weatherStation = new WeatherStation(site, name, region, latitude);
                    weatherStations.add(weatherStation); 
                }

                statement.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }

            return weatherStations;
        }


        public ArrayList<Table2ST2A> getTableTwo(String state, double startLat, double endLat, String metric) {
            ArrayList<Table2ST2A> table2 = new ArrayList<Table2ST2A>();

            Connection connection = null;

            try {
                connection = DriverManager.getConnection(JDBCConnection.DATABASE);

                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                if (metric.equalsIgnoreCase("OKTA")) {
                    metric = "(OKTA00 + OKTA03 + OKTA06 + OKTA09 + OKTA12 + OKTA15 + OKTA18 + OKTA21) / 8.0";
                }
                if (metric.equalsIgnoreCase("HUMID")) {
                    metric = "(HUMID00 + HUMID03 + HUMID06 + HUMID09 + HUMID12 + HUMID15 + HUMID18 + HUMID21) / 8.0";
                }

                String query = "SELECT REGION, COUNT(DISTINCT SITE) AS numberStations, ROUND(AVG(" + metric + "), 1) AS metricValue FROM LOCATION JOIN STATIONREPORT ON STATIONREPORT.LOCATION = LOCATION.SITE WHERE STATE = '" + state + "' AND CAST(LAT AS REAL) BETWEEN '" + startLat + "' AND '" + endLat + "' AND " + metric + " IS NOT NULL GROUP BY REGION ORDER BY numberStations DESC;";
                System.out.println(query); 
                
                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    String region = results.getString("REGION");
                    int stationCount = results.getInt("numberStations");
                    double metricVal = results.getDouble("metricValue");

                    Table2ST2A tableTwo = new Table2ST2A(region, stationCount, metricVal);
                    table2.add(tableTwo);
                }

                statement.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }

            return table2;
        }

        public ArrayList<Table1ST3A> getTableThree(String startDateOne, String endDateOne, String startDateTwo, String endDateTwo, String refStation, int numStations, String metric) {
            ArrayList<Table1ST3A> table3 = new ArrayList<Table1ST3A>();

            Connection connection = null;

            try {
                connection = DriverManager.getConnection(JDBCConnection.DATABASE);

                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                if (metric.equalsIgnoreCase("OKTA")) {
                    metric = "(OKTA00 + OKTA03 + OKTA06 + OKTA09 + OKTA12 + OKTA15 + OKTA18 + OKTA21) / 8.0";
                }
                else if (metric.equalsIgnoreCase("HUMID")) {
                    metric = "(HUMID00 + HUMID03 + HUMID06 + HUMID09 + HUMID12 + HUMID15 + HUMID18 + HUMID21) / 8.0";
                } 
                
            String query = "SELECT NAME AS 'stationName', " +
                "ROUND(AVG(CASE WHEN DATE BETWEEN '" + startDateOne + "' AND '" + endDateOne + "' THEN " + metric + " END), 1) AS 'metricOne', " +
                "ROUND(AVG(CASE WHEN DATE BETWEEN '" + startDateTwo + "' AND '" + endDateTwo + "' THEN " + metric + " END), 1) AS 'metricTwo', " +
                "ROUND((AVG(CASE WHEN DATE BETWEEN '" + startDateTwo + "' AND '" + endDateTwo + "' THEN " + metric + " END) - AVG(CASE WHEN DATE BETWEEN '" + startDateOne + "' AND '" + endDateOne + "' THEN " + metric + " END)) / AVG(CASE WHEN DATE BETWEEN '" + startDateOne + "' AND '" + endDateOne + "' THEN " + metric + " END) * 100, 2) AS 'percentChange', " +
                "ROUND(((AVG(CASE WHEN DATE BETWEEN '" + startDateTwo + "' AND '" + endDateTwo + "' THEN " + metric + " END) - AVG(CASE WHEN DATE BETWEEN '" + startDateOne + "' AND '" + endDateOne + "' THEN " + metric + " END)) / AVG(CASE WHEN DATE BETWEEN '" + startDateOne + "' AND '" + endDateOne + "' THEN " + metric + " END) * 100) - " +
                "(SELECT " +
                " (AVG(CASE WHEN DATE BETWEEN '" + startDateTwo + "' AND '" + endDateTwo + "' THEN " + metric + " END) - AVG(CASE WHEN DATE BETWEEN '" + startDateOne + "' AND '" + endDateOne + "' THEN " + metric + " END)) / AVG(CASE WHEN DATE BETWEEN '" + startDateOne + "' AND '" + endDateOne + "' THEN " + metric + " END) * 100 " +
                " FROM LOCATION JOIN STATIONREPORT ON STATIONREPORT.LOCATION = LOCATION.SITE JOIN DATETIME ON DATETIME.DATE = STATIONREPORT.DMY " +
                " WHERE UPPER(NAME) = UPPER('" + refStation + "')" +
                "), 2) AS diffFromRef " +
                "FROM LOCATION JOIN STATIONREPORT ON STATIONREPORT.LOCATION = LOCATION.SITE JOIN DATETIME ON DATETIME.DATE = STATIONREPORT.DMY " +
                "GROUP BY SITE " +
                "HAVING AVG(CASE WHEN DATE BETWEEN '" + startDateOne + "' AND '" + endDateOne + "' THEN " + metric + " END) IS NOT NULL AND " +
                "AVG(CASE WHEN DATE BETWEEN '" + startDateTwo + "' AND '" + endDateTwo + "' THEN " + metric + " END) IS NOT NULL AND " +
                "AVG(CASE WHEN DATE BETWEEN '" + startDateOne + "' AND '" + endDateOne + "' THEN " + metric + " END) != 0 " +
                "ORDER BY UPPER(NAME) = UPPER('" + refStation + "') DESC, ABS(diffFromRef) ASC " +
                "LIMIT " + numStations + ";";
                
                System.out.println(query); 

                ResultSet results = statement.executeQuery(query);
                
                while (results.next()) {
                    String stationName = results.getString("stationName");
                    double aveMetric1 = results.getDouble("metricOne");
                    double aveMetric2 = results.getDouble("metricTwo");
                    double changePer = results.getDouble("percentChange");
                    double diffReference = results.getDouble("diffFromRef");

                    Table1ST3A tableThree = new Table1ST3A(stationName, aveMetric1, aveMetric2, changePer, diffReference);
                    table3.add(tableThree);
                }

                statement.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }

            return table3;
        }

    /* Andy's Code */
    //get dataset metadata
    public ArrayList<metadataObj> getDatasetMetadata(String dataset) {
        ArrayList<metadataObj> datasetMetadata = new ArrayList<metadataObj>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM " + dataset + ";";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                metadataObj metadataObj_1 = new metadataObj();

                metadataObj_1.field = results.getString("Field");
                metadataObj_1.description = results.getString("Description");

                datasetMetadata.add(metadataObj_1);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the metrics
        return datasetMetadata;
    }

    // get datatset datetime
    public ArrayList<dateTimeObj> getDatasetDateTime() {
        ArrayList<dateTimeObj> datasetDateTime = new ArrayList<dateTimeObj>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM DateTime LIMIT 500;";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                dateTimeObj dateTimeObj_1 = new dateTimeObj();

                dateTimeObj_1.date = results.getString("Date");
                dateTimeObj_1.dayOfWeek = results.getString("DayOfWeek");
                dateTimeObj_1.week = results.getInt("Week");
                dateTimeObj_1.month = results.getString("Month");
                dateTimeObj_1.monthNum = results.getInt("MonthNum");
                dateTimeObj_1.quarter = results.getString("Quarter");
                dateTimeObj_1.half = results.getString("Half");
                dateTimeObj_1.year = results.getInt("Year");
                dateTimeObj_1.halfDecade = results.getInt("HalfDecade");
                dateTimeObj_1.decade = results.getInt("Decade");

                datasetDateTime.add(dateTimeObj_1);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the metrics
        return datasetDateTime;
    }

    //get dataset station report
    public ArrayList<stationReportObj> getDatasetStationReport() {
        ArrayList<stationReportObj> datasetStationReport = new ArrayList<stationReportObj>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM StationReport LIMIT 500;";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                stationReportObj stationReportObj_1 = new stationReportObj();

                stationReportObj_1.location = results.getInt("Location");
                stationReportObj_1.DMY = results.getString("DMY");
                stationReportObj_1.precipitation = results.getInt("Precipitation");
                stationReportObj_1.precipQual = results.getString("PrecipQual");
                stationReportObj_1.rainDaysNum = results.getInt("RainDaysNum");
                stationReportObj_1.rainDaysMeasure = results.getInt("RainDaysMeasure");
                stationReportObj_1.evaporation = results.getDouble("Evaporation");
                stationReportObj_1.evapQual = results.getString("EvapQual");
                stationReportObj_1.evapDaysNum = results.getInt("EvapDaysNum");
                stationReportObj_1.maxTemp = results.getDouble("MaxTemp");
                stationReportObj_1.maxTempQual = results.getString("MaxTempQual");
                stationReportObj_1.maxTempDays = results.getInt("MaxTempDays");
                stationReportObj_1.minTemp = results.getDouble("MinTemp");
                stationReportObj_1.minTempQual = results.getString("MinTempQual");
                stationReportObj_1.minTempDays = results.getInt("MinTempDays");
                stationReportObj_1.humid00 = results.getInt("Humid00");
                stationReportObj_1.humid00Qual = results.getString("Humid00Qual");
                stationReportObj_1.humid03 = results.getInt("Humid03");
                stationReportObj_1.humid03Qual = results.getString("humid03Qual");
                stationReportObj_1.humid06 = results.getInt("Humid06");
                stationReportObj_1.humid06Qual = results.getString("humid06Qual");
                stationReportObj_1.humid09 = results.getInt("Humid09");
                stationReportObj_1.humid09Qual = results.getString("humid09Qual");
                stationReportObj_1.humid12 = results.getInt("Humid12");
                stationReportObj_1.humid12Qual = results.getString("humid12Qual");
                stationReportObj_1.humid15 = results.getInt("Humid15");
                stationReportObj_1.humid15Qual = results.getString("humid15Qual");
                stationReportObj_1.humid18 = results.getInt("Humid18");
                stationReportObj_1.humid18Qual = results.getString("humid18Qual");
                stationReportObj_1.humid21 = results.getInt("Humid21");
                stationReportObj_1.humid21Qual = results.getString("humid21Qual");
                stationReportObj_1.sunshine = results.getDouble("Sunshine");
                stationReportObj_1.sunshineQual = results.getString("SunshineQual");
                stationReportObj_1.okta00 = results.getInt("Okta00");
                stationReportObj_1.okta00Qual = results.getString("Okta00Qual");
                stationReportObj_1.okta03 = results.getInt("Okta03");
                stationReportObj_1.okta03Qual = results.getString("Okta03Qual");
                stationReportObj_1.okta06 = results.getInt("Okta06");
                stationReportObj_1.okta06Qual = results.getString("Okta06Qual");
                stationReportObj_1.okta09 = results.getInt("Okta09");
                stationReportObj_1.okta09Qual = results.getString("Okta09Qual");
                stationReportObj_1.okta12 = results.getInt("Okta12");
                stationReportObj_1.okta12Qual = results.getString("Okta12Qual");
                stationReportObj_1.okta15 = results.getInt("Okta15");
                stationReportObj_1.okta15Qual = results.getString("Okta15Qual");
                stationReportObj_1.okta18 = results.getInt("Okta18");
                stationReportObj_1.okta18Qual = results.getString("Okta18Qual");
                stationReportObj_1.okta21 = results.getInt("Okta21");
                stationReportObj_1.okta21Qual = results.getString("Okta21Qual");

                datasetStationReport.add(stationReportObj_1);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the metrics
        return datasetStationReport;
    }

    //get dataset location
    public ArrayList<locationObj> getDatasetLocation() {
        ArrayList<locationObj> datasetLocation = new ArrayList<locationObj>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Location LIMIT 500;";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                locationObj locationObj_1 = new locationObj();

                locationObj_1.site = results.getInt("Site");
                locationObj_1.name = results.getString("Name");
                locationObj_1.lat = results.getDouble("Lat");
                locationObj_1.longitude = results.getDouble("Long");
                locationObj_1.state = results.getString("State");
                locationObj_1.region = results.getString("Region");

                datasetLocation.add(locationObj_1);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the metrics
        return datasetLocation;
    }

    //page ST2C
    //get table ST2C
    public ArrayList<TableST2C> getDatasetST2C(String filterQualityFlagInput, String filterMetric, String filterDate, String filterStationid, String startDateInput, String endDateInput, String stateInput, int periodInput, String sortInput, String sortAttribute, String flagInput, String metricInput) {
        ArrayList<TableST2C> datasetST2C = new ArrayList<TableST2C>();
        System.out.println("Inputs: " + filterMetric + ", " + filterQualityFlagInput + ", " + metricInput);
    
        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String qualityType = "";
            ArrayList<String> selectedCols = new ArrayList<>();

            String metricCol = "";
            String qualityCol = "";

            if (metricInput != null) {
                switch (metricInput) {
                    case "precipitation":
                        metricCol = "precipitation";
                        qualityCol = "PrecipQual";
                        break;
                    case "evaporation":
                        metricCol = "evaporation";
                        qualityCol = "EvapQual";
                        break;
                    case "maxTemperature":
                        metricCol = "MaxTemp";
                        qualityCol = "MaxTempQual";
                        break;
                    case "minTemperature":
                        metricCol = "MinTemp";
                        qualityCol = "MinTempQual";
                        break;
                    case "humid00":
                        metricCol = "Humid00";
                        qualityCol = "Humid00Qual";
                        break;
                    case "humid03":
                        metricCol = "Humid03";
                        qualityCol = "Humid03Qual";
                        break;
                    case "humid06":
                        metricCol = "Humid06";
                        qualityCol = "Humid06Qual";
                        break;
                    case "humid09":
                        metricCol = "Humid09";
                        qualityCol = "Humid09Qual";
                        break;
                    case "humid12":
                        metricCol = "Humid12";
                        qualityCol = "Humid12Qual";
                        break;
                    case "humid15":
                        metricCol = "Humid15";
                        qualityCol = "Humid15Qual";
                        break;
                    case "humid18":
                        metricCol = "Humid18";
                        qualityCol = "Humid18Qual";
                        break;
                    case "humid21":
                        metricCol = "Humid21";
                        qualityCol = "Humid21Qual";
                        break;
                    case "sunshine":
                        metricCol = "Sunshine";
                        qualityCol = "SunshineQual";
                        break;
                    case "okta00":
                        metricCol = "Okta00";
                        qualityCol = "Okta00Qual";
                        break;
                    case "okta03":
                        metricCol = "Okta03";
                        qualityCol = "Okta03Qual";
                        break;
                    case "okta06":
                        metricCol = "Okta06";
                        qualityCol = "Okta06Qual";
                        break;
                    case "okta09":
                        metricCol = "Okta09";
                        qualityCol = "Okta09Qual";
                        break;
                    case "okta12":
                        metricCol = "Okta12";
                        qualityCol = "Okta12Qual";
                        break;
                    case "okta15":
                        metricCol = "Okta15";
                        qualityCol = "Okta15Qual";
                        break;
                    case "okta18":
                        metricCol = "Okta18";
                        qualityCol = "Okta18Qual";
                        break;
                    case "okta21":
                        metricCol = "Okta21";
                        qualityCol = "Okta21Qual";
                        break;
                    default:
                        metricCol = metricInput; // fallback, in case of unknown metricInput
                        qualityCol = "";
                        break;
                }
            }

            if ("true".equals(filterMetric) && metricCol != null && !metricCol.isEmpty()) {
                selectedCols.add(metricCol);
            }

            if ("true".equals(filterDate)) {
                selectedCols.add("DMY");
            }
            if ("true".equals(filterStationid)) {
                selectedCols.add("Location");
            }

            // fallback if no filters selected
            if (selectedCols.isEmpty()) {
                selectedCols.add("DMY");
                if (metricCol != null && !metricCol.isEmpty()) {
                    selectedCols.add(metricCol);
                }
            }

            System.out.println("qualityCol: " + qualityCol + ", flagInput: " + flagInput);

            if (qualityCol != null && !qualityCol.isEmpty()) {
                selectedCols.add(qualityCol);
            }

            ArrayList<String> conditions = new ArrayList<>();

            if (!"allFlags".equalsIgnoreCase(flagInput) && qualityCol != null && !qualityCol.isEmpty()) {
                conditions.add(qualityCol + " = '" + flagInput.toUpperCase() + "'");
            }

            if (startDateInput != null && !startDateInput.isEmpty() &&
                endDateInput != null && !endDateInput.isEmpty()) {
                conditions.add("date(DMY) >= '" + startDateInput + "' AND date(DMY) <= '" + endDateInput + "'");
            }

            String whereClause = conditions.isEmpty() ? "" : " WHERE " + String.join(" AND ", conditions);


            // The Query
            String query = "SELECT " + String.join(", ", selectedCols) + " FROM StationReport" + whereClause;

            if (sortAttribute != null && !sortAttribute.equals("none")) {
                String sortColumn = "";

                if (sortAttribute.equals("metric")) {
                    sortColumn = metricCol;
                    System.out.println("Sort attribute: " + sortAttribute + ", sort column: " + sortColumn);


                    if ("descending".equals(sortInput)) {
                        query += " ORDER BY CASE WHEN " + sortColumn + " IS NULL OR " + sortColumn + " = 0 THEN 1 ELSE 0 END, " + sortColumn + " DESC";
                    } else {
                        query += " ORDER BY " + sortColumn + " ASC";
                    }

                } else {
                    if ("date".equalsIgnoreCase(sortAttribute)) {
                        sortColumn = "DMY";
                    } else if ("quality".equalsIgnoreCase(sortAttribute)) {
                        sortColumn = qualityCol;
                    } else if ("metric".equalsIgnoreCase(sortAttribute)) {
                        sortColumn = metricCol;
                    } else {
                        sortColumn = sortAttribute;
                    }
                    System.out.println("Sort attribute: " + sortAttribute + ", sort column: " + sortColumn);


                    if ("DMY".equalsIgnoreCase(sortColumn)) {
                        System.out.println("Sorting by DMY column");
                        query += " ORDER BY DMY";
                    } else {
                        System.out.println("Sorting by " + sortColumn);
                        query += " ORDER BY " + sortColumn;
                    }
                    query += " " + ("descending".equals(sortInput) ? "DESC" : "ASC");


                }
            }


            query += " LIMIT 2000;";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                TableST2C row = new TableST2C();

                if (selectedCols.contains("DMY")) {
                    row.date = results.getString("DMY");
                }
                if (selectedCols.contains("Location")) {
                    row.location = results.getString("Location");
                }
                if (selectedCols.contains(metricCol)) {
                    row.selectedMetric = results.getDouble(metricCol);
                }
                if (selectedCols.contains(qualityCol)) {
                    row.selectedMetricQuality = results.getString(qualityCol);
                }

                datasetST2C.add(row);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the metrics
        return datasetST2C;
    }

    public Map<String, Integer> getCountDatasetST2C(String metricInput, String stateInput, int halfdecadeInput) {
        Map<String, Integer> counts = new HashMap<>();
        String qualityCol = "";

        if (metricInput != null) {
                switch (metricInput) {
                    case "precipitation": 
                        qualityCol = "PrecipQual"; break;
                    case "evaporation":
                        qualityCol = "EvapQual";
                        break;
                    case "maxTemperature":
                        qualityCol = "MaxTempQual";
                        break;
                    case "minTemperature":
                        qualityCol = "MinTempQual";
                        break;
                    case "humid00":
                        qualityCol = "Humid00Qual";
                        break;
                    case "humid03":
                        qualityCol = "Humid03Qual";
                        break;
                    case "humid06":
                        qualityCol = "Humid06Qual";
                        break;
                    case "humid09":
                        qualityCol = "Humid09Qual";
                        break;
                    case "humid12":
                        qualityCol = "Humid12Qual";
                        break;
                    case "humid15":
                        qualityCol = "Humid15Qual";
                        break;
                    case "humid18":
                        qualityCol = "Humid18Qual";
                        break;
                    case "humid21":
                        qualityCol = "Humid21Qual";
                        break;
                    case "sunshine":
                        qualityCol = "SunshineQual";
                        break;
                    case "okta00":
                        qualityCol = "Okta00Qual";
                        break;
                    case "okta03":
                        qualityCol = "Okta03Qual";
                        break;
                    case "okta06":
                        qualityCol = "Okta06Qual";
                        break;
                    case "okta09":
                        qualityCol = "Okta09Qual";
                        break;
                    case "okta12":
                        qualityCol = "Okta12Qual";
                        break;
                    case "okta15":
                        qualityCol = "Okta15Qual";
                        break;
                    case "okta18":
                        qualityCol = "Okta18Qual";
                        break;
                    case "okta21":
                        qualityCol = "Okta21Qual";
                        break;
                    default:
                        qualityCol = "";
                        break;
                }
        }

        if (qualityCol.isEmpty()) {
            return counts;  // nothing to count
        }

        try (Connection conn = DriverManager.getConnection(JDBCConnection.DATABASE);
            Statement stmt = conn.createStatement()) {
            
            String sql = """
                SELECT %s, COUNT(*) AS count
                FROM StationReport
                JOIN Location ON StationReport.Location = Location.site
                JOIN DateTime ON StationReport.DMY = DateTime.date
                WHERE %s IS NOT NULL
            """.formatted(qualityCol, qualityCol);

            if (stateInput != null && !stateInput.isEmpty()) {
                sql += " AND Location.state = '" + stateInput + "'";
            }

            if (halfdecadeInput > 0) {
                sql += " AND DateTime.halfdecade = '" + halfdecadeInput + "'";
            }

            sql += " GROUP BY " + qualityCol + " ORDER BY count DESC;";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String flag = rs.getString(1); // first column = qualityCol
                if (flag == null || flag.isBlank()) {
                    flag = "Unknown";
                }
                counts.put(flag, rs.getInt("count"));
 
            }
            
            System.out.println("SQL: " + sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return counts;

    }

    // get different categories of time (attributes)
    public ArrayList<String> getDateTimeGroupings() {
        ArrayList<String> dateTimeGroupings = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC database
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            
            Statement statement = connection.createStatement();
            // put in a timeout incase the db is not running
            statement.setQueryTimeout(30);

            // The SQL Query to be executed 
            String query = "SELECT name FROM pragma_table_info('DateTime');";
            
            // Put the SQL results into a result set
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String groupingName = results.getString("name");

                dateTimeGroupings.add(groupingName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        
        return dateTimeGroupings;
    }

    // get different stations
    public Map<String, String> getAllStations() {
        Map<String, String> stationsMap = new LinkedHashMap<>(); // Keeps insertion order

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC database
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            
            Statement statement = connection.createStatement();
            // put in a timeout incase the db is not running
            statement.setQueryTimeout(30);

            // The SQL Query to be executed 
            String query = "SELECT Site, Name FROM Location ORDER BY Name;";
            
            // Put the SQL results into a result set
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String site = results.getString("Site");
                String name = results.getString("Name");
                stationsMap.put(site, name);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        
        return stationsMap;
    }

    // get different period
    public ArrayList<Integer> getAllHalfDecades() {
        ArrayList<Integer> listPeriods = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC database
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            
            Statement statement = connection.createStatement();
            // put in a timeout incase the db is not running
            statement.setQueryTimeout(30);

            // The SQL Query to be executed 
            String query = "SELECT DISTINCT HalfDecade FROM DateTime ORDER BY HalfDecade;";
            
            // Put the SQL results into a result set
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int period = results.getInt("HalfDecade");

                listPeriods.add(period);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        
        return listPeriods;
    }

    //get table ST3C
    public ArrayList<TableST3C> getDatasetST3C(int period, String station, String metric) {
        ArrayList<TableST3C> tableST3C = new ArrayList<TableST3C>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC database
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            
            Statement statement = connection.createStatement();
            // put in a timeout incase the db is not running
            statement.setQueryTimeout(30);

            // The SQL Query to be executed 
            String query = "SELECT ";

                //metric column will be separately after while loop, because of dependencies on the main metric
                query = query + "'" + capitalize(metric) + "'" + " AS [Metric Name], ";

                //total columns
                String str = String.valueOf(period);
                String firstHalf = str.substring(0, str.length() / 2);
                String secondHalf = str.substring(str.length() / 2);

                int year1 = Integer.parseInt(firstHalf);
                int year2 = year1 + 4;
                int year3 = year2 + 1;
                int year4 = Integer.parseInt(secondHalf);

                String oldStart = year1 + "-01-01";
                String oldEnd = year2 + "-12-31";
                String newStart = year3 + "-01-01";
                String newEnd = year4 + "-12-31";

                query = query + "[Total (" + year1 + "-" + year2 + ")], ";
                query = query + "[Total (" + year3 + "-" + year4 + ")], ";

                //change%
                query = query + "ROUND((([Total (" + year3 + "-" + year4 + ")] " + "- " + "[Total (" + year1 + "-" + year2 + ")]" + ") / " + "[Total (" + year1 + "-" + year2 + ")]" + ") * 100, 2) AS Change";

                //correlation
                query = query + ", CASE " +
                    "WHEN (([Total (" + year3 + "-" + year4 + ")] - [Total (" + year1 + "-" + year2 + ")]) / [Total (" + year1 + "-" + year2 + ")] * 100) > 0.5 THEN 'Positive correlation' " +
                    "WHEN (([Total (" + year3 + "-" + year4 + ")] - [Total (" + year1 + "-" + year2 + ")]) / [Total (" + year1 + "-" + year2 + ")] * 100) < -0.5 THEN 'Negative correlation' " +
                    "ELSE 'Neutral correlation' END AS \"Metric Trend (correlation)\" ";
                
                query = query + "FROM ( SELECT ";

                    //first total                    
                    if ("humidity".equals(metric)) {
                        query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year1 + "-01-01' AND '" + year2 +"-12-31' AND Location = '" + station + //
                                            "' THEN ROUND((HUMID00 + HUMID03 + HUMID06 + HUMID09 + HUMID12 + HUMID15 + HUMID18 + HUMID21) / 8.0) " + //
                                            "ELSE 0 END)) AS [Total (" + year1 + "-" + year2 + ")], ";
                    } else if ("okta".equals(metric)) {
                        query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year1 + "-01-01' AND '" + year2 +"-12-31' AND Location = '" + station + //
                                            "' THEN ROUND((OKTA00 + OKTA03 + OKTA06 + OKTA09 + OKTA12 + OKTA15 + OKTA18 + OKTA21) / 8.0) " + //
                                            "ELSE 0 END)) AS [Total (" + year1 + "-" + year2 + ")], ";
                    } else {
                        query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year1 + "-01-01' AND '" + year2 +"-12-31' AND Location = '" + station + //
                                            "' THEN " + metric + //
                                            " ELSE 0 END)) AS [Total (" + year1 + "-" + year2 + ")], ";
                    }

                    //second total
                    if ("humidity".equals(metric)) {
                        query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year3 + "-01-01' AND '" + year4 +"-12-31' AND Location = '" + station + //
                                            "' THEN ROUND((HUMID00 + HUMID03 + HUMID06 + HUMID09 + HUMID12 + HUMID15 + HUMID18 + HUMID21) / 8.0) " +//
                                            "ELSE 0 END)) AS [Total (" + year3 + "-" + year4 + ")]";
                    } else if ("okta".equals(metric)) {
                        query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year3 + "-01-01' AND '" + year4 +"-12-31' AND Location = '" + station + //
                                            "' THEN ROUND((OKTA00 + OKTA03 + OKTA06 + OKTA09 + OKTA12 + OKTA15 + OKTA18 + OKTA21) / 8.0) " + //
                                            "ELSE 0 END)) AS [Total (" + year3 + "-" + year4 + ")]";
                    } else {
                        query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year3 + "-01-01' AND '" + year4 +"-12-31' AND Location = '" + station + //
                                            "' THEN " + metric + //
                                            " ELSE 0 END)) AS [Total (" + year3 + "-" + year4 + ")]";
                    }

                query = query + " FROM StationReport" + ") AS sub";

                for (int i = 0; i < getAdditionalRows(period, station, metric).size(); i++) {
                    query = query + getAdditionalRows(period, station, metric).get(i);
                }

                query += ";";
            
            //debug output
            System.out.println("DEBUG SQL QUERY: " + query);
                
            // Put the SQL results into a result set
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                TableST3C row = new TableST3C();

                row.metricName = results.getString("Metric Name");
                
                row.totalHalfDecade1 = results.getString("Total (" + year1 + "-" + year2 + ")");

                row.totalHalfDecade2 = results.getString("Total (" + year3 + "-" + year4 + ")");

                String changeStr = results.getString("Change");
                if (changeStr != null && !changeStr.isEmpty()) {
                    double change = Double.parseDouble(changeStr.trim());
                    if (change > 0) {
                        row.changePercentage = "+" + changeStr;
                    } else if (change < 0) {
                        row.changePercentage = changeStr;
                    } else {
                        row.changePercentage = changeStr;
                    }
                } else {
                    row.changePercentage = "N/A";
                }

                row.metricTrend = results.getString("Metric Trend (correlation)");

                tableST3C.add(row);

            }

            if (tableST3C.size() > 1) {
                TableST3C firstRow = tableST3C.get(0);
                TableST3C max = null;
                TableST3C min = null;
                TableST3C closest = null;

                double closestDiff = Double.MAX_VALUE;

                for (int i = 1; i < tableST3C.size(); i++) {
                    TableST3C row = tableST3C.get(i);
                    if (row.changePercentage.equals("N/A")) continue;

                    double change = Double.parseDouble(row.changePercentage.replace("+", ""));

                    if (max == null || change > Double.parseDouble(max.changePercentage.replace("+", ""))) {
                        max = row;
                    }
                    if (min == null || change < Double.parseDouble(min.changePercentage.replace("+", ""))) {
                        min = row;
                    }
                    if (Math.abs(change) < closestDiff) {
                        closestDiff = Math.abs(change);
                        closest = row;
                    }
                }

                // Keep only first row + 3 selected rows
                ArrayList<TableST3C> filtered = new ArrayList<>();
                filtered.add(firstRow);
                if (max != null) filtered.add(max);
                if (min != null && min != max) filtered.add(min);
                if (closest != null && closest != max && closest != min) filtered.add(closest);

                tableST3C = filtered;
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        return tableST3C;
    }

    public ArrayList<String> getAdditionalRows(int period, String station, String metric) {
        ArrayList<String> additionalQueries = new ArrayList<String>();
        String query = "";

        //total columns
                    String str = String.valueOf(period);
                    String firstHalf = str.substring(0, str.length() / 2);
                    String secondHalf = str.substring(str.length() / 2);

                    int year1 = Integer.parseInt(firstHalf);
                    int year2 = year1 + 4;
                    int year3 = year2 + 1;
                    int year4 = Integer.parseInt(secondHalf);

            ArrayList<String> allMetrics = new ArrayList<>();
                allMetrics.add("precipitation");
                allMetrics.add("evaporation");
                allMetrics.add("maxTemp");
                allMetrics.add("minTemp");
                allMetrics.add("sunshine");
                allMetrics.add("humidity");
                allMetrics.add("okta");

            allMetrics.remove(metric);


            for (int i = 0; i < allMetrics.size(); ++i) {
                String currentMetric = allMetrics.get(i);

                query = query + " UNION ALL ";

                // The SQL Query to be executed 
                query = query + "SELECT ";

                    //metric column will be separately after while loop, because of dependencies on the main metric
                    query = query + "'" + capitalize(currentMetric) + "'" + " AS [Metric Name], ";

                    query = query + "[Total (" + year1 + "-" + year2 + ")], ";
                    query = query + "[Total (" + year3 + "-" + year4 + ")], ";

                    //change%
                    query = query + "ROUND((([Total (" + year3 + "-" + year4 + ")] " + "- " + "[Total (" + year1 + "-" + year2 + ")]" + ") / " + "[Total (" + year1 + "-" + year2 + ")]" + ") * 100, 2) AS Change";

                    //correlation
                    query = query + ", CASE " +
                        "WHEN (([Total (" + year3 + "-" + year4 + ")] - [Total (" + year1 + "-" + year2 + ")]) / [Total (" + year1 + "-" + year2 + ")] * 100) > 0.5 THEN 'Positive correlation' " +
                        "WHEN (([Total (" + year3 + "-" + year4 + ")] - [Total (" + year1 + "-" + year2 + ")]) / [Total (" + year1 + "-" + year2 + ")] * 100) < -0.5 THEN 'Negative correlation' " +
                        "ELSE 'Neutral correlation' END AS \"Metric Trend (correlation)\" ";
                    
                    query = query + "FROM ( SELECT ";

                        //first total                    
                        if ("humidity".equals(currentMetric)) {
                            query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year1 + "-01-01' AND '" + year2 +"-12-31' AND Location = '" + station + //
                                                "' THEN ROUND((HUMID00 + HUMID03 + HUMID06 + HUMID09 + HUMID12 + HUMID15 + HUMID18 + HUMID21) / 8.0) " + //
                                                "ELSE 0 END)) AS [Total (" + year1 + "-" + year2 + ")], ";
                        } else if ("okta".equals(currentMetric)) {
                            query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year1 + "-01-01' AND '" + year2 +"-12-31' AND Location = '" + station + //
                                                "' THEN ROUND((OKTA00 + OKTA03 + OKTA06 + OKTA09 + OKTA12 + OKTA15 + OKTA18 + OKTA21) / 8.0) " + //
                                                "ELSE 0 END)) AS [Total (" + year1 + "-" + year2 + ")], ";
                        } else {
                            query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year1 + "-01-01' AND '" + year2 +"-12-31' AND Location = '" + station + //
                                                "' THEN " + currentMetric + //
                                                " ELSE 0 END)) AS [Total (" + year1 + "-" + year2 + ")], ";
                        }

                        //second total
                        if ("humidity".equals(currentMetric)) {
                            query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year3 + "-01-01' AND '" + year4 +"-12-31' AND Location = '" + station + //
                                                "' THEN ROUND((HUMID00 + HUMID03 + HUMID06 + HUMID09 + HUMID12 + HUMID15 + HUMID18 + HUMID21) / 8.0) " +//
                                                "ELSE 0 END)) AS [Total (" + year3 + "-" + year4 + ")]";
                        } else if ("okta".equals(currentMetric)) {
                            query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year3 + "-01-01' AND '" + year4 +"-12-31' AND Location = '" + station + //
                                                "' THEN ROUND((OKTA00 + OKTA03 + OKTA06 + OKTA09 + OKTA12 + OKTA15 + OKTA18 + OKTA21) / 8.0) " + //
                                                "ELSE 0 END)) AS [Total (" + year3 + "-" + year4 + ")]";
                        } else {
                            query = query + "ROUND(SUM(CASE WHEN DMY BETWEEN '" + year3 + "-01-01' AND '" + year4 +"-12-31' AND Location = '" + station + //
                                                "' THEN " + currentMetric + //
                                                " ELSE 0 END)) AS [Total (" + year3 + "-" + year4 + ")]";
                        }

                    query = query + " FROM StationReport" + ") AS sub";

            additionalQueries.add(query);

            query = "";
            }



        return additionalQueries;
    }

    public ArrayList<TableST3C> get3Datasets(ArrayList<String> queries) {
        ArrayList<TableST3C> threeRows = new ArrayList<>();
            // Setup the variable for the JDBC connection
            Connection connection = null;

            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(JDBCConnection.DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                
                // The Query
                //first Query-gets us our reference
                String query = "";

                for (int i = 0; i < queries.size(); ++i) {
                    query = queries.get(i);
                    System.out.println(query);
                    // Get Result rnd1
                    ResultSet results = statement.executeQuery(query);
                    while(results.next()){
                        TableST3C row = new TableST3C();

                        row.metricName = results.getString("Metric Name");
                
                        row.totalHalfDecade1 = results.getString("Total (" + year1 + "-" + year2 + ")");

                        row.totalHalfDecade2 = results.getString("Total (" + year3 + "-" + year4 + ")");

                        String changeStr = results.getString("Change");
                        if (changeStr != null && !changeStr.isEmpty()) {
                            double change = Double.parseDouble(changeStr.trim());
                            if (change > 0) {
                                row.changePercentage = "+" + changeStr;
                            } else if (change < 0) {
                                row.changePercentage = changeStr;
                            } else {
                                row.changePercentage = changeStr;
                            }
                        } else {
                            row.changePercentage = "N/A";
                        }

                        row.metricTrend = results.getString("Metric Trend (correlation)");
                    
                        threeRows.add(row);
                    }

                }
                
                // Close the statement because we are done with it
                statement.close();
            } catch (SQLException e) {
                // If there is an error, lets just pring the error
                System.err.println(e.getMessage());
            } finally {
                // Safety code to cleanup
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e.getMessage());
                }
            }

        return threeRows;
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    //james code
    //% metric
    public ArrayList<percentageClimateMetrics> getRefClimateMetric(String refMetric, String startDate, String endDate, String timePeriod, int numMetrics){
        ArrayList<percentageClimateMetrics> metricList = new ArrayList<percentageClimateMetrics>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            //first Query-gets us our reference
            String query = "";
            query = "SELECT " + timePeriod + ", round(avg(MaxTemp), 3) AS MaxTemp, round(avg(Precipitation), 3) AS Precipitation, round(avg(RainDaysNum), 3)  AS RainDaysNum, round(avg(RainDaysMeasure), 3) AS RainDaysMeasure, round(avg(Evaporation), 3) AS Evaporation, round(avg(EvapDaysNum), 3) AS EvapDaysNum, round(avg(MaxTempDays), 3) AS MaxTempDays, round(avg(MinTemp), 3) AS MinTemp, round(avg(MinTempDays), 3) AS MinTempDays, round(avg((Humid00 + Humid03 + Humid06 + Humid09 + Humid15 + Humid18 + Humid21)/7), 3) AS Humid, round(avg((Okta00 + Okta03 + Okta06 + Okta09 + Okta15 + Okta18 + Okta21)/7), 3) AS Okta, round(avg(Sunshine), 3) AS Sunshine FROM StationReport JOIN Location JOIN datetime WHERE location = site AND DMY = date AND DMY >= '" + startDate + "' AND DMY <= '" + endDate + "' GROUP BY " + timePeriod + " ORDER BY " + timePeriod + ";";
            System.out.println(query);
            // Get Result rnd1
            ResultSet results = statement.executeQuery(query);
            while(results.next()){
                percentageClimateMetrics refMet = new percentageClimateMetrics();
                refMet.timeRange = results.getString(timePeriod);
                refMet.metricTotal = results.getDouble(refMetric);
                refMet.MaxTemp = results.getDouble("MaxTemp");
                refMet.Precipitation = results.getDouble("Precipitation");
                refMet.RainDaysNum = results.getDouble("RainDaysNum");
                refMet.RainDaysMeasure = results.getDouble("RainDaysMeasure");
                refMet.Evaporation = results.getDouble("Evaporation");
                refMet.EvapDaysNum = results.getDouble("EvapDaysNum");
                refMet.MaxTempDays = results.getDouble("MaxTempDays");
                refMet.MinTemp = results.getDouble("MinTemp");
                refMet.MinTempDays = results.getDouble("MinTempDays");
                refMet.Humid = results.getDouble("Humid");
                refMet.Okta = results.getDouble("Okta");
                refMet.Sunshine = results.getDouble("Sunshine");
            
                metricList.add(refMet);
            }
            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        // Finally we return all of the metrics
        return metricList;
    }

}