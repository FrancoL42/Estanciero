package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.entity.UserEntity;
import ar.edu.utn.frc.tup.lciii.model.console.User;

public interface UserService {
    User validateUser(String User, String Password);
    User postUser(String userName, String password);
}
