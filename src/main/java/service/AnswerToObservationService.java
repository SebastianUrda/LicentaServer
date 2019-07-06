package service;

import dao.AnswerDao;
import dao.ObservationDao;
import dto.ObservationDTO;
import dto.TableRowDTO;
import entity.AnswerA;
import entity.ObservationA;

import java.util.ArrayList;
import java.util.List;

public class AnswerToObservationService {

    public List<TableRowDTO> createTable(double geoDistance, double timeDistance, int userId) {
        List<TableRowDTO> tableDto = new ArrayList<>();
        AnswerDao answerDao = new AnswerDao();
        ObservationDao observationDao = new ObservationDao();
        List<AnswerA> answers = answerDao.findAllByUserId(userId);
        for (AnswerA ans : answers) {
            TableRowDTO rowDto = new TableRowDTO();
            rowDto.setAnswerId(ans.getId());
            rowDto.setTimestamp(ans.getTimestamp());
            rowDto.setLatitude(ans.getLatitude());
            rowDto.setLongitude(ans.getLongitude());
            rowDto.setValue(ans.getValue());
            rowDto.setQuestion(ans.getQuestion());
            rowDto.setUserId(ans.getUser().getId());
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

            if (observations.size() > 0) {
                rowDto.setObservations(mapObservationsToObservationsDto(observations));
                tableDto.add(rowDto);
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
