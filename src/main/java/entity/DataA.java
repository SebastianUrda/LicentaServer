package entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "data")
public class DataA {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(name = "date", nullable = false, length = 200)
    private String date;
    @Column(name = "latitude", nullable = false, length = 200)
    private String latitude;
    @Column(name = "longitude", nullable = false, length = 200)
    private String longitude;
    @Column(name = "lpg", nullable = false, length = 200)
    private double lpg;
    @Column(name = "co", nullable = false, length = 200)
    private double co;
    @Column(name = "smoke", nullable = false, length = 200)
    private double smoke;
    @Column(name = "co2", nullable = false, length = 200)
    private double co2;
    @Column(name = "back_temp", nullable = false, length = 200)
    private double backTemp;
    @Column(name = "humidity", nullable = false, length = 200)
    private double humidity;
    @Column(name = "dust", nullable = false, length = 200)
    private double dust;
    @Column(name = "pressure", nullable = false, length = 200)
    private double pressure;
    @Column(name = "front_temp", nullable = false, length = 200)
    private double frontTemp;
    @Column(name = "vis", nullable = false, length = 200)
    private double vis;
    @Column(name = "ir", nullable = false, length = 200)
    private double ir;
    @Column(name = "uv", nullable = false, length = 200)
    private double uv;
    @Column(name = "frontTempDht", nullable = false, length = 200)
    private double frontTempDht;
    @Column(name = "frontHumidity", nullable = false, length = 200)
    private double frontHumidity;

    public DataA() {
    }

    public DataA(int id, String date, String latitude, String longitude, double lpg, double co, double smoke, double co2,
                 double backTemp, double humidity, double dust, double pressure, double frontTemp, double vis, double ir,
                 double uv, double frontTempDht, double frontHumidity) {
        this.id = id;
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
        this.frontTempDht = frontTempDht;
        this.frontHumidity = frontHumidity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

