package entity;

import com.google.gson.Gson;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name="sensor_observation")
public class ObservationA {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(name="measuring", nullable=false,length=200)
    private String measuring;
    @Column(name = "timestamp",columnDefinition = "DATETIME",nullable = false, length = 200)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Column(name = "latitude", nullable = false, length = 200)
    private Double latitude;
    @Column(name = "longitude", nullable = false, length = 200)
    private Double longitude;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sensor_id")
    private SensorA sensor;
    @Column(name = "value", nullable = false, length = 200)
    private Double value;
    @Column(name = "measurement_unit", nullable = false, length = 200)
    private String measurementUnit;

    public ObservationA() {
    }

    public ObservationA(String measuring, Date timestamp, Double latitude, Double longitude, SensorA sensor, Double value, String measurementUnit) {
        this.measuring = measuring;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sensor = sensor;
        this.value = value;
        this.measurementUnit = measurementUnit;
    }

    public ObservationA(Date timestamp, Double latitude, Double longitude, SensorA sensor, Double value, String measurementUnit) {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sensor = sensor;
        this.value = value;
        this.measurementUnit = measurementUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public SensorA getSensor() {
        return sensor;
    }

    public void setSensor(SensorA sensor) {
        this.sensor = sensor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getMeasuring() {
        return measuring;
    }

    public void setMeasuring(String measuring) {
        this.measuring = measuring;
    }

    public String toJson() {

        Gson gson=new Gson();
        String jo=gson.toJson(this);
        return jo;
    }

    @Override
    public String toString() {
        return "ObservationA{" +
                "id=" + id +
                ", measuring='" + measuring + '\'' +
                ", timestamp=" + timestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", sensor=" + sensor +
                ", value=" + value +
                ", measurementUnit='" + measurementUnit + '\'' +
                '}';
    }
}
