package servlets;

import com.google.gson.Gson;
import dao.DataDao;
import dto.DataDTO;
import entity.DataA;
import dto.DataList;
import service.ObservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns="/mightWork/data")
public class DataServlet  extends HttpServlet{
    private static final Logger LOGGER = LogManager.getLogger(DataServlet.class);
    private static final long serialVersionUID = 1L;
    DataList testList=new DataList();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin","*");
        DataDao dao=new DataDao();
        List<DataA> toPrint =dao.findAll();
        for(DataA data:toPrint)
        {
            DataDTO toInsert =new DataDTO();
            toInsert.setDate(data.getDate());
            toInsert.setLatitude(data.getLatitude());
            toInsert.setLongitude(data.getLongitude());
            toInsert.setLpg(data.getLpg());
            toInsert.setCo(data.getCo());
            toInsert.setSmoke(data.getSmoke());
            toInsert.setCo2(data.getCo2());
            toInsert.setBackTemp(data.getBackTemp());
            toInsert.setHumidity(data.getHumidity());
            toInsert.setDust(data.getDust());
            toInsert.setPressure(data.getPressure());
            toInsert.setFrontTemp(data.getFrontTemp());
            toInsert.setVis(data.getVis());
            toInsert.setIr(data.getIr());
            toInsert.setUv(data.getUv());
            toInsert.setFrontHumidity(data.getFrontHumidity());
            toInsert.setFrontTempDht(data.getFrontTempDht());
            testList.getDates().add(toInsert);
        }
        PrintWriter out = response.getWriter();
        out.print(testList.toJson());
        testList.getDates().clear();
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        DataDTO dataDTO = gson.fromJson(reader, DataDTO.class);
        testList.getDates().add(dataDTO);
        ObservationService observationService = new ObservationService();
        observationService.saveObservation(dataDTO);
    }
}
