package entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "answer")
public class AnswerA {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private UserA user;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "question_id")
    private QuestionA question;
    @Column(name = "timestamp", columnDefinition = "DATETIME", nullable = false, length = 200)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Column(name = "latitude", nullable = false, length = 200)
    private Double latitude;
    @Column(name = "longitude", nullable = false, length = 200)
    private Double longitude;
    @Column(name = "value", nullable = false, length = 200)
    private int value;

    public AnswerA() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserA getUser() {
        return user;
    }

    public void setUser(UserA user) {
        this.user = user;
    }

    public QuestionA getQuestion() {
        return question;
    }

    public void setQuestion(QuestionA question) {
        this.question = question;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AnswerA{" +
                "id=" + id +
                ", question=" + question.getText() +
                ", timestamp=" + timestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", value=" + value +
                '}';
    }
}
