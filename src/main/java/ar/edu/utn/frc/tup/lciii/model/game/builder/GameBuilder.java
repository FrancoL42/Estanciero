package ar.edu.utn.frc.tup.lciii.model.game.builder;


import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.service.GameService;


public interface GameBuilder {
    /**
     * Agrega el mazo  de cartas de Suerte
     */
    void addChanceCards();
    /**
     * Agrega el mazo de cartas  de Destino
     */
    void addDestinyCards();
    /**
     * Agrega una lista de jugadores
     */
    void addPlayers(Long gameid);
    /**
     * Agrega una lista las casillas
     */
    void addSquares();

    /**
     * Agrega una lista las propiedades
     */
    void addProperties();

    /**
     *  Devuelve el game resultante
     * @return Game resultante de la construido por el builder
     */
    Game getGame();

}
