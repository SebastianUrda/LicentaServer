package servlets;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ObservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/mightWork/observation/around")
public class ObservationsAroundServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(ObservationServlet.class);
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        double geoDistance = Double.valueOf(request.getParameter("geoDistance"));
        double timeDistance = Double.valueOf(request.getParameter("timeDistance"));
        double latitude = Double.valueOf(request.getParameter("latitude"));
        double longitude = Double.valueOf(request.getParameter("longitude"));

        PrintWriter out = response.getWriter();
        ObservationService observationService = new ObservationService();
        Gson gson = new Gson();
//        LOGGER.info(latitude + " " + longitude + " " + timeDistance + " " + geoDistance);
        String toRespond=observationService.getObservationsAround(latitude, longitude, new Date(), timeDistance, geoDistance);
        LOGGER.info(toRespond);
        String jo = gson.toJson(toRespond);
        out.print(jo);
        out.flush();


    }
}