package ar.edu.utn.frc.tup.lciii.model.game;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.game.builder.AbstractGameBuilder;
import ar.edu.utn.frc.tup.lciii.model.game.builder.BuilderDirector;
import ar.edu.utn.frc.tup.lciii.model.game.builder.GameBuilder;
import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.dice.Dice;
import ar.edu.utn.frc.tup.lciii.model.dice.DiceImpl;
import ar.edu.utn.frc.tup.lciii.model.dice.DiceResult;
import ar.edu.utn.frc.tup.lciii.model.player.*;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.square.FreeParkingSquare;
import ar.edu.utn.frc.tup.lciii.model.square.JailSquare;
import ar.edu.utn.frc.tup.lciii.model.square.RestSquare;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

/**
 * Clase Game
 * Encargada de iniciar el juego con sus parametros(Dificultad y Monto Victoria)
 */
@Getter
@Setter
@NoArgsConstructor
public class Game {
    private Long gameId;
    private Difficulty difficulty;
    private Integer victoryAmount;
    private Integer turn;
    private PlayerImplement playerTurn;
    Console console = new Console();
    private Dice dice = DiceImpl.getInstance();
    private Integer initialBalance;
    private Boolean finishedGame;
    private boolean exitGame;

    public Game(Long gameId, Difficulty difficulty, int victoryAmount, int turn, Queue<PlayerImplement> players, Integer initialBalance) {
        this.gameId = gameId;
        this.difficulty = difficulty;
        this.victoryAmount = victoryAmount;
        this.initialBalance = initialBalance;
        this.turn = turn;
        finishedGame = false;
        exitGame = false;
    }


    public void startGame() {

        if (this.turn == 1) {
            definerPlayerPosition();
        }

        while (!finishedGame && !exitGame) {
            gameTurn();
            saveGame();
            isFinished();
        }

    }

    public boolean isFinished() {
        if (victoryAmount != 0) {
            for (PlayerImplement p : Board.getInstance().getPlayers()) {
                if (p.getBalance() >= victoryAmount) {
                    finishedGame = true;
                    LetterByLetterPrinter.println(p.getPlayerName() + " es el ganador con un saldo de "
                            + p.getBalance() + "!!"+  "  JUEGO FINALIZADO!!");
                    break;
                }
            }
        }
        for (PlayerImplement p : Board.getInstance().getPlayers()){
            if(p.getStrategy() instanceof HumanPlayerStrategy && p.isLoser()){
                finishedGame = true;
                LetterByLetterPrinter.println(p.getPlayerName()+ "no tiene fondos, ni puede continuar!!." +
                        "  JUEGO FINALIZADO!!");
            }
        }

        List<PlayerImplement> playersRemaining = Board.getInstance().getPlayers().stream().filter(player -> !player.isLoser()).toList();
        if(playersRemaining.size() == 1){
            LetterByLetterPrinter.println(playersRemaining.get(0).getPlayerName()+" es el último en pie y el gran ganador!!"+ " JUEGO FINALIZADO!!");
            finishedGame = true;

        }

        return finishedGame;
    }

    private void gameTurn() {
        playerTurn = Board.getInstance().getLastPlayers();
        if (playerTurn.isLoser()) {
            return;
        }
        LetterByLetterPrinter.println(playerTurn.getPlayerName() + " comienza su turno");
        Integer countMirrorDices = 0;
        DiceResult diceResult;
        do {
            if (isRestSquare())
                break;
            diceResult = dice.rollDice();
            DiceImpl.getInstance().setResult(diceResult);
            LetterByLetterPrinter.println(playerTurn.getPlayerName()
                    + " sacó " + diceResult.getSumDices() + " en los dados.");

            if (diceResult.getIsMirror()) {
                LetterByLetterPrinter.println(playerTurn.getPlayerName()
                        + " obtuvo dados iguales.");
                ++countMirrorDices;
            }
            if (playerTurn.isInJail()) {
                if (!diceResult.getIsMirror()) {
                    LetterByLetterPrinter.println(playerTurn.getPlayerName() + " no obtuvo dobles, continúa en la cárcel.");
                    break;
                } else {
                    LetterByLetterPrinter.println(playerTurn.getPlayerName() + " salió de la cárcel!!");
                    playerTurn.setInJail(false);
                }
            }
            if (countMirrorDices == 3) {
                LetterByLetterPrinter.println(playerTurn.getPlayerName() + " obtuvo dados iguales tres veces consecutivas." +
                        "¡Va a la cárcel por suertudo!");
                playerTurn.goToJail();
                break;
            }
            playerTurn.calculateSteps(diceResult.getSumDices());
            playerTurn.executeSquare();
            if(playerTurn.getSquare() instanceof FreeParkingSquare) {
                diceResult.setIsMirror(true);
                continue;
            }
            exitGame = playerTurn.decide();
            if(exitGame){
                break;
            }
            playerTurn.showStats();

        } while (diceResult.getIsMirror());
        playerTurnToBoard();
        turn++;
    }

    private void playerTurnToBoard() {
        Board.getInstance().setPlayer(playerTurn);
    }

    private Boolean isRestSquare() {
        if (playerTurn.getSquare() instanceof RestSquare) {
            if (playerTurn.getRestTurnCounter() == 2) {
                playerTurn.setRestTurnCounter(0);
                LetterByLetterPrinter.println(playerTurn.getPlayerName()
                        + " ha descansado el máximo de turnos permitidos. Continua tu turno.");
                return false;
            }
            return playerTurn.restYesOrNo();
        }
        return false;
    }

    private void definerPlayerPosition() {
        List<PlayerImplement> players = new ArrayList<>();
        while (true) {
            PlayerImplement playerImplement = Board.getInstance().getLastPlayers();
            if (playerImplement == null) {
                break;
            }
            players.add(playerImplement);
        }
        for (PlayerImplement p : players) {
            DiceResult diceResult = dice.rollDice();
            p.setDiceResult(diceResult.getSumDices());
        }
        players.sort(Comparator.comparingInt(PlayerImplement::getDiceResult).reversed());
        for (PlayerImplement playerImplement : players) {
            Board.getInstance().setPlayer(playerImplement);
        }
    }


    private void saveGame() {
        Board.getInstance().save();
    }

}
