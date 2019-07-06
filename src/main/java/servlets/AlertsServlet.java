package servlets;

import com.google.gson.Gson;
import dao.AlertDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AlertsDeletionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/mightWork/alerts")
public class AlertsServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(AlertsServlet.class);
    private static final long serialVersionUID = 1L;
    private static boolean started=false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        if(!started) {
            Thread deleter = new Thread(new AlertsDeletionService());
            deleter.start();
            started=true;
        }
        PrintWriter out = resp.getWriter();
        AlertDao alertDao = new AlertDao();
        Gson gson = new Gson();
        String jo = gson.toJson(alertDao.findAll());
        out.print(jo);
        out.flush();
    }

}
