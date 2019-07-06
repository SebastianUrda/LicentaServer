package servlets;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.DeviceService;
import service.ObservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/mightWork/device")
public class DeviceRegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DeviceRegistrationServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        int userId = Integer.parseInt(request.getParameter("userId"));
        LOGGER.info(name, userId);
        DeviceService deviceService=new DeviceService();
        Gson gson = new Gson();
        String jo = gson.toJson(deviceService.createDevice(name,userId));
        out.print(jo);
        out.close();
    }
}
