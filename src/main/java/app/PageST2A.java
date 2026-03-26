package app;
import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PageST2A implements Handler {

    public static final String URL = "/page2A.html";

    @Override
    public void handle(Context context) throws Exception {
        String html = "<html>";

        html = html + "<head>" +
               "<title>Climate Equipment</title>";

        html = html + "<link rel='stylesheet' type='text/css' href='pageST2A.css' />";
        html = html + "</head>";

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

        html = html + """
            <div class='header'>
                <h1>Weather Station Analytics</h1>
            </div>
        """;

        html = html + """
        <div class='dropdown-header' style='max-height: 66dvh;'>
                <h3>Hello! Please use the filters below to explore state climate similiarities. Click submit to generate your results table.</h3>
        </div>

        <hr style='height: 2px; background-color: black; margin: 30 auto; margin-left: 182px; width: 80%';>

        <form method='get' action='/page2A.html'>

        <div class="dropdown-row">

        <div class="dropdown">
            <label for="dropdown2">State:</label>
            <select name='state' id='state' onchange="showLatDropdown()">
                <option value="" disabled selected hidden>Select State</option>
                <option value='N.S.W.'>New South Wales</option>
                <option value='N.T.'>Northern Territory</option>
                <option value='QLD'>Queensland</option>
                <option value='S.A.'>South Australia</option>
                <option value='TAS'>Tasmania</option>
                <option value='VIC'>Victoria</option>
                <option value='W.A.'>Western Australia</option>
                <option value='A.E.T.'>A.E.T</option>
                <option value='A.A.T.'>A.A.T</option> 
            </select>
        </div>
       
        <div class="dropdown">
                <label for="dropdown1">Start Latitude:</label>
                <input type='number' id='latitude' name='latitude' placeholder='Eg. -43'><br><br>
        </div>


        <div class="dropdown">
                <label for="dropdown1">End Latitude:</label>
                <input type='number' id='endlatitude' name='endlatitude' placeholder='Eg. -40'><br><br>
        </div>

        <div class='dropdown'>
            <label for='metric'>Metric:</label>
            <select name='metric' id='metric'>
                <option value="" disabled selected hidden>Select Metric</option>
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

        <div class="submit-container">
        <button type='submit' class='btn btn-primary'>Submit</button>
        </div>
             
        """;
       
        html = html + "</div>";

        html = html + "<hr style='height: 2px; background-color: black; width: 100%;'>";

            String state = context.queryParam("state");
            String startLat = context.queryParam("latitude");
            String endLat = context.queryParam("endlatitude");
            String metric = context.queryParam("metric");
   
            if (startLat != null && endLat != null && metric != null && state != null) {
                String resultsTable1 = sqlQuery(state, startLat, endLat, metric);
                String resultsTable2 = sqlQuery2(state, startLat, endLat, metric);

                if (state.equals("TAS")) {
                    state = "Tasmania";
                }
                else if (state.equals("N.S.W.")) {
                    state = "New South Wales";
                }
                else if (state.equals("N.T.")) {
                    state = "Northern Territory";
                }
                else if (state.equals("QLD")) {
                    state = "Queensland";
                }
                else if (state.equals("S.A.")) {
                    state = "South Australia";
                }
                else if (state.equals("VIC")) {
                    state = "Victoria";
                }
                else if (state.equals("W.A.")) {
                    state = "Western Australia";
                }
                html += "<div class='tableContainer'><h3>" + state + "</h3><table>" + resultsTable1 + "</table></div>";
                html += "<div class='tableContainer'><h3>" + state + "</h3><table>" + resultsTable2 + "</table></div>";
            } else {
                html += "<div class='tableContainer'><h3>Please select filters</h3></div>";
            }

        html += "</body></html>";

        html = html + "<div>";

        html = html + "</div>";

        html = html + "<div style='padding:2dvh;'></div>";

        html = html + "</div>";

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

        html = html + "</body>" + "</html>";

        context.html(html);    
    }

    /* TABLE 1 */
    public String sqlQuery(String state, String startLat, String endLat, String metric) {
        JDBCConnection jdbc = new JDBCConnection();
        String html = "";
        double lat1 = Double.parseDouble(startLat);
        double lat2 = Double.parseDouble(endLat);

        ArrayList<WeatherStation> weatherStation = jdbc.getWeatherStation(state, lat1, lat2, metric);

        html = html + """
                        <tr>
                           <th>Site</th>
                           <th>Name</th>
                           <th>Region</th>
                           <th>Latitude</th>
                           </tr>
                        """;
                       
            for(WeatherStation ws : weatherStation) {
            html = html + "<td>" + ws.getSite() + "</td>";
            html = html + "<td>" + ws.getName() + "</td>";
            html = html + "<td>" + ws.getRegion() + "</td>";
            html = html + "<td>" + ws.getLatitude() + "</td>";
            html = html + "</tr>";
        }    
        return html;
    }

    /* TABLE 2 */
    public String sqlQuery2(String state, String startLat, String endLat, String metric) {
        JDBCConnection jdbc = new JDBCConnection();
        String html = "";
        double lat1 = Double.parseDouble(startLat);
        double lat2 = Double.parseDouble(endLat);

        ArrayList<Table2ST2A> table2 = jdbc.getTableTwo(state, lat1, lat2, metric);

            html += "<tr>";
            html += "<th>Region</th>";
            html += "<th>Number Weather Stations</th>";
            html += "<th>" + metric + "</th>";
            html += "</tr>";
                       
            for(Table2ST2A t2 : table2) {
            html = html + "<td>" + t2.getRegion() + "</td>";
            html = html + "<td>" + t2.getStationCount() + "</td>";
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
            else if (metric.equals("Humidity")) {
                unit = "%";
            }   
            else if (metric.equals("Sunshine")) {
                unit = " Hours";
            }       
            else if (metric.equals("Okta")) {
                unit = " Oktas";
            }            
            else {
                unit = ""; 
            }           
            html = html + "<td>" + t2.getMetric() + unit + "</td>";
            html = html + "</tr>";
        }    
        return html;
    }
}