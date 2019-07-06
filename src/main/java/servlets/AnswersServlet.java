package servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.AnswerDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AnswersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet(urlPatterns = "/mightWork/answers")
public class AnswersServlet extends HttpServlet {
    private AnswersService answersService = new AnswersService();
    private static final Logger LOGGER = LogManager.getLogger(AnswersServlet.class);
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Type type = new TypeToken<List<AnswerDTO>>() {
        }.getType();
        List<AnswerDTO> read = gson.fromJson(reader, type);
        answersService.saveAnswers(read);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        String jo = gson.toJson(answersService.findAll());
        out.print(jo);
        out.flush();
    }
}
