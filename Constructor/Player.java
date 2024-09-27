package Constructor;

import java.util.Scanner;

public class Player {
    private String name;
    private char color;
    private static int players = 1;
    private static int place_players = 0;
    public void Player(){
    }
    public String getName(){
        return this.name;
    }
    public char getColor(){
        return this.color;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setColor(char color){
        this.color = color;
    }

    public static Player username() {
        Player player = new Player();
        System.out.print("Player " + players + ") What is your nickname ? ");
        if(players == 1) {
            player.setColor('R');
            players++;
        } else {
            System.out.print(" 'Bot' if you want to play with a Bot : ");
            player.setColor('Y');
        }
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        player.setName(username);
        System.out.print("\n");
        return player;
    }
}