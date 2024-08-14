package ar.edu.utn.frc.tup.lciii.service;


import ar.edu.utn.frc.tup.lciii.dtos.GamesDto;
import ar.edu.utn.frc.tup.lciii.dtos.ListGameInfoDto;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.service.impl.CardServiceImpl;

import java.util.List;

public interface GameService {
    /*
    Al getAllGame falta agregarle que busque por usuario (ID);
     */
    Game getGame(Long idUsuario);
    /*
    Debería recibir una lista de cartas, usuario, lista de jugadores, casillas, estado de casillas,
    propiedades y sus estados, dinero.
     */
    ListGameInfoDto newGame(Long idUser, Integer balance, Integer victoryAmount, Difficulty difficulty, List<PlayerImplement> players);
    /*
        Carga un juego seleccionado por el usuario/jugador.
     */
    void loadGame(Long idGame);

    void save(Long gameId,List<AbstractProperty> properties, List<AbstractCard> cards, List<PlayerImplement> playerServices);

    List<GamesDto> getAllGames(User user);


}
