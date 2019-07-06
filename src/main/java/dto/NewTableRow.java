package dto;

import entity.AnswerA;
import entity.ObservationA;

import java.util.List;

public class NewTableRow {
    private List<AnswerA> answers;
    private List<ObservationDTO> observations;

    public List<AnswerA> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerA> answers) {
        this.answers = answers;
    }

    public List<ObservationDTO> getObservations() {
        return observations;
    }

    public void setObservations(List<ObservationDTO> observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "NewTableRow{" +
                "answers=" + answers +
                ", observations=" + observations +
                '}';
    }
}
