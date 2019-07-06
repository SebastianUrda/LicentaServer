package service;

import dao.AnswerDao;
import dao.ObservationDao;
import dto.NewTableRow;
import dto.ObservationDTO;
import entity.AnswerA;
import entity.ObservationA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewAnswersToObservationsService {
    public List<NewTableRow> createTable(double geoDistance, double timeDistance, int userId) {
        List<NewTableRow> tableDto = new ArrayList<>();
        AnswerDao answerDao = new AnswerDao();
        ObservationDao observationDao = new ObservationDao();
        List<AnswerA> answers = answerDao.findAllByUserId(userId);
        for (AnswerA ans : answers) {
            NewTableRow row = new NewTableRow();
            List<AnswerA> answersToAdd = new ArrayList<>();
            boolean isPresent = false;
            for (NewTableRow r : tableDto) {
                if (r.getAnswers().contains(ans)) {
                    isPresent = true;
                }
            }
            if (!isPresent)
                answersToAdd.add(ans);
            for (Iterator<AnswerA> iterator = answers.listIterator(); iterator.hasNext(); ) {
                AnswerA answer = iterator.next();
                boolean present = false;
                for (NewTableRow r : tableDto) {
                    if (r.getAnswers().contains(answer)) {
                        present = true;
                    }
                }
                if (answer.getTimestamp().equals(ans.getTimestamp()) && !answersToAdd.contains(answer) && !present) {
                    answersToAdd.add(answer);
//                    iterator.remove();
                }
            }
            List<ObservationA> observations = new ArrayList<>();
            if (ans.getQuestion().getType().equals("temperature")) {
                observations.addAll(observationDao.callFindAllInTimeMeasuring(ans.getLatitude(), ans.getLongitude(), ans.getTimestamp(), timeDistance, geoDistance, "FrontTemp"));
                observations.addAll(observationDao.callFindAllInTimeMeasuring(ans.getLatitude(), ans.getLongitude(), ans.getTimestamp(), timeDistance, geoDistance, "BackTemp"));
                observations.addAll(observationDao.callFindAllInTimeMeasuring(ans.getLatitude(), ans.getLongitude(), ans.getTimestamp(), timeDistance, geoDistance, "FrontTempDht"));
            }
            if (ans.getQuestion().getType().equals("dust")) {
                observations.addAll(observationDao.callFindAllInTimeMeasuring(ans.getLatitude(), ans.getLongitude(), ans.getTimestamp(), timeDistance, geoDistance, "DustDensity"));
            }
            if (ans.getQuestion().getType().equals("light")) {
                observations.addAll(observationDao.callFindAllInTimeMeasuring(ans.getLatitude(), ans.getLongitude(), ans.getTimestamp(), timeDistance, geoDistance, "Infrared"));
                observations.addAll(observationDao.callFindAllInTimeMeasuring(ans.getLatitude(), ans.getLongitude(), ans.getTimestamp(), timeDistance, geoDistance, "Ultraviolet"));
                observations.addAll(observationDao.callFindAllInTimeMeasuring(ans.getLatitude(), ans.getLongitude(), ans.getTimestamp(), timeDistance, geoDistance, "Visible"));
            }
            if (ans.getQuestion().getType().equals("humidity")) {
                observations.addAll(observationDao.callFindAllInTimeMeasuring(ans.getLatitude(), ans.getLongitude(), ans.getTimestamp(), timeDistance, geoDistance, "FrontHumidity"));
                observations.addAll(observationDao.callFindAllInTimeMeasuring(ans.getLatitude(), ans.getLongitude(), ans.getTimestamp(), timeDistance, geoDistance, "BackHumidity"));
            }
            if (observations.size() > 0 && answersToAdd.size() > 0) {
                row.setObservations(mapObservationsToObservationsDto(observations));
                row.setAnswers(answersToAdd);
                tableDto.add(row);
            }
        }
        return tableDto;
    }

    private List<ObservationDTO> mapObservationsToObservationsDto(List<ObservationA> observations) {
        List<ObservationDTO> toReturn = new ArrayList<>();
        for (ObservationA iterator : observations) {
            ObservationDTO toAdd = new ObservationDTO();
            toAdd.setId(iterator.getId());
            toAdd.setLatitude(iterator.getLatitude());
            toAdd.setLongitude(iterator.getLongitude());
            toAdd.setMeasurementUnit(iterator.getMeasurementUnit());
            toAdd.setMeasuring(iterator.getMeasuring());
            toAdd.setValue(iterator.getValue());
            toAdd.setTimestamp(iterator.getTimestamp());
            toReturn.add(toAdd);
        }
        return toReturn;
    }
}
