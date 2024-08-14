package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entity.UserEntity;

public interface UserRepository {

    UserEntity getByName(String name);

    UserEntity save(UserEntity entity);

}
