import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class Application {
    public void Game(int playerNo, int roundNo)
    {
        // objects
        Input input = new Input();
        Board board = new Board();

        //main
        int turn = 0;
        int rounds = 0;
        int maxRounds;

        //introduction
        Output out = new Output();
        out.print("Welcome to Monopoly!");
        //out.print("Please choose the number of players: ");
        Scanner sc = new Scanner(System.in);

        //players
        //int no_of_Players = sc.nextInt();
        int no_of_Players = playerNo;
        /*while(no_of_Players > 5 || no_of_Players < 2) {
            if(no_of_Players > 5)
                out.print("The number of players is too high, please try another.");
            else out.print("The number of players is too low, please try another.");
            no_of_Players = sc.nextInt();
        }*/
        sc.nextLine();
        Player[] player = new Player[no_of_Players];
        out.print("Players, input your names: ");
        for (int i = 0; i < no_of_Players; i++)
        {
            player[i] = new Player(0,1500, sc.nextLine());
        }

        //board call
        Square[] brd = board.getBoard();

        //sort array alphabetically, assign turns
        String[] turns = new String[no_of_Players];
        for (int i = 0; i < no_of_Players; i++)
        {
            turns[i] = player[i].name;
        }
        turns = Stream.of(turns).sorted().toArray(String[]::new);
        for (int i = 0; i < no_of_Players; i++)
            player[i].name = turns[i];

        //out.print("Now please choose the number of rounds: ");
        //maxRounds = sc.nextInt();
        maxRounds = roundNo;

        //game loop
        while(true)
        {

            if(turn == no_of_Players) {
                turn = 0;
                rounds++;
            }

            if(rounds == maxRounds)
                break;

            out.print("It's " + player[turn].name + "'s turn.");
            out.print("Press any key to roll the dice.");
            out.print("              or              ");
            out.print("Press [m] to check your money and properties.");
            //String key = sc.nextLine();
            boolean stop = true;
            while(stop) {
                switch (sc.nextLine()) {
                    case "m":
                        out.print(player[turn].name + "'s total is of $" + player[turn].money);
                        out.print(player[turn].name + " owns " + player[turn].propertiesOwned +
                                " properties, " + player[turn].companiesOwned + " companies, and " + player[turn].railsOwned + " railroads.");
                        for(int i = 0; i < player[turn].properties.size(); i++) {
                            out.print(player[turn].properties.get(i) + " ");
                        }
                        sc.nextLine();
                        break;
                    default:

                        //check if player is in jail, skip them during current run
                        if(player[turn].inJail)
                        {
                            if(player[turn].jailRounds == 3) {
                                player[turn].inJail = false;
                                player[turn].money -= 50;
                                player[turn].jailRounds = 0;
                            }
                            else {
                                out.print(player[turn].name + ", you are currently in jail.");
                                out.print("Press [m] to pay $50");
                                if(player[turn].outofJailCard)
                                    out.print("Press [c] to use your Get out of Jail card.");
                                out.print("          or         ");
                                out.print("Press any key to try to throw doubles");
                                String key = sc.nextLine();
                                if(key == "m") {
                                    player[turn].money -= 50;
                                    out.print(player[turn].name + " has paid $50, and can return to playing!");
                                    player[turn].inJail = false;
                                }


                                    else if(key == "c") {
                                    if (player[turn].outofJailCard) {
                                        player[turn].outofJailCard = false;
                                        out.print(player[turn].name + " has used their card, and can return to playing!");
                                        player[turn].inJail = false;
                                    }
                                }

                                    else {
                                    int dice1 = input.RandomNumber(1,6);
                                    int dice2 = input.RandomNumber(1,6);
                                    if (dice1 == dice2) {
                                        out.print("The numbers on the dice are " + dice1 + " and " + dice2 + ", " + player[turn].name + " can return to playing!");
                                        player[turn].inJail = false;
                                    } else
                                        out.print("The numbers on the dice are " + dice1 + " and " + dice2 + ", " + player[turn].name + " is still in jail.");
                                }


                            }
                            break;

                        }

                        //throwing dice and board position handling
                        int dice1 = input.RandomNumber(1,6);
                        int dice2 = input.RandomNumber(1,6);
                        int sum = dice1 + dice2;
                        out.print("The dice have shown " + dice1 + " and " + dice2 + ", so " + player[turn].name + " can move a total of " + sum + " positions!");
                        player[turn].position += sum;
                        if(player[turn].position >= 40)
                        {
                            player[turn].position -= 40;
                            System.out.println("Player " + player[turn].name + " has passed through the Go, so they get $200!");
                            player[turn].money += 200;
                        }
                        boolean chance = true;
                        while(chance) {
                            int pos = player[turn].position;
                            out.print(player[turn].name + " landed on " + brd[pos].name + "!");
                            if (brd[pos].owner == null)
                                board.squareActionNull(brd[pos], player[turn]);
                            else
                                board.squareActionOwned(sum, brd[pos], player, player[turn]);
                            if (pos == player[turn].position)
                                chance = false;
                        }



                        //double
                        if(dice1 == dice2)
                        {
                            out.print(player[turn].name + " has thrown a double, so they can roll again!");
                            turn -= 1;
                        }

                        break;
                }
                break;
            }
            turn++;
        }
        out.print("The number of rounds has been reached! The game has ended.");

        //choosing the winner by the highest sum of money
        int[] total = new int[no_of_Players];
        for(int i = 0; i < no_of_Players; i++)
            total[i] = player[i].money;
        Arrays.sort(total);
        for(int i = 0; i < no_of_Players; i++)
        {
            if(total[no_of_Players - 1] == player[i].money) {
                out.print("The winner of this game is " + player[i].name + ", with a total of $" + player[i].money + "!");
                break;
            }
        }
    }

}
