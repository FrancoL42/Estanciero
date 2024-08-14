package ar.edu.utn.frc.tup.lciii.model.game.builder;

import ar.edu.utn.frc.tup.lciii.dtos.ListGameInfoDto;
import ar.edu.utn.frc.tup.lciii.entity.*;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ConservativeBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.square.AbstractSquare;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import ar.edu.utn.frc.tup.lciii.service.GameService;
import ar.edu.utn.frc.tup.lciii.service.PlayerService;
import ar.edu.utn.frc.tup.lciii.service.SquareService;
import ar.edu.utn.frc.tup.lciii.service.impl.GameServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.PlayerServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.SquareServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class EasyGameBuilder extends AbstractGameBuilder {

    GameService gameService = new GameServiceImpl();

    public EasyGameBuilder(int victoryAmount) {
        this.victoryAmount = victoryAmount;
    }

    public EasyGameBuilder(User user, Integer amount, Integer victory ){
        this.infoGameDto =  gameService.newGame(user.getId(), amount, victory,Difficulty.EASY ,this.listPlayer(user));
        List<Square> abstractSquares = squareService.getAllSquare(infoGameDto.getProperties());
        infoGameDto.setSquares(abstractSquares);
        this.turn = 1;
    }
    /**
     * Agrega Players llamando a un service que contruya los objetos
     */


    @Override
    public void addPlayers(Long gameid) {
        infoGameDto.getPlayers().forEach(p-> {
            p.setSquare(Board.getInstance().getSquare(0));
            Board.getInstance().setPlayer(p);

        });

    }

    private List<PlayerImplement> listPlayer(User user){
        List<PlayerImplement> playerImplements = new ArrayList<>();
        PlayerImplement conservative = new PlayerImplement();
        conservative.setPlayerName("Reservado");
        conservative.setStrategy(new ConservativeBotStrategy());
        conservative.setBalance(35000);
        conservative.setInJail(false);
        conservative.setLoser(false);

        PlayerImplement humanPlayer = new PlayerImplement();
        humanPlayer.setPlayerName(user.getName());
        humanPlayer.setStrategy(new HumanPlayerStrategy());
        humanPlayer.setBalance(35000);
        humanPlayer.setInJail(false);
        humanPlayer.setLoser(false);

        PlayerImplement moderatePlayer = new PlayerImplement();
        moderatePlayer.setStrategy(new ModerateBotStrategy());
        moderatePlayer.setBalance(35000);
        moderatePlayer.setPlayerName("Moderado");
        moderatePlayer.setInJail(false);
        moderatePlayer.setLoser(false);

        playerImplements.add(conservative);
        playerImplements.add(moderatePlayer);
        playerImplements.add(humanPlayer);
        return playerImplements;
    }



}