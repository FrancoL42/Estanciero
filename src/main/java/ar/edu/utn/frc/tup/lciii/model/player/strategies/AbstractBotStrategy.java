package ar.edu.utn.frc.tup.lciii.model.player.strategies;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.property.states.BougthPropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractBotStrategy implements Strategy {
    private static final Integer BALANCE_THRESHOLD = 1500;

    protected List<String> PREFERRED_PROVINCES;
    /*Campos comprados provincias preferidas*/
    protected int preferredFieldsBought;


    @Override
    public boolean useCard(PlayerImplement player) {
        if (!player.getCards().isEmpty()) {
            removeCard(player);
            player.setInJail(false);
            LetterByLetterPrinter.println(player.getPlayerName() + " uso una carta para salir de la carcel");
            return true;
        }
        return false;
    }

    private void removeCard(PlayerImplement player) {
        AbstractCard c = (AbstractCard) player.getCards().remove(0);
        if (c.getCardType() == 1) {
            Board.getInstance().setDestinyCard(c);
        } else {
            Board.getInstance().setChanceCard(c);
        }
    }

    @Override
    public void sellFarm(PlayerImplement player) {
        //Obtener las field properties con farms
        List<FieldProperty> properties = getListProperties(player, FieldProperty.class).stream()
                .filter(p -> p.getFarmCount() > 0).toList();
        properties = new ArrayList<>(properties);
        //Ordenar para vender farms de la fields mas baratas primero.
        properties.sort(Comparator.comparingInt(FieldProperty::getPropertyValue));
        // Vende una farm por field, recordando que por reglamento no puede haber diferencia
        // de mas de 1 en las zonas de la misma province.
        for (int i = 0; player.getBalance() < 0; i++) {
            //reset del contador cuando ya se vendio una farm de todas las fields.
            if (i == properties.size())
                i = 0;
            FieldProperty fp = properties.get(i);
            if (fp.getFarmCount() != 0) {
                fp.setFarmCount(fp.getFarmCount() - 1);
                LetterByLetterPrinter.println(player.getPlayerName() + " vendio una chacra de " + fp.getName());
                player.setBalance(player.getBalance() + fp.getFarmValue());
            }
            if (!player.hasAnyFarm())
                break;

        }
    }

    /* Version basica de buyFarm llamada desde strategies concretas.*/
    public void buyFarm(PlayerImplement player, double percentageLimit) {
        List<FieldProperty> listFields = getListProperties(player, FieldProperty.class);
        //Ordenar de mayor a menor para intentar comprar farms.
        listFields = new ArrayList<>(listFields);
        listFields.sort(Comparator.comparingInt(FieldProperty::getPropertyValue).reversed());
        for (FieldProperty property : listFields) {
            if (property.checkOwnsAllZonesInProvince()) {
                int budget = (int) (player.getBalance() * percentageLimit);
                if (budget >= property.getFarmValue() && property.getFarmCount() < 5) {
                    property.setFarmCount(property.getFarmCount() + 1);
                    player.updateBalance(-property.getFarmValue());
                    if (property.getFarmCount() < 5)
                        LetterByLetterPrinter.println(player.getPlayerName() + " compra chacra en " + property.getName());
                    else
                        LetterByLetterPrinter.println(player.getPlayerName() + " compra estancia " + property.getName());
                }
            }
        }
    }

    @Override
    public void validateBalance(List<Property> properties, PlayerImplement player) {
        LetterByLetterPrinter.println(player.getPlayerName() + "no tiene suficientes fondos para pagar.");
        if (!player.getCards().isEmpty()) {
            sellCard(player);
        }
        if (player.hasAnyFarm()) {
            sellFarm(player);
        }
        if (!player.getProperties().isEmpty()) {
            sellProperty(player);
        }
        if (!player.areAllPropertiesMortgaged()) {
            mortgageProperty(player);
        }
        if (player.getBalance() < 0) {
            player.setLoser(true);
            LetterByLetterPrinter.println(player.getPlayerName() + "no tiene dinero y pierde el juego.");
        }
    }


@Override
public void sellCard(PlayerImplement player) {
    List<Card> lst = player.getCards();
    while (!player.getCards().isEmpty()) {
        for (Card c : lst) {
            if (player.getBalance() >= 0)
                break;
            removeCard(player);
            player.setBalance(1000 + player.getBalance());
            LetterByLetterPrinter.println(player.getPlayerName() + " vende  una carta para salir de la carcel." +
                    "Obiene $1000.");
            if (lst.isEmpty()) {
                break;
            }
        }
    }
}


@Override
public void sellProperty(PlayerImplement player) {
    List<AbstractProperty> listProperties = getSellableProperties(player);
    listProperties = new ArrayList<>(listProperties);
    listProperties.sort(Comparator.comparingInt(AbstractProperty::getPropertyValue));
    for (AbstractProperty property : listProperties) {
        if (player.getBalance() >= 0)
            break;
        if (property instanceof FieldProperty) {
            //Solo vende los campos de las provincias no preferidas
            if (!PREFERRED_PROVINCES.contains(((FieldProperty) property).getProvince())) {
                sell(player, property);
            }
        } else sell(player, property);
    }
}


private void sell(PlayerImplement player, AbstractProperty property) {
    LetterByLetterPrinter.println(player.getPlayerName() + " vende " + property.getName());
    player.getProperties().remove(property);
    property.setOwner(null);
    property.nextState();
    player.setBalance(player.getBalance() + property.getPropertyValue());
}

protected void buy(PlayerImplement player, AbstractProperty p) {
    if (player.getBalance() >= p.getPropertyValue()) {
        p.setOwner(player);
        p.nextState();
        player.getProperties().add(p);
        p.setState(new BougthPropertyState());
        LetterByLetterPrinter.println(player.getPlayerName() + " compra " + p.getName());
        player.updateBalance(-p.getPropertyValue());
        preferredFieldsBought++;
    }
}

@Override
public void mortgageProperty(PlayerImplement player) {
    List<AbstractProperty> listProperties = player.getProperties().stream()
            .map(p -> (AbstractProperty) p)
            .filter(p -> p.getState() instanceof BougthPropertyState)
            .toList();
    for (AbstractProperty property : listProperties) {
        if (player.getBalance() >= 0)
            break;
        if (property instanceof FieldProperty) {
            if (PREFERRED_PROVINCES.contains(((FieldProperty) property).getProvince())) {
                mortage(player, property);
            }
        } else {
            mortage(player, property);
        }
    }
}

private List<AbstractProperty> getSellableProperties(PlayerImplement player) {
    List<AbstractProperty> sellableList = new ArrayList<>();
    for (Property p : player.getProperties()) {
        if (!(p instanceof FieldProperty))
            sellableList.add((AbstractProperty) p);
        if (p instanceof FieldProperty && ((FieldProperty) p).getFarmCount() == 0)
            sellableList.add((AbstractProperty) p);
    }
    return sellableList;
}

private void mortage(PlayerImplement player, AbstractProperty property) {
    property.setState(new MortgagePropertyState());
    int value = property.getPropertyValue() / 2 * 9 / 10; //el banco se cobra el 10% por adelantado.
    player.setBalance(value + player.getBalance());
    LetterByLetterPrinter.println(player.getPlayerName() + " hipoteca " + property.getName());
}

@Override
public void goToJail(PlayerImplement player) {
    if (useCard(player)) {
        return;
    }
    if (player.getBalance() >= 1000) {
        player.updateBalance(-1000);
        LetterByLetterPrinter.println(player.getPlayerName() + " pagó 1000 y salió de la carcel");
        player.setInJail(false);
    } else {
        LetterByLetterPrinter.println(player.getPlayerName() +
                " no tiene suficiente dinero para pagar la fianza.");
    }

}

@Override
public boolean decide(PlayerImplement player) {
    payOffMortgages(player);
    buyFarm(player);
    return false;
}

@Override
public void payOffMortgages(PlayerImplement player) {
    for (Property property : player.getProperties()) {
        if (((AbstractProperty) property).getState() instanceof MortgagePropertyState)
            if (player.getBalance() >= ((AbstractProperty) property).getPropertyValue() / 2) {
                property.setState(new BougthPropertyState());
                LetterByLetterPrinter.println(player.getPlayerName() + " levanta la hipoteca de " + ((AbstractProperty) property).getName());
                player.updateBalance(-((AbstractProperty) property).getPropertyValue() / 2);
            }
    }
}

protected <T> List<T> getListProperties(PlayerImplement player, Class<T> propertyClass) {
    List<T> listp = new ArrayList<>();
    for (Property p : player.getProperties()) {
        if (p.getClass().equals(propertyClass)) {
            listp.add((T) p);
        }
    }
    return listp;
}
}
