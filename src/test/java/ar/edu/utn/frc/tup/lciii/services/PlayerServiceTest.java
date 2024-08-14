package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.repositories.PlayerRepository;
import ar.edu.utn.frc.tup.lciii.service.PlayerService;
import ar.edu.utn.frc.tup.lciii.service.impl.PlayerServiceImpl;
import org.hibernate.mapping.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepositoryMock;

    PlayerService playerService = new PlayerServiceImpl();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Disabled
    @Test
    public void getAllPlayerTest(){
        Long idgame = Long.parseLong("1");
        List<PlayerImplement> players = playerService.getAllPlayer(idgame);
        assertNotNull(players);
    }

}
