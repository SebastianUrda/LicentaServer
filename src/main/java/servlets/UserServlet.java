package servlets;

import com.google.gson.Gson;
import dto.UserDTO;
import entity.UserA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/mightWork/user")
public class UserServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(UserServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        UserA received = gson.fromJson(reader, UserA.class);
        UserService userService = new UserService();
        UserDTO toReturn = userService.authenticate(received);
        PrintWriter out = resp.getWriter();
        String jo = gson.toJson(toReturn);
        out.print(jo);
        out.close();
    }
}
