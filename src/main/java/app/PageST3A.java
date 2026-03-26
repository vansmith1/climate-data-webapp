package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST3A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Climate Equipment</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='pageST2A.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        html = html + """
                <div class='topnav'>
                    <a>
                        <img src='logo.png' class='top-image' alt='RMIT logo' height='40'>
                    </a>
                    <a>
                        <img src='signal-icon.png' class='signal-image' alt='SIGNAL' height='40'>
                    </a>
                    <a href='mission.html' class='infoButton'>
                        <img src='i-icon.png' class='i-image' alt='INFORMATION' height='40'>    
                    </a>
                </div>
                """;
                
        // This uses a Java v15+ Text Block
        html = html + """
             <div class='sidenav'>
                <a href='/'>
                    <img src="home-icon.png" alt="home icon" width="50">
                </a>
                <a href='mission.html'>Our Mission</a>
		        <a href="equipment.html">Dataset Overview</a>
                <a href='page2A.html'>Climate by Station</a>
                <a href='page2B.html'>Climate by Metric</a>
                <a href='page2C.html'>Climate Data Quality</a>
                <a href='page3A.html'>Station Similarities</a>
                <a href='page3B.html'>Metric Similarities</a>
                <a href='page3C.html'>Metric Impacts</a>
            </div>
        """;

        html = html + "<div class='main'>";

        html = html + "<div class='leftContainer'>";

        // Add header content block
        html = html + """
            <div class='header'> 
                <h1>Weather Station Similiarities</h1>
            </div>
        """;

        html = html + """
        <div class='dropdown-header' style='max-height: 66dvh;'>
                <h3>Hello! Please use the filters below to explore state climate information. Click submit to generate your results table.</h3>
        </div>

        <hr style='height: 2px; background-color: black; margin: 30 auto; margin-left: 182px; width: 80%';>

        <form method='get' action='/page3A.html'>

        <div class="dropdown-row">
    
        <div class="dropdownDate">
            <label for='startDateOne'>Start Date One:</label>
            <input type='date' id='startDateOne' name='startDateOne' min='1907-01-01' max='2020-12-31'>
        </div>

        <div class="dropdownDate">
            <label for='endDateOne'>End Date One:</label>
            <input type='date' id='endDateOne' name='endDateOne' min='1970-01-01' max='2020-12-31'> 
        </div>

        <div class="dropdownDate">
            <label for='startDateTwo'>Start Date Two:</label>
            <input type='date' id='startDateTwo' name='startDateTwo' min='1907-01-01' max='2020-12-31'>
        </div>

        <div class="dropdownDate">
            <label for='endDateTwo'>End Date Two:</label>
            <input type='date' id='endDateTwo' name='endDateTwo' min='1970-01-01' max='2020-12-31'> 
        </div>
        </div>

        <div class="dropdown-row">

        <div class="dropdown">
                <label for="dropdown1">Reference station:</label>
                <input type='text' id='refStation' name='refStation' placeholder='Eg. Essendon Airport'>
            </select>
        </div>

        <div class="dropdown">
                <label for="dropdown1">No. of Stations:</label>
                <input type='number' id='numStations' name='numberStations' placeholder='Eg. 3'>
        </div>

        <div class='dropdown'>
            <label for='metric'>Metric:</label>
            <select name='metric' id='metric'>
                <option value=>Select Metric</option>
                <option value='Precipitation'>Precipitation</option>
                <option value='Evaporation'>Evaporation</option>
                <option value='MaxTemp'>Max Temperature</option>
                <option value='MinTemp'>Min Temperature</option>
                <option value='Humid'>Humidity</option>
                <option value='Sunshine'>Sunshine</option>
                <option value='Okta'>Okta</option>
            </select>
        </div>
        </div>

        <br><br>
        <div class="submit-container">
        <button type='submit' class='btn btn-primary'>Submit</button>
        </div>

        """;

        html = html + "</div>";

        html = html + "<hr style='height: 2px; background-color: black; width: 100%; margin: 60px 0 20px;'>";
        
            String startDateOne = context.queryParam("startDateOne"); 
            String endDateOne = context.queryParam("endDateOne"); 
            String startDateTwo = context.queryParam("startDateTwo"); 
            String endDateTwo = context.queryParam("endDateTwo"); 
            String refStation = context.queryParam("refStation"); 
            String numberStations = context.queryParam("numberStations"); 
            String metric = context.queryParam("metric");

            if (startDateOne == null || endDateOne == null || startDateTwo == null || endDateTwo == null || refStation == null || numberStations == null || metric == null) {
                html += "<div class='tableContainer'><h3>Please select filters</h3></div>";
            }
            else if (startDateOne != null && endDateOne != null && startDateTwo != null && endDateTwo != null && refStation != null && numberStations != null && metric != null) {
                String resultsTable = sqlQuery(startDateOne, endDateOne, startDateTwo, endDateTwo, refStation, numberStations, metric);
                html += "<div class='tableContainer'><h3>" + refStation + "</h3><table>" + resultsTable + "</table></div>";
            }
      
        html += "</body></html>";

        //end leftContainer
        html = html + "</div>";

        //middle gap between containers
        html = html + "<div style='padding:2dvh;'></div>";

        //main class div end
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <p>COSC2803 - Studio Project Starter Code (ACC-Apr2025)</p>
            <div class="footer-grid">
                <div class="footer-box">
                    <p>Website authors:</p>
                    <p>Vanessa Smith</p>
                    <p>PRIVATE</p>
                    <p>PRIVATE</p>
                </div>
                <div class="footer-box">
                    <p>Page links:</p>
                    <p><a href='mission.html'>Our Mission</a></p>
                    <p><a href="equipment.html">Dataset Overview</a></p>
                    <p><a href='page2A.html'>Focused View by Weather Station</a></p>
                    <p><a href='page2B.html'>Focused View by Climate Metric</a></p>
                    <p><a href='page2C.html'>Climate Data Quality</a></p>
                    <p><a href='page3A.html'>Weather Station Similiarities</a></p>
                    <p><a href='page3B.html'>Climate Metric Similiarities</a></p>
                    <p><a href='page3C.html'>Metric Impact on Other Metrics</a></p>
                </div>
                <div class="footer-box">
                    <p>Any issues? Feel free to email any of the creators at:</p>
                    <p>S4091675@student.rmit.edu.au</p>
                    <p>PRIVATE@student.rmit.edu.au</p>
                    <p>PRIVATE@student.rmit.edu.au</p>
                    <p>Or send feedback to our:</p>
                    <p><Review Form</p>
                </div>
            </div>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public String sqlQuery(String startDateOne, String endDateOne, String startDateTwo, String endDateTwo, String refStation, String numStations, String metric) { 
        JDBCConnection jdbc = new JDBCConnection(); 
        String html = ""; 
        int numberStations = Integer.parseInt(numStations); 

        ArrayList<Table1ST3A> table3 = jdbc.getTableThree(startDateOne, endDateOne, startDateTwo, endDateTwo, refStation, numberStations, metric); 

            html += "<tr>";
            html += "<th>Weather Station</th>";
            String metricName = "";
                if (metric.equals("MaxTemp")) {
                    metricName = "Max Temperature";
                }
                else if (metric.equals("MinTemp")) {
                    metricName = "Min Temperature";
                }
                else if (metric.equalsIgnoreCase("OKTA")) {
                    metricName = "Okta";
                }
                else if (metric.equalsIgnoreCase("HUMID")) {
                    metricName = "Humidity";
                } 
                else {
                    metricName = metric;
                }

            html += "<th>Average " + metricName + " (Time Range One)</th>";
            html += "<th>Average " + metricName + " (Time Range Two)</th>";
            html += "<th>% Change</th>";
            html += "<th>Difference From " + refStation + "</th>";
            html += "</tr>";
                        
            for(Table1ST3A t3 : table3) { 
            html = html + "<td>" + t3.getStationName() + "</td>"; 
            String unit = "";
            if (metric.equals("MaxTemp")) {
                unit = "&degC";
            }
            else if (metric.equals("MinTemp")) {
                unit = "&degC";
            }
            else if (metric.equals("Precipitation")) {
                unit = "mm";
            }
            else if (metric.equals("Evaporation")) {
                unit = "mm";
            }    
            else if (metric.equalsIgnoreCase("HUMID")) {
                unit = "%";
            }   
            else if (metric.equals("Sunshine")) {
                unit = " Hours";
            }       
            else if (metric.equalsIgnoreCase("OKTA")) {
                unit = " Oktas";
            }            
            else {
                unit = ""; 
            }           
            html = html + "<td>" + t3.getAveMetricOne() + unit + "</td>"; 
            html = html + "<td>" + t3.getAveMetricTwo() + unit + "</td>"; 
            html = html + "<td>" + t3.getChange() + "%</td>";
            html = html + "<td>" + t3.getDifference() + "%</td>"; 
            html = html + "</tr>"; 
        }    
        return html;
    }

}
