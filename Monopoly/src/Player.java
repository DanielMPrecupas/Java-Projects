import java.util.*;
import java.io.*;

public class Player {

    public int position;
    public int money;
    public ArrayList<String> properties;
    public boolean inJail;
    public boolean outofJailCard;
    public String name;
    public int turn;
    public boolean Round;
    public int jailRounds;
    public int propertiesOwned;
    public int railsOwned;
    public int companiesOwned;

    public Player(int position, int money, String name)
    {
        this.position = position;
        this.money = money;
        this.name = name;
        jailRounds = 0;
        propertiesOwned = 0;
        railsOwned = 0;
        companiesOwned = 0;
        properties = new ArrayList<>();
        inJail = false;
        outofJailCard = false;
        Round = false;
    }

}


