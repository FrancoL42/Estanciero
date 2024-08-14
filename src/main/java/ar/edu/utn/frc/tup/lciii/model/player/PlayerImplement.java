package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.Strategy;
import ar.edu.utn.frc.tup.lciii.model.property.*;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.query.sqm.spi.DelegatingSqmSelectionQueryImplementor;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerImplement implements Player {
    private Console console = new Console();

    private Long playerID;

    private String playerName;

    private Integer balance;

    private List<Property> properties = new ArrayList<>();

    private List<Card> cards = new ArrayList<>();

    private Strategy strategy;

    private Square square;

    private Integer orderTurn;

    private int restTurnCounter;

    private boolean isInJail;

    private Integer diceResult;

    private boolean isLoser;

    //uso unico para construir el loadGame
    private Integer numberSquare;

    @Override
    public void useCard() {
        strategy.useCard(this);
    }

    // <editor-fold desc="Stratey Methods">


    @Override
    public void buyProperty(Property p) {
        strategy.buyProperty(this, p);
    }

    @Override
    public void mortgageProperty() {
        strategy.mortgageProperty(this);
    }

    @Override
    public void sellCard() {
        strategy.sellCard(this);
    }

    @Override
    public void buyFarm() {
        strategy.buyFarm(this);
    }

    @Override
    public void sellFarm() {
        strategy.sellFarm(this);
    }

    @Override
    public void sellProperty() {
        strategy.sellProperty(this);
    }

    // </editor-fold>

    @Override
    public void calculateSteps(Integer steps) {
        //Recibe la cantidad de pasos que tiene que desplazarse, y calcula la nueva casilla
        //que tiene que recibir del Board,

        int newPosition = steps + square.getNumberSquare();
        if (newPosition > Board.getInstance().getNumbersOfSquare()) {
            this.balance = this.balance + 5000;
            newPosition = newPosition - Board.getInstance().getNumbersOfSquare()-1;
        }
        if (newPosition < 0) {
            newPosition = Board.getInstance().getNumbersOfSquare() + newPosition;
        }
        this.square = Board.getInstance().getSquare(newPosition);

    }

    @Override
    public void setCardToList(Card card) {
        this.cards.add(card);
    }
    // <editor-fold desc="Update Balance">

    @Override
    public void updateBalance(Integer amount) {
        balance = balance + amount;
        if (balance <= 0) {
            while (balance <= 0 && !isLoser) {
                this.strategy.validateBalance(properties, this);
            }
        }
    }

    public Boolean areAllPropertiesMortgaged() {

        for (Property property : properties) {
            if (property instanceof FieldProperty) {
                if (!(((FieldProperty) property).getState() instanceof MortgagePropertyState)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean hasAnyFarm() {
        if (areAllPropertiesMortgaged()) {
            return false;
        }
        List<Property> prop = properties.stream().filter(p -> p instanceof FieldProperty).filter(p -> ((FieldProperty) p).getFarmCount() > 0).toList();
        if (prop.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean restYesOrNo() {
        return strategy.restYesNo(this);
    }

    @Override
    public boolean decide() {
        return strategy.decide(this);
    }


    public void goToJail() {
        //enváa al jugador a la cárcel
        isInJail = true;
        this.square = Board.getInstance().getJail();
        this.strategy.goToJail(this);
    }

    public void executeSquare() {
        //ejecuta la casilla donde está el jugador.
        square.executeSquare(this);
    }

    public void showStats() {
        LetterByLetterPrinter.println(playerName + " tiene $" + balance + " de saldo.");
        if (!properties.isEmpty()) {
            LetterByLetterPrinter.println(playerName + " tiene las siguientes propiedades:");
            showFieldsStats();
            showRailRoadStats();
            showCompanyStats();
            showCardsStats();
        }
    }

    public void showCardsStats() {
        if(!cards.isEmpty())
          LetterByLetterPrinter.println(playerName + " tiene " +cards.size() + " cartas para salir de la carcel.");
    }

    public void showCompanyStats() {
        List<Property> companyProps = properties.stream().filter(p -> p instanceof CompanyProperty).toList();
        if (!companyProps.isEmpty()) {
            LetterByLetterPrinter.println("Tiene " + companyProps.size() + " compañias.");
            for (Property p : companyProps)
                LetterByLetterPrinter.println(((AbstractProperty) p).getName());
        }
    }

    public void showRailRoadStats() {
        List<Property> trainProps = properties.stream().filter(p -> p instanceof RailroadProperty).toList();
        if (!trainProps.isEmpty()) {
            LetterByLetterPrinter.println("Tiene " + trainProps.size() + " ferrocarriles.");
            for (Property p : trainProps)
                LetterByLetterPrinter.println(((AbstractProperty) p).getName());
        }
    }

    public void showFieldsStats() {
        List<Property> fieldProps = properties.stream().filter(p -> p instanceof FieldProperty).toList();
        if (!fieldProps.isEmpty())
            for (Property p : fieldProps)
                if (((FieldProperty) p).getFarmCount() < 5) {
                    LetterByLetterPrinter.println(((FieldProperty) p).getName()
                            + " con " + ((FieldProperty) p).getFarmCount() + " chacras");
                } else
                    LetterByLetterPrinter.println(((FieldProperty) p).getName()
                            + " con 1 estancia");
    }
}




