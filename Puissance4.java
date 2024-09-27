import Constructor.Bot;
import Constructor.Player;
import Constructor.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puissance4{

    public static boolean end = false;
    public static boolean restart = true;
    public static int even = 0;
    public static char winner;
    public static List<Position> positions = new ArrayList<>();

    public static char[][] initialise() {
        even = 0;
        end = false;
        winner = 0;
        positions.clear();
        char[][] tableau = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tableau[i][j] = '-';
            }
        }
        printTab(tableau);
        return tableau;
    }

    public static void printTab(char[][] tab) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if(tab[i][j] == 'R') {
                    if(!positions.isEmpty()){
                        if(winner == 'R'){
                            if(i == positions.get(0).getRow() && j == positions.get(0).getColumn()) {
                                System.out.print("\u001B[32m\u001B[1m" + tab[i][j] + " \u001B[0m");
                                positions.remove(0);
                            } else {
                                System.out.print("\u001B[31m" + tab[i][j] + " \u001B[0m");
                            }
                        }
                        else {
                            System.out.print("\u001B[31m" + tab[i][j] + " \u001B[0m");
                        }
                    } else {
                        System.out.print("\u001B[31m" + tab[i][j] + " \u001B[0m");
                    }
                } else if(tab[i][j] == 'Y') {
                    if(!positions.isEmpty()){
                        if(winner == 'Y'){
                            if(i == positions.get(0).getRow() && j == positions.get(0).getColumn()) {
                                System.out.print("\u001B[32m\u001B[1m" + tab[i][j] + " \u001B[0m");
                                positions.remove(0);
                            } else {
                                System.out.print("\u001B[93m" + tab[i][j] + " \u001B[0m");
                            }
                        } else {
                            System.out.print("\u001B[93m" + tab[i][j] + " \u001B[0m");
                        }
                    } else {
                        System.out.print("\u001B[93m" + tab[i][j] + " \u001B[0m");
                    }
                } else {
                    System.out.print(tab[i][j] + " ");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < 7; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    public static char[][] play(char[][] tab, Player player) {
        while(true) {
            System.out.print("\n");
            if(player.getColor() == 'R') {
                System.out.print("\u001B[31m" + player.getName() + "\u001B[0m" + ") Choose a column : ");
            } else {
                System.out.print("\u001B[93m" + player.getName() + "\u001B[0m" + ") Choose a column : ");
            }
            if(player.getColor() == 'Y' && player.getName().equalsIgnoreCase("Bot")){
                Position botchoice = Bot.playBot(tab);
                System.out.print(botchoice.getColumn() + "\n");
                tab[botchoice.getRow()][botchoice.getColumn()] = player.getColor();
                even++;
                if(ifWin(tab, player)){
                    end = true;
                    winner = player.getColor();
                    printTab(tab);
                    System.out.print("\n");
                    System.out.print("Victory of : " + player.getName() + "\n");
                    askRestart();
                } else if(even >= 42) {
                    end = true;
                    printTab(tab);
                    System.out.print("\n");
                    System.out.print("Draw !\n");
                    askRestart();
                } else {
                    printTab(tab);
                }
                return tab;
            } else {
                Scanner scanner = new Scanner(System.in);
                if(scanner.hasNextInt()){
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice > 6 || choice < 0) {
                        System.out.print("You must choose a column between 0 and 6 !\n");
                        printTab(tab);
                    } else {
                        if (tab[0][choice] != '-'){
                            System.out.print("You cannot place a token on top of the board\n");
                            printTab(tab);
                        } else {
                            for (int i = 5; i >= 0; i--) {
                                if (tab[i][choice] == '-'){
                                    tab[i][choice] = player.getColor();
                                    even++;
                                    if(ifWin(tab, player)){
                                        end = true;
                                        winner = player.getColor();
                                        printTab(tab);
                                        System.out.print("\n");
                                        System.out.print("Victory of : " + player.getName() + "\n");
                                        tab = initialise();
                                        askRestart();
                                    } else if(even >= 42) {
                                        end = true;
                                        printTab(tab);
                                        System.out.print("\n");
                                        System.out.print("Draw !\n");
                                        tab = initialise();
                                        askRestart();
                                    } else {
                                        printTab(tab);
                                    }
                                    return tab;
                                }
                            }
                        }
                    }
                } else {
                    System.out.print("Your entry must be an integer !\n");
                    printTab(tab);
                }
            }
        }
    }
    public static boolean ifWin(char[][] tab, Player player) {
        // Vérif des lignes
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= 3; j++) {
                if (tab[i][j] == player.getColor() && tab[i][j + 1] == player.getColor() && tab[i][j + 2] == player.getColor() && tab[i][j + 3] == player.getColor()) {
                    positions.add(new Position(i, j));
                    positions.add(new Position(i, j+1));
                    positions.add(new Position(i, j+2));
                    positions.add(new Position(i, j+3));
                    return true;
                }
            }
        }

        // Vérif des colonnes
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j < 7; j++) {
                if (tab[i][j] == player.getColor() && tab[i + 1][j] == player.getColor() && tab[i + 2][j] == player.getColor() && tab[i + 3][j] == player.getColor()) {
                    positions.add(new Position(i, j));
                    positions.add(new Position(i+1, j));
                    positions.add(new Position(i+2, j));
                    positions.add(new Position(i+3, j));
                    return true;
                }
            }
        }

        // Vérif des diag (ascendantes)
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j <= 3; j++) {
                if (tab[i][j] == player.getColor() && tab[i - 1][j + 1] == player.getColor() && tab[i - 2][j + 2] == player.getColor() && tab[i - 3][j + 3] == player.getColor()) {
                    positions.add(new Position(i-3, j+3));
                    positions.add(new Position(i-2, j+2));
                    positions.add(new Position(i-1, j+1));
                    positions.add(new Position(i, j));
                    return true;
                }
            }
        }

        // Vérif des diag (descendantes)
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 7; j++) {
                if (tab[i][j] == player.getColor() && tab[i - 1][j - 1] == player.getColor() && tab[i - 2][j - 2] == player.getColor() && tab[i - 3][j - 3] == player.getColor()) {
                    positions.add(new Position(i-3, j-3));
                    positions.add(new Position(i-2, j-2));
                    positions.add(new Position(i-1, j-1));
                    positions.add(new Position(i, j));
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean askRestart(){
        System.out.print("Do you want to restart a game ? (Yes/No) :\n");
        Scanner scanner = new Scanner(System.in);
        String reponse = scanner.next();
        if(reponse.equalsIgnoreCase("Yes")|| reponse.equalsIgnoreCase("Ye") || reponse.equalsIgnoreCase("Y")) {
            System.out.print("Launch of a new part of \u001B[34mConnect 4\u001B[0m !\n");
            restart = true;
            return true;
        } else {
            System.out.print("See you soon !");
            System.exit(1);
            return false;
        }
    }
}