package servlets;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.NewAnswersToObservationsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/mightWork/NewAnswersToObservations")
public class NewAnswerToObservationServlet extends HttpServlet {
    NewAnswersToObservationsService ansService = new NewAnswersToObservationsService();
    private static final Logger LOGGER = LogManager.getLogger(AnswerToObservationServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = resp.getWriter();
        double geoDistance = Double.valueOf(req.getParameter("geoDistance"));
        double timeDistance = Double.valueOf(req.getParameter("timeDistance"));
        int userId = Integer.valueOf(req.getParameter("userId"));
        Gson gson = new Gson();
        String jo = gson.toJson(ansService.createTable(geoDistance, timeDistance, userId));
        out.print(jo);
        out.flush();
    }
}
