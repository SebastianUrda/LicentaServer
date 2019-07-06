package dto;

import entity.QuestionA;

import java.util.Date;
import java.util.List;

public class TableRowDTO {

    private int answerId;
    private int userId;
    private QuestionA question;
    private Date timestamp;
    private Double latitude;
    private Double longitude;
    private int value;
    private List<ObservationDTO> observations;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public List<ObservationDTO> getObservations() {
        return observations;
    }

    public void setObservations(List<ObservationDTO> observations) {
        this.observations = observations;
    }
}
