package ar.edu.utn.frc.tup.lciii.entity.converts;

import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entity.PlayerTypeEntity;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.AgressiveBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ConservativeBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;

public class PlayerConvert {

    public static PlayerImplement ConvertToPlayer (PlayerEntity player){
       PlayerImplement playerImplement = new PlayerImplement();
        if(player.getPlayerType().getId() ==1L){
            playerImplement.setStrategy(new HumanPlayerStrategy());

        } else if(player.getPlayerType().getId()==2L){
            playerImplement.setStrategy(new ConservativeBotStrategy());
        } else if (player.getPlayerType().getId()==3L){
            playerImplement.setStrategy(new ModerateBotStrategy());
        } else if(player.getPlayerType().getId()==4L){
            playerImplement.setStrategy(new AgressiveBotStrategy());
        }

        if(player.getSquare()==null){
            playerImplement.setNumberSquare(0);
        } else{
            playerImplement.setNumberSquare(player.getSquare().getNumber());
        }
        playerImplement.setPlayerID(player.getId());
        playerImplement.setOrderTurn(player.getOrderTurn());
        playerImplement.setPlayerName(player.getName());
        playerImplement.setBalance(player.getBalance());
        playerImplement.setInJail(player.getInJail());
        playerImplement.setRestTurnCounter(player.getRestCount());

        return playerImplement;
    }

    public static  PlayerEntity ConvertToPlayerEntity(PlayerImplement playerImplement){
        PlayerEntity player = new PlayerEntity();
        PlayerTypeEntity playerTypeEntity = new PlayerTypeEntity();
        if (playerImplement.getStrategy() instanceof HumanPlayerStrategy){
            playerTypeEntity.setId(1L);
        } else if(playerImplement.getStrategy() instanceof  ConservativeBotStrategy){
            playerTypeEntity.setId(2L);
        } else if (playerImplement.getStrategy() instanceof  ModerateBotStrategy){
            playerTypeEntity.setId(3L);
        } else if(playerImplement.getStrategy() instanceof AgressiveBotStrategy){
            playerTypeEntity.setId(4L);
        }
        player.setOrderTurn(playerImplement.getOrderTurn());
        player.setId(playerImplement.getPlayerID());
        player.setPlayerType(playerTypeEntity);
        player.setBalance(playerImplement.getBalance());
        player.setName(playerImplement.getPlayerName());
        player.setInJail(playerImplement.isInJail());
        player.setRestCount(playerImplement.getRestTurnCounter());
        return player;

    }
}
