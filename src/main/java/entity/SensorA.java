package entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sensor")
public class SensorA {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "device_id")
    private DeviceA device;
    @Column(name = "name", nullable = true, length = 200)
    private String name = null;
    @Column(name = "measuring", nullable = true, length = 200)
    private String measuring = null;

    public SensorA() {
    }

    public SensorA(DeviceA device, String name, String measuring) {
        this.device = device;
        this.name = name;
        this.measuring = measuring;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeviceA getDevice() {
        return device;
    }

    public void setDevice(DeviceA device) {
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasuring() {
        return measuring;
    }

    public void setMeasuring(String measuring) {
        this.measuring = measuring;
    }

    @Override
    public String toString() {
        return "SensorA{" +
                "id=" + id +
                ", device=" + device +
                ", name='" + name + '\'' +
                ", measuring='" + measuring + '\'' +
                '}';
    }
}
