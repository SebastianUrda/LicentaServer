package servlets;

import com.google.gson.Gson;
import dao.QuestionDao;
import entity.QuestionA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns="/mightWork/question")
public class QuestionServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(QuestionServlet.class);
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String type=req.getParameter("type");
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setHeader("Access-Control-Allow-Origin","*");
        List<QuestionA> questions= QuestionDao.findByType(type);
        Gson gson=new Gson();
        String jo=gson.toJson(questions);
        out.print(jo);
        out.close();
    }
}
