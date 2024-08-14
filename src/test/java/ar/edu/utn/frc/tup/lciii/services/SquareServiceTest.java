package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.entity.PropertyEntity;
import ar.edu.utn.frc.tup.lciii.entity.ProvinceEntity;
import ar.edu.utn.frc.tup.lciii.entity.SquareEntity;
import ar.edu.utn.frc.tup.lciii.entity.SquareTypeEntity;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.square.*;
import ar.edu.utn.frc.tup.lciii.repositories.SquareRepository;
import ar.edu.utn.frc.tup.lciii.service.SquareService;
import ar.edu.utn.frc.tup.lciii.service.UserService;
import ar.edu.utn.frc.tup.lciii.service.impl.SquareServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SquareServiceTest {
    @Mock
    private SquareRepository squareRepository;

    @InjectMocks
    private SquareServiceImpl squareService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getAllSquares(){
        List<Square> squares = new ArrayList<>();
        squares.add(new PropertySquare());
        squares.add(new LuckSquare());
        squares.add(new DestinySquare());
        squares.add(new JailSquare());
        squares.add(new PrizePenaltySquare());
        squares.add(new StartingSquare());
        squares.add(new RestSquare());
        squares.add(new GoToJailSquare());
        squares.add(new FreeParkingSquare());
        squares.add(new PrizePenaltySquare());
        squares.add(new StartingSquare());
        when(squareService.getAllSquare(new ArrayList<>())).thenReturn(squares);
    }

}
