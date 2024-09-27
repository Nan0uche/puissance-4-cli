package Constructor;

import java.util.Random;

public class Bot {
    public static Position playBot(char[][] tab) {
        Random random = new Random();
        for (int col = 0; col < 6; col++) {
            if (isValidMove(tab, col)) {
                char[][] tempTab = copyBoard(tab);
                int row = dropToken(tempTab, col, 'Y');

                if (checkWin(tempTab, row, col, 'Y')) {
                    return new Position(row, col);
                }
            }
        }

        for (int col = 0; col < 6; col++) {
            if (isValidMove(tab, col)) {
                char[][] tempTab = copyBoard(tab);
                int row = dropToken(tempTab, col, 'R');

                if (checkWin(tempTab, row, col, 'R')) {
                    return new Position(row, col);
                }
            }
        }
        int randomCol;
        do {
            randomCol = random.nextInt(6);
        } while (!isValidMove(tab, randomCol));

        int randomRow = dropToken(tab, randomCol, 'Y');
        return new Position(randomRow, randomCol);
    }

    private static boolean isValidMove(char[][] tab, int col) {
        return tab[0][col] == '-';
    }

    private static int dropToken(char[][] tab, int col, char token) {
        for (int row = 5; row >= 0; row--) {
            if (tab[row][col] == '-') {
                tab[row][col] = token;
                return row;
            }
        }
        return -1;
    }

    private static boolean checkWin(char[][] tab, int row, int col, char token) {
        int count = 0;
        for (int j = Math.max(0, col - 3); j <= Math.min(6, col + 3); j++) {
            if (tab[row][j] == token) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int i = Math.max(0, row - 3); i <= Math.min(5, row + 3); i++) {
            if (tab[i][col] == token) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int i = row - Math.min(row, col), j = col - Math.min(row, col); i <= Math.min(5, row + 3) && j <= Math.min(6, col + 3); i++, j++) {
            if (tab[i][j] == token) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int i = row + Math.min(5 - row, col), j = col - Math.min(5 - row, col); i >= Math.max(0, row - 3) && j <= Math.min(6, col + 3); i--, j++) {
            if (tab[i][j] == token) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    private static char[][] copyBoard(char[][] original) {
        char[][] copy = new char[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }
}