package ar.edu.utn.frc.tup.lciii.entity.converts;
import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entity.PlayerTypeEntity;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.AgressiveBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ConservativeBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerConvertTest {
    private PlayerEntity playerEntity;
    private PlayerImplement playerImplement;
    private PlayerTypeEntity playerTypeEntity;

    @BeforeEach
    public void setUp(){

        playerEntity = new PlayerEntity();
        playerImplement = new PlayerImplement();
        playerTypeEntity = new PlayerTypeEntity();

        playerEntity.setId(123L);
        playerEntity.setName("Test Player");
        playerEntity.setBalance(1000);
        playerEntity.setInJail(false);
        playerEntity.setRestCount(2);

        playerImplement.setPlayerID(123L);
        playerImplement.setPlayerName("Test Player");
        playerImplement.setBalance(1000);
        playerImplement.setInJail(false);
        playerImplement.setRestTurnCounter(2);


    }

    @Test
    public void ConvertToPlayerHuman(){
        playerTypeEntity.setId(1L);
        playerEntity.setPlayerType(playerTypeEntity);

        PlayerImplement result = PlayerConvert.ConvertToPlayer(playerEntity);

        assertNotNull(result);
        assertTrue(result.getStrategy() instanceof HumanPlayerStrategy);
        assertEquals(123L, result.getPlayerID());
        assertEquals("Test Player", result.getPlayerName());
        assertEquals(1000, result.getBalance());
        assertFalse(result.isInJail());
        assertEquals(2, result.getRestTurnCounter());
    }

    @Test
    public void ConvertToPlayerConservativeBot(){
        playerTypeEntity.setId(2L);
        playerEntity.setPlayerType(playerTypeEntity);

        PlayerImplement result = PlayerConvert.ConvertToPlayer(playerEntity);

        assertNotNull(result);
        assertTrue(result.getStrategy() instanceof ConservativeBotStrategy);
        assertEquals(123L, result.getPlayerID());
        assertEquals("Test Player", result.getPlayerName());
        assertEquals(1000, result.getBalance());
        assertFalse(result.isInJail());
        assertEquals(2, result.getRestTurnCounter());
    }

    @Test
    public void ConvertToPlayerModerateBot(){
        playerTypeEntity.setId(3L);
        playerEntity.setPlayerType(playerTypeEntity);

        PlayerImplement result = PlayerConvert.ConvertToPlayer(playerEntity);

        assertNotNull(result);
        assertTrue(result.getStrategy() instanceof ModerateBotStrategy);
        assertEquals(123L, result.getPlayerID());
        assertEquals("Test Player", result.getPlayerName());
        assertEquals(1000, result.getBalance());
        assertFalse(result.isInJail());
        assertEquals(2, result.getRestTurnCounter());
    }

    @Test
    public void ConvertToPlayerAgressiveBot(){
        playerTypeEntity.setId(4L);
        playerEntity.setPlayerType(playerTypeEntity);

        PlayerImplement result = PlayerConvert.ConvertToPlayer(playerEntity);

        assertNotNull(result);
        assertTrue(result.getStrategy() instanceof AgressiveBotStrategy);
        assertEquals(123L, result.getPlayerID());
        assertEquals("Test Player", result.getPlayerName());
        assertEquals(1000, result.getBalance());
        assertFalse(result.isInJail());
        assertEquals(2, result.getRestTurnCounter());
    }

    @Test
    public void ConvertToPlayerEntityHuman(){
        playerImplement.setStrategy(new HumanPlayerStrategy());

        PlayerEntity result = PlayerConvert.ConvertToPlayerEntity(playerImplement);

        assertNotNull(result);
        assertEquals(1L, result.getPlayerType().getId());
        assertEquals(123L, result.getId());
        assertEquals("Test Player", result.getName());
        assertEquals(1000, result.getBalance());
        assertFalse(result.getInJail());
        assertEquals(2, result.getRestCount());
    }

    @Test
    public  void ConvertToPlayerEntityConservativeBot(){
        playerImplement.setStrategy(new ConservativeBotStrategy());

        PlayerEntity result = PlayerConvert.ConvertToPlayerEntity(playerImplement);

        assertNotNull(result);
        assertEquals(2L, result.getPlayerType().getId());
        assertEquals(123L, result.getId());
        assertEquals("Test Player", result.getName());
        assertEquals(1000, result.getBalance());
        assertFalse(result.getInJail());
        assertEquals(2, result.getRestCount());
    }

    @Test
    public void ConvertToPlayerEntityModerateBot(){
        playerImplement.setStrategy(new ModerateBotStrategy());

        PlayerEntity result = PlayerConvert.ConvertToPlayerEntity(playerImplement);

        assertNotNull(result);
        assertEquals(3L, result.getPlayerType().getId());
        assertEquals(123L, result.getId());
        assertEquals("Test Player", result.getName());
        assertEquals(1000, result.getBalance());
        assertFalse(result.getInJail());
        assertEquals(2, result.getRestCount());
    }

    @Test
    public void ConvertToPlayerEntityAgressiveBot(){
        playerImplement.setStrategy(new AgressiveBotStrategy());

        PlayerEntity result = PlayerConvert.ConvertToPlayerEntity(playerImplement);

        assertNotNull(result);
        assertEquals(4L, result.getPlayerType().getId());
        assertEquals(123L, result.getId());
        assertEquals("Test Player", result.getName());
        assertEquals(1000, result.getBalance());
        assertFalse(result.getInJail());
        assertEquals(2, result.getRestCount());
    }
}
