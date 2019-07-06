package service;

import dao.AlertDao;
import entity.AlertA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class AlertsDeletionService implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(AlertsDeletionService.class);

    @Override
    public void run() {
        AlertDao alertDao = new AlertDao();
        while (true) {
            LOGGER.info("I was called at {}", new Date());

            List<AlertA> alerts = alertDao.findAll();
            for (AlertA test : alerts) {
                Date date = new Date();
                if (Math.abs(test.getTimestamp().getTime() - date.getTime()) / (60 * 1000) > 10) {
                    alertDao.delete(test);
                }
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                LOGGER.error("Deletion thread ", e);
            }
        }
    }
}
