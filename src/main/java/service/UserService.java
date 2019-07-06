package service;

import dao.DeviceDao;
import dao.UserDao;
import dto.UserDTO;
import entity.DeviceA;
import entity.UserA;

public class UserService {

    public UserDTO authenticate(UserA received) {

        UserDao userDao = new UserDao();
        UserA found = userDao.findByUserNameAndPassword(received.getUsername(), received.getPassword());
        DeviceDao deviceDao = new DeviceDao();
        DeviceA deviceA = deviceDao.findByOwnerId(found.getId());
        if (deviceA != null) {
            return new UserDTO(found.getId(), deviceA.getId(), found.getUsername(), found.getPassword(), found.getRole(), found.getEmail());
        } else {
            return new UserDTO(found.getId(), 0, found.getUsername(), found.getPassword(), found.getRole(), found.getEmail());
        }
    }

    public UserDTO register(UserA received) {
        UserDao userDao = new UserDao();
        received.setRole("USER");
        UserA saved = userDao.save(received);
        return new UserDTO(saved.getId(), 0, saved.getUsername(), saved.getPassword(), saved.getRole(), saved.getEmail());
    }
}
