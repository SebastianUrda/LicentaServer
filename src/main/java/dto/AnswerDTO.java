package dto;

import java.util.Date;

public class AnswerDTO {
    private int answer;
    private Date date;
    private Double latitude;
    private Double longitude;
    private int questionId;
    private int userId;

    public AnswerDTO(int answer, Date date, Double latitude, Double longitude, int questionId, int userId) {
        this.answer = answer;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.questionId = questionId;
        this.userId = userId;
    }

    public AnswerDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "date=" + date +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", questionId=" + questionId +
                ", userId=" + userId +
                ", answer=" + answer +
                '}';
    }
}
