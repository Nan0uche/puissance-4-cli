import Constructor.Player;

public class Main extends Puissance4 {
    public static void main(String[] args) {
        System.out.print("Welcome to the game \u001B[34mConnect 4\u001B[0m !\n\n");
        Player j1 = Player.username();
        Player j2 = Player.username();
        while(Puissance4.restart){
            char[][] board = Puissance4.initialise();
            while(!Puissance4.end) {
                board = Puissance4.play(board, j1);
                board = Puissance4.play(board, j2);
            }
        }
    }
}