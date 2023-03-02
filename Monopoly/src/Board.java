import java.util.*;
import java.io.*;

public class Board {

    public void squareActionNull(Square square, Player player) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        Input input = new Input();
        int rd;
        switch (square.type) {
            case "Chest" -> {
                int plusMinus = input.RandomNumber(0, 1) * 2 - 1;
                rd = input.RandomNumber(50, 200);
                if (plusMinus < 0)
                    System.out.println(player.name + " has lost $" + rd + "!");
                else System.out.println(player.name + " has won $" + rd + "!");
                player.money += rd * plusMinus;
            }
            case "Tax" -> {
                System.out.println("The " + square.name + " is $" + square.price + ".");
                player.money -= square.price;
            }
            case "Rail" -> {
                System.out.println("The " + square.name + "'s price is $" + square.price + ". Would you like to purchase it?");
                System.out.println("Press [y] if yes, or any key otherwise!");
                switch (sc.nextLine()) {
                    case "y":
                        System.out.println(player.name + " has purchased " + square.name + "!");
                        player.money -= square.price;
                        player.properties.add(square.name);
                        square.owner = player.name;
                        player.railsOwned++;
                        break;
                    default:
                        System.out.println(square.name + " remains unowned.");
                }
            }
            case "Chance" -> {
                rd = input.RandomNumber(0, 39);
                player.position = rd;
            }
            case "GTJail" -> {
                player.position = 10;
                player.inJail = true;
            }
            case "Inc" -> {
                System.out.println("The " + square.name + "'s price is $" + square.price + ". Would you like to purchase it?");
                System.out.println("Press [y] if yes, or any key otherwise!");
                switch (sc.nextLine()) {
                    case "y":
                        System.out.println(player.name + " has purchased " + square.name + "!");
                        player.money -= square.price;
                        player.properties.add(square.name);
                        square.owner = player.name;
                        player.companiesOwned += 1;
                        break;
                    default:
                        System.out.println(square.name + " remains unowned.");
                }
            }
            case "Property" -> {
                System.out.println("The " + square.name + "'s price is $" + square.price + ". Would you like to purchase it?");
                System.out.println("Press [y] if yes, or any key otherwise!");
                switch (sc.nextLine()) {
                    case "y" -> {
                        System.out.println(player.name + " has purchased " + square.name + "!");
                        player.money -= square.price;
                        player.properties.add(square.name);
                        square.owner = player.name;
                        player.propertiesOwned += 1;
                    }
                    default -> System.out.println(square.name + " remains unowned.");
                }
            }
        }
    }

    public void squareActionOwned(int dice, Square square, Player[] players, Player player) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        if(player.name == square.owner)
        {
            System.out.println(player.name + " already owns this square!");
        }
        else {
            switch (square.type) {

                case "Rail":
                    for (Player value : players) {
                        if (square.owner.equals(value.name)) {
                            switch (value.railsOwned) {
                                case 1 -> {
                                    player.money -= 25;
                                    value.money += 25;
                                    System.out.println(square.name + " is owned by " + square.owner + ", who owns one card of this type.");
                                    System.out.println("The rent will be 25.");
                                }
                                case 2 -> {
                                    player.money -= 50;
                                    value.money += 50;
                                    System.out.println(square.name + " is owned by " + square.owner + ", who owns two cards of this type.");
                                    System.out.println("The rent will be 50.");
                                }
                                case 3 -> {
                                    player.money -= 100;
                                    value.money += 100;
                                    System.out.println(square.name + " is owned by " + square.owner + ", who owns three cards of this type.");
                                    System.out.println("The rent will be 100.");
                                }
                                case 4 -> {
                                    player.money -= 200;
                                    value.money += 200;
                                    System.out.println(square.name + " is owned by " + square.owner + ", who owns four cards of this type.");
                                    System.out.println("The rent will be 200.");
                                }
                            }
                        }
                    }
                    break;

                case "Inc":
                    for (Player value : players) {
                        if (square.owner.equals(value.name)) {
                            int r;
                            switch (value.companiesOwned) {
                                case 1 -> {
                                    player.money -= 4 * dice;
                                    value.money += 4 * dice;
                                    r = 4 * dice;
                                    System.out.println(square.name + " is owned by " + square.owner + ", who owns one card of this type.");
                                    System.out.println("The rent will be four times the dice value, in this case 4 x " + dice + ", so $" + r + ".");
                                }
                                case 2 -> {
                                    player.money -= 10 * dice;
                                    value.money += 10 * dice;
                                    r = 10 * dice;
                                    System.out.println(square.name + " is owned by " + square.owner + ", who owns two cards of this type.");
                                    System.out.println("The rent will be ten times the dice value, in this case 10 x " + dice + ", so $" + r + ".");
                                }
                            }
                        }
                    }
                    break;

                case "Property":
                    for (Player value : players) {
                        if (square.owner.equals(value.name)) {
                            int inRent = square.rent;
                            square.rent *= value.propertiesOwned;
                            player.money -= square.rent;
                            value.money += square.rent;
                            System.out.println(square.name + " is owned by " + square.owner + "! The rent is " + value.propertiesOwned + " times the rent of " + inRent + ", for a total of $" + square.rent + ".");
                        }
                    }
                    break;
            }
        }
    }
    Input input = new Input();
    Square[] square = new Square[40];
    public Square[] getBoard()
    {
        String[] squareName = input.StringArray("Resources/Squares.txt");
        String[] squarePrice = input.StringArray("Resources/Prices.txt");
        String[] squareType = input.StringArray("Resources/Types.txt");

        for (int i = 0; i < squareName.length; i++)
        {
            square[i] = new Square(squareName[i], i, squareType[i]);
            square[i].price = Integer.parseInt(squarePrice[i]);
            square[i].rent = square[i].price / 10;

        }
        return square;

    }



}
