package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.entity.UserEntity;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.service.UserService;
import ar.edu.utn.frc.tup.lciii.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    private UserService userService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void validateUserTestSuccess(){
        UserEntity user = new UserEntity();
        user.setPassword("123");
        user.setName("Franco");
        User userDB = new User();
        userDB.setName("Franco");
        when(userService.validateUser(user.getName(),user.getPassword())).thenReturn(userDB);
        assertEquals(userService.validateUser(user.getName(),user.getPassword()), userDB);
    }
    @Test
    public void validateUserFail(){
        UserEntity user = new UserEntity();
        user.setPassword("123");
        user.setName("Pepo");
        UserEntity userDB = new UserEntity();
        userDB.setPassword("123");
        userDB.setName("Franco");
        when(userService.validateUser(user.getName(),user.getPassword())).thenReturn(null);
        assertNull(userService.validateUser(user.getName(),user.getPassword()));
    }
    @Test
    public void postUserTest(){
        UserEntity user = new UserEntity();
        user.setPassword("123");
        user.setName("Franco");
       when(userService.postUser(user.getName(),user.getPassword())).thenReturn(null);
       assertNull(userService.postUser(user.getName(),user.getPassword()));
    }
}
