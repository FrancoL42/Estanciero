package ar.edu.utn.frc.tup.lciii.model.player.strategies;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.property.states.BougthPropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import ar.edu.utn.frc.tup.lciii.model.square.PropertySquare;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class HumanPlayerStrategy implements Strategy {
    Console console = new Console();


    @Override
    public void buyProperty(PlayerImplement player, Property property) {
        AbstractProperty p = (AbstractProperty) property;
        LetterByLetterPrinter.println("¿Desea comprar "+((AbstractProperty) property).getName()
                +" de valor $"+((AbstractProperty) property).getPropertyValue()+"? (Saldo:$" +player.getBalance()+")");
        if(console.inputYesNo()) {
            if (player.getBalance() >= p.getPropertyValue()) {
                p.setOwner(player);
                player.getProperties().add(property);
                property.setState(new BougthPropertyState());
                player.updateBalance(-p.getPropertyValue());
                LetterByLetterPrinter.println(player.getPlayerName() + " compra " + p.getName());
            } else
                LetterByLetterPrinter.println(player.getPlayerName() + " no tiene fondos suficientes para comprar la propiedad.");
        }
    }

    @Override
    public void mortgageProperty(PlayerImplement player) {
        //Obtener hipotecables
        List<Property> mortgageableProperties = getMortgageableProperties(player);
        if (mortgageableProperties.isEmpty()) {
            LetterByLetterPrinter.println("No tienes propiedades para hipotecar.");
            return;
        }
        //Obtener input de cual hipotecar
        int selectedOption = getSelectedOption(mortgageableProperties);
        //Hipotecar
        Property toMortgage = mortgageableProperties.get(selectedOption - 1);
        toMortgage.setState(new MortgagePropertyState());
        int value = ((AbstractProperty) toMortgage).getPropertyValue() / 2 * 9 / 10; //el banco se cobra el 10% por adelantado.
        player.updateBalance(value);
    }

    /* Mandar a consola opciones para obtener el input */
    private <T> int getSelectedOption(List<T> list) {
        LinkedHashMap<Integer, String> options = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            options.put(i + 1, list.get(i).toString());
        }
        int selectedOption = console.selectOptions(options);
        return selectedOption;
    }

    /*Obtencion de hipotecables*/
    private List<Property> getMortgageableProperties(PlayerImplement player) {
        //Solo son hipotecables Campos sin Chacras/Estancia + Ferrocarriles + Compañias
        List<Property> mortgageableProperties = new ArrayList<>();
        for (Property p : player.getProperties()) {
            if (((AbstractProperty) p).getState() instanceof BougthPropertyState) {
                if (p instanceof FieldProperty) {
                    if (((FieldProperty) p).getFarmCount() == 0)
                        mortgageableProperties.add(p);
                } else
                    mortgageableProperties.add(p);
            }
        }
        return mortgageableProperties;
    }

    @Override
    public boolean useCard(PlayerImplement player) {
        if (player.getCards().isEmpty()) {
            LetterByLetterPrinter.println("No tiene cartas para usar.");
            return false;
        }
        LetterByLetterPrinter.println("¿quieres usar una carta para salir?");
        boolean choice = console.inputYesNo();
        if (choice) {
            LetterByLetterPrinter.println("Seleccione una carta a usar: ");
            int seleccion = getSelectedOption(player.getCards());
            player.setInJail(false);
            removeCard(player, seleccion - 1);
            LetterByLetterPrinter.println(player.getPlayerName() + " sale de la carcel.");
            return true;
        }
        return false;
    }


    private void removeCard(PlayerImplement player, int index) {
        AbstractCard c = (AbstractCard) player.getCards().remove(index);
        if (c.getCardType() == 1) {
            Board.getInstance().setDestinyCard(c);
        } else {
            Board.getInstance().setChanceCard(c);
        }
    }

    @Override
    public void buyFarm(PlayerImplement player) {
        List<FieldProperty> buyableFarmProperties = getBuyableFarmProperties(player);
        if (buyableFarmProperties.isEmpty()) {
            LetterByLetterPrinter.println("No tienes propiedades a las cuales comprar chacras/estancias.");
            return;
        }
        int selectedOption = getSelectedOption(buyableFarmProperties);
        FieldProperty fieldToBuyFarms = buyableFarmProperties.get(selectedOption - 1);
        if (fieldToBuyFarms.getFarmCount() == 5) {
            LetterByLetterPrinter.println("Ya tiene una estancia.No puede comprar mas.");
            return;
        }
        LetterByLetterPrinter.println("¿Cuantas chacras quiere comprar?. Actualmente tiene: " + fieldToBuyFarms.getFarmCount());

        int cantidad;
        do {
            LetterByLetterPrinter.println("Ingrese un maximo de " + (5 - fieldToBuyFarms.getFarmCount()) + " incluyendo estancia.");
            cantidad = console.inputInt();
        } while (cantidad + fieldToBuyFarms.getFarmCount() > 5);

        if (player.getBalance() >= cantidad * fieldToBuyFarms.getFarmValue()) {
            fieldToBuyFarms.setFarmCount(fieldToBuyFarms.getFarmCount() + cantidad);
            player.updateBalance(-cantidad * fieldToBuyFarms.getFarmValue());
        } else
            LetterByLetterPrinter.println("No tiene suficiente dinero para comprar esa cantidad de chacras/estancia.");

    }

    private List<FieldProperty> getBuyableFarmProperties(PlayerImplement player) {
        List<FieldProperty> buyableFarmProperties = new ArrayList<>();
        for (Property p : player.getProperties()) {
            if ((p instanceof FieldProperty) && ((FieldProperty) p).checkOwnsAllZonesInProvince())
                if (((FieldProperty) p).getState().getClass().equals(BougthPropertyState.class))
                    buyableFarmProperties.add((FieldProperty) p);
        }
        return buyableFarmProperties;
    }

    @Override
    public void sellFarm(PlayerImplement player) {
        if (!player.hasAnyFarm()) {
            LetterByLetterPrinter.println("No tienes chacras para vender");
            return;
        }
        List<FieldProperty> propertiesWithFarms = getPropertiesWithFarms(player);
        Integer response = getSelectedOption(propertiesWithFarms);
        FieldProperty fieldToSellFarms = propertiesWithFarms.get(response - 1);
        LetterByLetterPrinter.println("¿Cuantas chacras quiere vender?. Actualmente tiene: " + fieldToSellFarms.getFarmCount());
        Integer cant;
        while (true) {
            cant = console.inputInt();
            if (cant < fieldToSellFarms.getFarmCount()) {
                break;
            }
        }
        fieldToSellFarms.setFarmCount(fieldToSellFarms.getFarmCount() - cant);
        player.updateBalance(cant * fieldToSellFarms.getFarmValue());
    }

    private List<FieldProperty> getPropertiesWithFarms(PlayerImplement player) {
        List<FieldProperty> propertiesWithFarms = new ArrayList<>();
        for (Property p : player.getProperties()) {
            if (p instanceof FieldProperty) {
                if (((FieldProperty) p).getFarmCount() > 0)
                    propertiesWithFarms.add((FieldProperty) p);

            }
        }
        return propertiesWithFarms;
    }

    @Override
    public void validateBalance(List<Property> properties, PlayerImplement p) {
        LetterByLetterPrinter.println("No tienes dinero suficiente");
        LinkedHashMap<Integer, String> options = new LinkedHashMap<>();

        if (p.areAllPropertiesMortgaged()) {
            options.put(1, "Hipotecar Propiedad.");
        }
        if (p.hasAnyFarm()) {
            options.put(2, "Vender chacras.");
        }
        if (!p.getCards().isEmpty()) {
            options.put(3, "Vender Tarjeta");
        }
        if (!p.getProperties().isEmpty()) {
            options.put(4, "Vender propiedad");
        }
        if (options.isEmpty()) {
            if (p.getBalance() < 0) {
                p.setLoser(true);
            }
            return;
        }
        while (true) {
            Integer selectOption = console.selectOptions(options);
            switch (selectOption) {
                case 1 -> p.mortgageProperty();
                case 2 -> p.sellFarm();
                case 3 -> p.sellCard();
                case 4 -> p.sellProperty();
            }
            if (selectOption != null) {
                break;
            }
        }
    }


    @Override
    public void sellCard(PlayerImplement p) {
        if (p.getCards().isEmpty()) {
            LetterByLetterPrinter.println("No tienes cartas para vender.");
            return;
        }
        LetterByLetterPrinter.println("¿Que carta deseas vender?");
        int response = getSelectedOption(p.getCards());
        removeCard(p, response - 1);
        p.updateBalance(1000);
        LetterByLetterPrinter.println("Vendes la carta.Obtienes $1000");
    }

    @Override
    public void sellProperty(PlayerImplement p) {
        if (getSellableProperties(p).isEmpty()) {
            LetterByLetterPrinter.println("No tienes propiedades para vender");
            return;
        }
        boolean choice;
        do{
        choice = false;
        LetterByLetterPrinter.println("Seleccione propiedad a vender:");
        int selectProperty = getSelectedOption(getSellableProperties(p));
        Property property = getSellableProperties(p).remove(selectProperty - 1);
        p.getProperties().remove(property);
        ((AbstractProperty) property).setOwner(null);
        property.setState(new FreePropertyState());
        LetterByLetterPrinter.println("Vendiste " +((AbstractProperty) property).getName());
        p.updateBalance(((AbstractProperty) property).getPropertyValue());
        if (!getSellableProperties(p).isEmpty()){
             LetterByLetterPrinter.println("¿desea vender otra propiedad?");
             choice = console.inputYesNo();
        }
        }while(choice);
    }

    private List<AbstractProperty> getSellableProperties(PlayerImplement player) {
        List<AbstractProperty> sellableList = new ArrayList<>();
        for(Property p : player.getProperties()){
            if(!(p instanceof FieldProperty))
                sellableList.add((AbstractProperty) p);
            if(p instanceof FieldProperty && ((FieldProperty) p).getFarmCount()==0)
                sellableList.add((AbstractProperty) p);
        }
        return sellableList;
    }


    @Override
    public Boolean restYesNo(PlayerImplement player) {
        LetterByLetterPrinter.println("¿Quieres descansar un turno?");
        boolean choice = console.inputYesNo();
        if (choice) {
            LetterByLetterPrinter.println("Perfecto! Tomate un merecido descanso!");
            player.setRestTurnCounter(player.getRestTurnCounter() + 1);
        } else {
            LetterByLetterPrinter.println("No quieres descansar. Tiras los dados.");
        }
        return choice;
    }

    @Override
    public void payOffMortgages(PlayerImplement player) {
        if (player.getProperties().isEmpty()) {
            LetterByLetterPrinter.println("No tienes propiedades para levantar hipoteca.");
            return;
        }
        boolean choice;
        do {
            List<Property> properties = player.getProperties().stream()
                    .filter(p -> ((AbstractProperty) p).getState() instanceof MortgagePropertyState).toList();
            if (properties.isEmpty()) {
                LetterByLetterPrinter.println("No tienes propiedades para levantar hipoteca.");
                break;
            }
            int option = getSelectedOption(properties);
            Property property = properties.get(option - 1);
            if (player.getBalance() >= ((AbstractProperty) property).getPropertyValue() / 2) {
                property.setState(new BougthPropertyState());
                player.updateBalance((-((AbstractProperty) property).getPropertyValue() / 2));
                LetterByLetterPrinter.println("¿Desea levantar hipoteca de otra propiedad?");
                choice = console.inputYesNo();
            } else {
                LetterByLetterPrinter.println("No tiene suficiente dinero para levantar hipoteca");
                LetterByLetterPrinter.println("¿Desea levantar hipoteca de otra propiedad?");
                choice = console.inputYesNo();
            }
        } while (choice);

    }

    @Override
    public void goToJail(PlayerImplement player) {
        if(useCard(player)){
            return;
        }
        LetterByLetterPrinter.println("¿quiere pagar una multa de 1000?");
        boolean choice = console.inputYesNo();
        if (choice) {
            if (player.getBalance() >= 1000) {
                player.updateBalance(-1000);
                LetterByLetterPrinter.println("Pagaste 1000 y saliste de la carcel");
                player.setInJail(false);
            } else {
                LetterByLetterPrinter.println("No tienes suficiente dinero para pagar la fianza.");
            }
        }
    }

    @Override
    public boolean decide(PlayerImplement player) {
        boolean exitGame = false;
        LetterByLetterPrinter.println("Elija una opcion: ");
        List<String> options = List.of("Vender Propiedad", "Vender Tarjetas", "Comprar Chacras/Estancia",
                "Vender Chacras/Estancia", "Hipotecar Propiedad", "Levantar Hipoteca", "Terminar Turno","Salir del Juego");
        boolean endTurn = false;
        do {
            int input = getSelectedOption(options);
            switch (input) {
                case 1 -> sellProperty(player);
                case 2 -> sellCard(player);
                case 3 -> buyFarm(player);
                case 4 -> sellFarm(player);
                case 5 -> mortgageProperty(player);
                case 6 -> payOffMortgages(player);
                case 7 -> endTurn = true;
                case 8 -> {endTurn = true; exitGame=true;}
            }
        } while (!endTurn);
        return exitGame;
    }


}
