package ar.edu.utn.frc.tup.lciii.model.game.builder;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.AgressiveBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ConservativeBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.square.Square;


import java.util.ArrayList;
import java.util.List;


public class HardGameBuilder extends AbstractGameBuilder {

    public HardGameBuilder(int victoryAmount) {
        this.victoryAmount = victoryAmount;
    }

    @Override
    public void addPlayers(Long gameid) {
        infoGameDto.getPlayers().forEach(p-> {
            p.setSquare(Board.getInstance().getSquare(0));
            Board.getInstance().setPlayer(p);
        });
    }

    public HardGameBuilder(User user, Integer amount, Integer victory ){
        this.infoGameDto =  gameService.newGame(user.getId(), amount, victory,Difficulty.HARD ,this.listPlayer(user));
        List<Square> abstractSquares = squareService.getAllSquare(infoGameDto.getProperties());
        infoGameDto.setSquares(abstractSquares);
        this.turn = 1;

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
        moderatePlayer.setPlayerName("medio 1");
        moderatePlayer.setBalance(35000);
        moderatePlayer.setInJail(false);
        moderatePlayer.setLoser(false);

        PlayerImplement moderatePlayer2= new PlayerImplement();
        moderatePlayer2.setStrategy(new ModerateBotStrategy());
        moderatePlayer2.setPlayerName("medio 2");
        moderatePlayer2.setBalance(35000);
        moderatePlayer2.setInJail(false);
        moderatePlayer2.setLoser(false);


        PlayerImplement agressivePlayer = new PlayerImplement();
        agressivePlayer.setStrategy(new AgressiveBotStrategy());
        agressivePlayer.setPlayerName("Agresivo");
        agressivePlayer.setBalance(35000);
        agressivePlayer.setInJail(false);
        agressivePlayer.setLoser(false);


        playerImplements.add(agressivePlayer);
        playerImplements.add(conservative);
        playerImplements.add(moderatePlayer);
        playerImplements.add(moderatePlayer2);
        playerImplements.add(humanPlayer);
        return playerImplements;
    }
}
