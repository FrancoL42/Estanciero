package ar.edu.utn.frc.tup.lciii;


import ar.edu.utn.frc.tup.lciii.entity.CardEntity;
import ar.edu.utn.frc.tup.lciii.entity.UserEntity;
import ar.edu.utn.frc.tup.lciii.model.console.MenuI;
import ar.edu.utn.frc.tup.lciii.repositories.CardRepository;
import ar.edu.utn.frc.tup.lciii.repositories.UserRepository;
import ar.edu.utn.frc.tup.lciii.repositories.impl.CardRepositoryImpl;
import ar.edu.utn.frc.tup.lciii.repositories.impl.UserRepositoryImpl;

import java.util.List;

/**
 * Hello to TPI Estanciero
 *
 */
public class App {

    /**
     * This is the main program
     */
    public static void main(String[] args) {
      MenuI menuI = new MenuI();
      menuI.start();
    }
}
