package dto;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DataList {
    List<DataDTO> dates = new ArrayList<>();

    public DataList() {
    }

    public List<DataDTO> getDates() {
        return dates;
    }

    public void setDates(List<DataDTO> dates) {
        this.dates = dates;
    }

    public String toJson() {

        Gson gson = new Gson();
        String jo = gson.toJson(this);
        return jo;
    }
}
