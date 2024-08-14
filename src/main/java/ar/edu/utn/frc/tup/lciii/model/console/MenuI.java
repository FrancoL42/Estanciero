package ar.edu.utn.frc.tup.lciii.model.console;

import ar.edu.utn.frc.tup.lciii.dtos.GamesDto;
import ar.edu.utn.frc.tup.lciii.entity.UserEntity;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.game.builder.*;
import ar.edu.utn.frc.tup.lciii.service.GameService;
import ar.edu.utn.frc.tup.lciii.service.UserService;
import ar.edu.utn.frc.tup.lciii.service.impl.GameServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.UserServiceImpl;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
public class MenuI implements Menu{

    //usuario que se logeo, usado para buscar los games o crear el game nuevo.
    //private User user
    private User user;
    private UserService userService = new UserServiceImpl();

    private GameService gameService = new GameServiceImpl();
    //private Game game
    private Console console = new Console();
    private  Integer victoryModeAmount = null;
    //dificultad debe ser un builder para construir el game.
    private GameBuilder game;
    private BuilderDirector director = new BuilderDirector();
    private int dificultad;
    public Integer optionLogin() {
        LetterByLetterPrinter.println("Bienvenido al Estanciero.");
        LinkedHashMap<Integer, String> listOptions = new LinkedHashMap<>();
        listOptions.put(1, "Ingresar con usuario");
        listOptions.put(2, "Registrarse");
        listOptions.put(3, "Salir");
        while (true){
            Integer selectedOption = console.selectOptions(listOptions);
            if(selectedOption != null){
                return selectedOption;
            }
            LetterByLetterPrinter.println("Ingrese una opción válida");
        }
    }

    @Override
    public void start() {
        while (true){
          this.user=  login();
          if(user == null){
              return;
          }
          selectLoadOrNewGame();
          createAndExecuteGame();
        }
    }

    //Debe retornar el usuario registrado o logeado para buscar los juegos o crearlos en la base de datos.
    @Override
    public User login() {
        User user = null;
        while (true){
            Integer optionLogin= optionLogin();
            switch (optionLogin) {
                case 1:

                    user = findUser();
                    if(user != null){
                        return user;
                    } break;

                case 2:
                    user= register();
                    if(user !=null){
                        return user;
                    }break;
                case 3:
                    return user;
                default:
                    LetterByLetterPrinter.println("Debe ingresar una opcion válida");
                    break;
            }
        }


    }

    public User findUser() {

        LetterByLetterPrinter.println("Ingrese usuario");
        String nombre = console.inputString().toLowerCase();

        LetterByLetterPrinter.println("Ingrese password");
        String password = console.inputString().toLowerCase();

        User user = userService.validateUser(nombre,password);
        if(user != null){
            return user;
        }
        return null;

    }
    public User register() {
        while (true){
            LetterByLetterPrinter.println("Ingrese el nombre del usuario a registrar.");
            String newUser = console.inputString().toLowerCase();
            LetterByLetterPrinter.println("Ingrese contraseña.");
                String password = console.inputString();
                while ( password.length()<6){
                    LetterByLetterPrinter.println("La contraseña debe ser al menos de 6 caracteres.");
                    password = console.inputString();
                    //crear, guardar y retornar
                }
            User user = userService.postUser(newUser, password );
            if(user == null){
                LetterByLetterPrinter.println("El usuario ya existe");
            } else{
                LetterByLetterPrinter.println("Se registro el usuario correctamente.");
                return user;
            }

        }
    }

    @Override
    public void selectLoadOrNewGame() {
        LinkedHashMap<Integer, String> listOption = new LinkedHashMap<>();
        game = null;
        listOption.put(1,"Nueva Partida");
        listOption.put(2,"Cargar Partida");
        while (true){
            Integer selectedOption = console.selectOptions(listOption);
            switch (selectedOption){
                case 1:
                    selectVictoryMode();
                    selectDifficult(victoryModeAmount);
                    break;
                case 2:
                    findGamesUser();
                    break;
                default:
                    break;
            }
            if (game != null){
                break;
            }

        }
    }
    @Override
    public void selectDifficult(Integer victoryModeAmount) {
        LinkedHashMap<Integer, String> listOption= new LinkedHashMap<>();
        listOption.put(1,"Fácil");
        listOption.put(2,"Medio");
        listOption.put(3, "Difícil");
        Boolean flag = true;
        while (flag){
            LetterByLetterPrinter.println("Ingrese la dificultad");
            Integer selectedOption = console.selectOptions(listOption);
            switch (selectedOption){
                case 1:
                    //Selecciona dificultad facil
                    LetterByLetterPrinter.println("Facil seleccionado");
                    dificultad = 1;
                    game = new EasyGameBuilder(user,5000,victoryModeAmount);
                    flag=false;
                    break;
                case 2:
                    //Selecciona dificultad media
                    LetterByLetterPrinter.println("Medio seleccionado");
                    dificultad = 2;
                    game = new MediumGameBuilder(user,5000,victoryModeAmount);
                    flag=false;
                    break;
                case 3:
                    //Selecciona dificultad dificil
                    LetterByLetterPrinter.println("Difícil seleccionado");
                    dificultad=3;
                    game = new HardGameBuilder(user,5000,victoryModeAmount);
                    flag=false;
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void findGamesUser() {

        System.out.println("Buscar juegos del jugador");
        List<GamesDto> games = gameService.getAllGames(user) ;
        if (games.isEmpty()){
            LetterByLetterPrinter.println("No se encontro ninguna partida.");
            return;
        }
        game = new LoadGameBuilder(selectGame(games));
    }
    /*Aca el jugador debe elegir la partida, el codigo debe buscar las partidas en la base de datos*/
    @Override
    public Long selectGame(List<GamesDto> games) {
        System.out.println("Debe elegir una partida partida");

        LinkedHashMap<Integer,String> listOption = new LinkedHashMap<>();
        games.forEach(g->{
            listOption.put(g.getId().intValue(), g.getName());
        });

        return Long.valueOf(console.selectOptions(listOption));
    }

    @Override
    public void selectVictoryMode() {
        LetterByLetterPrinter.println("¿Desea que el juego tenga victoria por monto?");
        Boolean response = console.inputYesNo();
        if(response){
            while (true){
                LetterByLetterPrinter.println("Ingrese el monto de victoria");
                Integer amount = console.inputInt();
                if(amount>75000 && amount<150000) {
                    victoryModeAmount = amount;
                    return;
                }
                LetterByLetterPrinter.println("Debe ingresar un monto mayor a 75000 y menor a 150000");
            }
        }

    }

    @Override
    public void createAndExecuteGame() {
        Board.getInstance().clear();
        if (game instanceof LoadGameBuilder){
            director.LoadGame(game);

        } else {
            AbstractGameBuilder abstractGameBuilder = (AbstractGameBuilder) game;
            director.NewGame(abstractGameBuilder);
            Board.getInstance().save();
        }
        Game executeGame = game.getGame();
        executeGame.startGame();
    }
}
