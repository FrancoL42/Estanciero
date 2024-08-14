package ar.edu.utn.frc.tup.lciii.model.console;

import ar.edu.utn.frc.tup.lciii.dtos.GamesDto;

import java.util.List;

public interface Menu {

    public void start();
    public User login();

    public void selectLoadOrNewGame();

    public void selectDifficult(Integer victoryAmount);

    public void findGamesUser();

    public Long selectGame(List<GamesDto> games);

    public void selectVictoryMode();

    public void createAndExecuteGame();
}
