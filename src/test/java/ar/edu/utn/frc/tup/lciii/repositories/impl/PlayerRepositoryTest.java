package ar.edu.utn.frc.tup.lciii.repositories.impl;

import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entity.SquareEntity;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.square.AbstractSquare;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import ar.edu.utn.frc.tup.lciii.model.square.StartingSquare;
import ar.edu.utn.frc.tup.lciii.repositories.PlayerRepository;
import ar.edu.utn.frc.tup.lciii.service.PlayerService;
import ar.edu.utn.frc.tup.lciii.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PlayerRepositoryTest {


    private PlayerRepository playerRepositoryMock;

    PlayerService playerService = new PlayerServiceImpl();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        playerRepositoryMock = new PlayerRepositoryImpl();
    }
    @Test
    void getAllPlayersByGameIdTest(){
        Long id = Long.parseLong("1");
        List<PlayerEntity> playerEntities = playerRepositoryMock.getAllPlayersByGameId(id);
        assertNotNull(playerEntities);
    }
    @Test
    @Disabled
    public void getAllPlayerTest1(){
        //Funciona
        Long idGame = 1L;
        Map<Integer, Square> squareEntityMap = new HashMap<>();
        AbstractSquare abstractSquare = new StartingSquare();
        abstractSquare.setNumberSquare(0);
        squareEntityMap.put(0,abstractSquare);
        Board.getInstance().setSquares(squareEntityMap);
        List<PlayerEntity> mockPlayerEntities = Collections.singletonList(createMockPlayerEntity());
       // when(playerRepositoryMock.getAllPlayersByGameId(idGame)).thenReturn(mockPlayerEntities);
        List<PlayerImplement> players = playerService.getAllPlayer(idGame);

        assertEquals(1, players.size()); // verifica que se devuelva exactamente un jugador
        PlayerImplement player = players.get(0);
        assertEquals("pepe", player.getPlayerName()); // verifica el nombre del jugador
        assertEquals(1000, player.getBalance());
    }
    @Test
    public void getByIdTest(){
        Long idGame = 1L;
        PlayerEntity mockPlayerEntiti =createMockPlayerEntity();
        //when(playerRepositoryMock.getById(idGame)).thenReturn(mockPlayerEntiti);
        PlayerEntity result = playerRepositoryMock.getById(idGame);
        assertNotNull(result);

        assertEquals("pepe", result.getName());
        assertEquals(1000, result.getBalance());
    }
    private PlayerEntity createMockPlayerEntity() {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(1L);
        playerEntity.setName("pepe");
        playerEntity.setBalance(1000);
        return playerEntity;
    }
}
