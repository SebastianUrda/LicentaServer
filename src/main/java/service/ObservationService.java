package service;

import dao.AlertDao;
import dao.DataDao;
import dao.ObservationDao;
import dao.SensorDao;
import dto.DataDTO;
import dto.ObservationDTO;
import entity.AlertA;
import entity.DataA;
import entity.ObservationA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObservationService {
    private static final Logger LOGGER = LogManager.getLogger(ObservationService.class);

    public void saveObservation(DataDTO dataDTO) {
        DataA toInsert = new DataA();
        toInsert.setDate(dataDTO.getDate());
        toInsert.setLatitude(dataDTO.getLatitude());
        toInsert.setLongitude(dataDTO.getLongitude());
        toInsert.setLpg(dataDTO.getLpg());
        toInsert.setCo(dataDTO.getCo());
        toInsert.setSmoke(dataDTO.getSmoke());
        toInsert.setCo2(dataDTO.getCo2());
        toInsert.setBackTemp(dataDTO.getBackTemp());
        toInsert.setHumidity(dataDTO.getHumidity());
        toInsert.setDust(dataDTO.getDust());
        toInsert.setPressure(dataDTO.getPressure());
        toInsert.setFrontTemp(dataDTO.getFrontTemp());
        toInsert.setVis(dataDTO.getVis());
        toInsert.setIr(dataDTO.getIr());
        toInsert.setUv(dataDTO.getUv());
        toInsert.setFrontHumidity(dataDTO.getFrontHumidity());
        toInsert.setFrontTempDht(dataDTO.getFrontTempDht());
        DataDao dao = new DataDao();
        createAlert(dataDTO);
        dao.insert(toInsert);
        ObservationDao observationDao = new ObservationDao();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            java.util.Date timeStamp = dateFormat.parse(dataDTO.getDate());
            observationDao.save(new ObservationA("LPG", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("MQ2", dataDTO.getDeviceId()), dataDTO.getLpg(), "PPM"));
            observationDao.save(new ObservationA("CO", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("MQ2", dataDTO.getDeviceId()), dataDTO.getCo(), "PPM"));
            observationDao.save(new ObservationA("SMOKE", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("MQ2", dataDTO.getDeviceId()), dataDTO.getSmoke(), "PPM"));
            observationDao.save(new ObservationA("CO2", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("MQ135", dataDTO.getDeviceId()), dataDTO.getCo2(), "PPM"));
            observationDao.save(new ObservationA("BackTemp", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("DHT11", dataDTO.getDeviceId()), dataDTO.getBackTemp(), "C"));
            observationDao.save(new ObservationA("BackHumidity", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("DHT11", dataDTO.getDeviceId()), dataDTO.getHumidity(), "%"));
            observationDao.save(new ObservationA("Pressure", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("MPL3115A2", dataDTO.getDeviceId()), dataDTO.getPressure(), "Pa"));
            observationDao.save(new ObservationA("FrontTemp", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("MPL3115A2", dataDTO.getDeviceId()), dataDTO.getFrontTemp(), "C"));
            observationDao.save(new ObservationA("Infrared", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("SI1145", dataDTO.getDeviceId()), dataDTO.getIr(), " "));
            observationDao.save(new ObservationA("Ultraviolet", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("SI1145", dataDTO.getDeviceId()), dataDTO.getUv(), "UV Index"));
            observationDao.save(new ObservationA("Visible", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("SI1145", dataDTO.getDeviceId()), dataDTO.getVis(), "Lux"));
            observationDao.save(new ObservationA("DustDensity", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("GP2Y1014", dataDTO.getDeviceId()), dataDTO.getDust(), "ug/m3"));
            observationDao.save(new ObservationA("FrontTempDht", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("DHT112", dataDTO.getDeviceId()), dataDTO.getFrontTempDht(), "C"));
            observationDao.save(new ObservationA("FrontHumidity", timeStamp, Double.parseDouble(dataDTO.getLatitude()), Double.parseDouble(dataDTO.getLongitude()), SensorDao.findByNameAndDeviceId("DHT112", dataDTO.getDeviceId()), dataDTO.getFrontHumidity(), "%"));
        } catch (ParseException e) {
            LOGGER.error("Date Parsing while inserting observations ", e);
        }
    }

    public static List<ObservationDTO> findAll() {
        List<ObservationDTO> observationDTOS = new ArrayList<>();
        ObservationDao observationDao = new ObservationDao();
        List<ObservationA> observations = observationDao.findAll();
        for (ObservationA observation : observations) {
            ObservationDTO toAdd = new ObservationDTO();
            toAdd.setId(observation.getId());
            toAdd.setMeasuring(observation.getMeasuring());
            toAdd.setTimestamp(observation.getTimestamp());
            toAdd.setLatitude(observation.getLatitude());
            toAdd.setLongitude(observation.getLongitude());
            toAdd.setValue(observation.getValue());
            toAdd.setMeasurementUnit(observation.getMeasurementUnit());
            observationDTOS.add(toAdd);
        }
        return observationDTOS;
    }

    public String getObservationsAround(double latitude, double longitude, Date timestamp, double timeDist, double spaceDist) {
        ObservationDao observationDao = new ObservationDao();
        List<ObservationA> observations = observationDao.callAverageOfMeasurementsAround(latitude, longitude, timestamp, timeDist, spaceDist);
//        LOGGER.info(observations);
        List<ObservationDTO> toReturn = new ArrayList<>();
        observations.forEach(obs->{
            ObservationDTO toAdd = new ObservationDTO();
            toAdd.setMeasuring(obs.getMeasuring());
            toAdd.setMeasurementUnit(obs.getMeasurementUnit());
            toAdd.setValue(obs.getValue());
            toReturn.add(toAdd);
        });
        String returned = "";
        DecimalFormat numberFormat = new DecimalFormat("0.00");
        for (ObservationDTO obs : toReturn) {
            returned = returned + obs.getMeasuring() + ": " + numberFormat.format(obs.getValue()) + " " + obs.getMeasurementUnit() + ", ";
        }
        if(returned.equals(""))
            return "No data available around!";
        return returned;
    }

    public List<ObservationDTO> getObservationsBetween(Date start, Date end, int id) {
        ObservationDao observationDao = new ObservationDao();
        List<ObservationDTO> observationDTOS = new ArrayList<>();
        List<ObservationA> fromDb = observationDao.callObservationsBetween(start, end, id);
        fromDb.forEach(observationA -> {
            ObservationDTO toAdd = new ObservationDTO();
            toAdd.setId(observationA.getId());
            toAdd.setLongitude(observationA.getLongitude());
            toAdd.setLatitude(observationA.getLatitude());
            toAdd.setTimestamp(observationA.getTimestamp());
            toAdd.setValue(observationA.getValue());
            toAdd.setMeasurementUnit(observationA.getMeasurementUnit());
            toAdd.setMeasuring(observationA.getMeasuring());
            observationDTOS.add(toAdd);
        });
        return observationDTOS;

    }


    private void createAlert(DataDTO dataDTO) {
        if (dataDTO.getLpg() > 0 || dataDTO.getCo() > 0 || dataDTO.getSmoke() > 0 || dataDTO.getCo2() > 10) {
            AlertDao alertDao = new AlertDao();
            List<AlertA> allAlerts = alertDao.findAll();
            boolean found = false;
            for (AlertA iterator : allAlerts) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                try {
                    Date timeStamp = dateFormat.parse(dataDTO.getDate());
                    if (timestampDiff(iterator.getTimestamp(), timeStamp) < 10 && distance(Double.parseDouble(dataDTO.getLatitude()), iterator.getLatitude(), Double.parseDouble(dataDTO.getLongitude()), iterator.getLongitude()) < 200) {
                        found = true;
                    }
                } catch (ParseException e) {
                    LOGGER.error("Date Parsing while creating alerts ", e);
                }
            }
            if (!found) {
                AlertA toAdd = new AlertA();
                toAdd.setDescription("Gas Alert");
                toAdd.setType("Gas");
                toAdd.setLatitude(Double.parseDouble(dataDTO.getLatitude()));
                toAdd.setLongitude(Double.parseDouble(dataDTO.getLongitude()));
                AlertsAddressAPICall alertsAddress=new AlertsAddressAPICall();
                toAdd.setAddress(alertsAddress.getAddress(toAdd.getLatitude(),toAdd.getLongitude()));
                try {
                    toAdd.setTimestamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(dataDTO.getDate()));
                    alertDao.save(toAdd);
                } catch (ParseException e) {
                    LOGGER.error("Date Parsing while creating alerts ", e);
                }
            }
        }
    }

    private double timestampDiff(Date timeStamp1, Date timeStamp2) {
        //minutes
        return Math.abs(timeStamp1.getTime() - timeStamp2.getTime()) / (60 * 1000);
    }

    private double distance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        return Math.round(distance);
    }

}
