package service;

import dao.AnswerDao;
import dto.AnswerDTO;
import entity.AnswerA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AnswersService {

    private static final Logger LOGGER = LogManager.getLogger(AnswersService.class);

    public void saveAnswers(List<AnswerDTO> answersDTO) {

        for (AnswerDTO answer : answersDTO) {
            AnswerDao answerDao = new AnswerDao();
            answerDao.save(answer);
        }
    }

    public List<AnswerDTO> findAll() {
        List<AnswerDTO> toReturn = new ArrayList<>();
        AnswerDao answerDao = new AnswerDao();
        for (AnswerA iterator : answerDao.findAll()) {
            toReturn.add(new AnswerDTO(iterator.getValue(), iterator.getTimestamp(), iterator.getLatitude(), iterator.getLongitude(), iterator.getQuestion().getId(), iterator.getUser().getId()));
        }
        return toReturn;
    }


}
