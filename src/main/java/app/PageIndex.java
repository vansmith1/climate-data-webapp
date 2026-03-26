package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PageIndex implements Handler {

    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        String html = "<html>";

        html = html + "<head>" +
               "<title>Homepage</title>";

        html = html + "<link rel='stylesheet' type='text/css' href='pageindex.css' />";
        html = html + "</head>";

        html =  html + "<body>";

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

        html = html + """
            <div class='header'>
                <h1>
                    How has Australia's climate changed since 1970?
                </h1>
            </div>
        """;
 
        html = html + """
            <div class='content-grid'>
                <div class="content-box">
                    <img src="calendar.png" alt="Calendar">
                    <h3>Year Range:</h3>
                    <p>1970-2020</p>
                </div>
                <div class="content-box">
                    <img src="temp.png" alt="Temperature">
                    <h3>Lowest Recorded Temperature:</h3>
                    <p>Trangie Research Station AWS - Narromine</p>
                    <p>-47.3&deg</p>
                </div>
                <div class="content-box">
                    <img src="rain.png" alt="Rain">
                    <h3>Highest Recorded Rainfall:</h3>
                    <p>Redesdale - Greater Bendigo</p>
                    <p>930.4mm</p>
                    </div>
            </div>
        """;

        html = html + """
            <div class='topic-content'>
                <h1>Navigate by Topic</h1>
            </div>
        """;

        html = html + """
        <div class="topics">
                <div class="content-box">
                    <img src="highest temp.png" alt="High Temp">
                    <p><a href='page2A.html'>Focused View by Weather Station</a></p>
                </div>
                <div class="content-box">
                    <img src="lowest temp.png" alt="Low Temp">
                    <p><a href='page2B.html'>Focused View by Climate Metric</a></p>
                </div>
                <div class="content-box">
                    <img src="rainfall.png" alt="Rainfall">
                    <p><a href='page2C.html'>Threats to Data Quality</a></p>
                </div>
                <div class="content-box">
                    <img src="raining.png" alt="Rain">
                    <p><a href='page3A.html'>Weather Station Similiarities</a></p>
                </div>
                <div class="content-box">
                    <img src="solar exposure.png" alt="Sun">
                    <p><a href='page3B.html'>Climate Metric Similiarities</a></p>
                </div>
                <div class="content-box">
                    <img src="wind speed.png" alt="Wind">
                    <p><a href='page3C.html'>Metric Impact on other Metrics</a></p>
                </div>
                </div>
        """;

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

        html = html + "</div>";

        html = html + "</body>" + "</html>";

        context.html(html);
    }

}