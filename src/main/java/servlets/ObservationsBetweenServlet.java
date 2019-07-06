package servlets;

import com.google.gson.Gson;
import dto.ObservationDTO;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/mightWork/observation/between")
public class ObservationsBetweenServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(ObservationsBetweenServlet.class);
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        int id = Integer.valueOf(request.getParameter("id"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start;
        Date end;
        List<ObservationDTO> observations=new ArrayList<>();
        try {
            start = dateFormat.parse(startDate);
            end = dateFormat.parse(endDate);
            ObservationService observationService = new ObservationService();
            observations=(observationService.getObservationsBetween(start,end,id));
        } catch (ParseException e) {
            LOGGER.error("Getting observations Between",e);
        }
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String jo = gson.toJson(observations);
        out.print(jo);
        out.flush();
    }
}