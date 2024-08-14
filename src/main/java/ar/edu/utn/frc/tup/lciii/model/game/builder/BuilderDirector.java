package ar.edu.utn.frc.tup.lciii.model.game.builder;

/**
 * Clase que dirige al builder para que arme el game
 */
public class BuilderDirector {
    /**
     * Para un nuevo juego, construir incluyendo el seteo de Id.
     * El victory amount ya esta dado en el constructor del builder.
     * @param builder
     */
    public void NewGame(AbstractGameBuilder builder){
        construct(builder);
    }

    /**
     * Para cargar un juego, contruir incluyendo el seteo de victory ammount.
     * El Id Game ya esta dado en el constructor del builder.
     * @param builder
     */
    public void LoadGame(GameBuilder builder){
        construct(builder);

    }

    /**
     * Dirige al builder de cualquier tipo de juego.
     * @param builder
     */

    private void construct(GameBuilder builder) {
        //Player si o si despues de square porque tiene que setearle la primera posicion en caso de ser newGame;
        builder.addPlayers(builder.getGame().getGameId());
        builder.addProperties();

        builder.addSquares();

        builder.addChanceCards();
        builder.addDestinyCards();


    }
}
