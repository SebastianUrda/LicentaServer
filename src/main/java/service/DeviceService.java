package service;

import dao.DeviceDao;
import dao.SensorDao;
import dao.UserDao;
import dto.DeviceDTO;
import entity.DeviceA;
import entity.SensorA;
import entity.UserA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeviceService {
    private static final Logger LOGGER = LogManager.getLogger(DeviceService.class);
    public DeviceDTO createDevice(String name, int userId) {
        LOGGER.info(name,userId);
        UserDao userDao = new UserDao();
        DeviceDao deviceDao = new DeviceDao();
        UserA owner = userDao.findById(userId);
        DeviceA toInsert = new DeviceA();
        toInsert.setName(name);
        toInsert.setOwner(owner);
        DeviceA deviceA=deviceDao.save(toInsert);
        DeviceDTO deviceDTO= new DeviceDTO(deviceA.getId(),deviceA.getName());
        SensorDao sensorDao=new SensorDao();
        SensorA toAdd=new SensorA(deviceDao.findById(deviceDTO.getId()),"SI1145","ultraviolet, infrared, visible");
        sensorDao.save(toAdd);
        toAdd=new SensorA(deviceDao.findById(deviceDTO.getId()),"MPL3115A2","pressure, temperature");
        sensorDao.save(toAdd);
        toAdd=new SensorA(deviceDao.findById(deviceDTO.getId()),"MQ2","smoke, lpg, co");
        sensorDao.save(toAdd);
        toAdd=new SensorA(deviceDao.findById(deviceDTO.getId()),"MQ135","co2");
        sensorDao.save(toAdd);
        toAdd=new SensorA(deviceDao.findById(deviceDTO.getId()),"DHT11","temperature, humidity");
        sensorDao.save(toAdd);
        toAdd=new SensorA(deviceDao.findById(deviceDTO.getId()),"DHT112","temperature, humidity");
        sensorDao.save(toAdd);
        toAdd=new SensorA(deviceDao.findById(deviceDTO.getId()),"GP2Y1014","dust density ");
        sensorDao.save(toAdd);
        return deviceDTO;

    }
}
