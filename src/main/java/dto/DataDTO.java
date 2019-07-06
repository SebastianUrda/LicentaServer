package dto;

import com.google.gson.Gson;


public class DataDTO {
    private int userId;
    private int deviceId;
    private String date;
    private String latitude;
    private String longitude;
    private double lpg;
    private double co;
    private double smoke;
    private double co2;
    private double backTemp;
    private double humidity;
    private double dust;
    private double pressure;
    private double frontTemp;
    private double vis;
    private double ir;
    private double uv;
    private double frontTempDht;
    private double frontHumidity;

    public DataDTO() {
    }

    public DataDTO(String date, String latitude, String longitude, double lpg, double co, double smoke, double co2,
                   double backTemp, double humidity, double dust, double pressure, double frontTemp, double vis,
                   double ir, double uv,double frontTempDht,double frontHumidity) {
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lpg = lpg;
        this.co = co;
        this.smoke = smoke;
        this.co2 = co2;
        this.backTemp = backTemp;
        this.humidity = humidity;
        this.dust = dust;
        this.pressure = pressure;
        this.frontTemp = frontTemp;
        this.vis = vis;
        this.ir = ir;
        this.uv = uv;
        this.frontTempDht=frontTempDht;
        this.frontHumidity=frontHumidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String lattitude) {
        this.latitude = lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public double getLpg() {
        return lpg;
    }

    public void setLpg(double lpg) {
        this.lpg = lpg;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public double getSmoke() {
        return smoke;
    }

    public void setSmoke(double smoke) {
        this.smoke = smoke;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getBackTemp() {
        return backTemp;
    }

    public void setBackTemp(double backTemp) {
        this.backTemp = backTemp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getDust() {
        return dust;
    }

    public void setDust(double dust) {
        this.dust = dust;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getFrontTemp() {
        return frontTemp;
    }

    public void setFrontTemp(double frontTemp) {
        this.frontTemp = frontTemp;
    }

    public double getVis() {
        return vis;
    }

    public void setVis(double vis) {
        this.vis = vis;
    }

    public double getIr() {
        return ir;
    }

    public void setIr(double ir) {
        this.ir = ir;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public double getFrontTempDht() {
        return frontTempDht;
    }

    public void setFrontTempDht(double frontTempDht) {
        this.frontTempDht = frontTempDht;
    }

    public double getFrontHumidity() {
        return frontHumidity;
    }

    public void setFrontHumidity(double frontHumidity) {
        this.frontHumidity = frontHumidity;
    }

    public String toJson() {

        Gson gson=new Gson();
        String jo=gson.toJson(this);
        return jo;
    }
}