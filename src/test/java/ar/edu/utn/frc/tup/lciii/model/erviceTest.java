package ar.edu.utn.frc.tup.lciii.model;

import ar.edu.utn.frc.tup.lciii.service.SquareService;
import ar.edu.utn.frc.tup.lciii.service.UserService;
import ar.edu.utn.frc.tup.lciii.service.impl.SquareServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class erviceTest {

    @Test
    public void serviceTest(){
        UserService userService = new UserServiceImpl();
        userService.validateUser("rama", "123");
        userService.postUser("Rama", "123");

        SquareService service = new SquareServiceImpl();
        service.getAllSquare(new ArrayList<>());

    }
}
