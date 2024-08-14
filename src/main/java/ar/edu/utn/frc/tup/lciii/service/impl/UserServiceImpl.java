package ar.edu.utn.frc.tup.lciii.service.impl;

import ar.edu.utn.frc.tup.lciii.entity.UserEntity;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.repositories.UserRepository;
import ar.edu.utn.frc.tup.lciii.repositories.impl.UserRepositoryImpl;
import ar.edu.utn.frc.tup.lciii.service.UserService;

public class UserServiceImpl implements UserService {
    UserRepository userRepository = new UserRepositoryImpl();
    @Override
    public User validateUser(String User, String Password) {
        UserEntity userEntity    = userRepository.getByName(User);
        if(userEntity != null && userEntity.getPassword().equals(Password)){
           User user =  new User();
           user.setId(userEntity.getId());
           user.setName(userEntity.getName());
            return user;
        }
        else {
            return null;
        }
    }

    @Override
    public User postUser(String userName, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userName);
        userEntity.setPassword(password);
        UserEntity excistUSer = userRepository.getByName(userName);

        if(excistUSer == null){
            userRepository.save(userEntity);
            userEntity = userRepository.getByName(userName);
            User user = new User(userEntity.getId(), userEntity.getName());
            return user;
        }
        return null;
    }
}
