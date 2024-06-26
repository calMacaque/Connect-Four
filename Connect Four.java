import java.util.Scanner;

class Main {

    public static void viewGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char e : row) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    public static boolean move(char[][] grid, int turns, Scanner sc) {
        String str = (turns % 2 == 0 ? "---PLAYER 1'S TURN---\nEnter move: row(0-5) & column(0-6)\n" :
                "---PLAYER 2'S TURN---\nEnter move: row(0-5) & column(0-6)\n");
        System.out.print(str);
        viewGrid(grid);
        int row = sc.nextInt(), col = sc.nextInt();

        // checking if move is invalid
        if (!((row >= 0 && row <= 5) && (col >= 0 && col <= 6))) {
            System.out.println("WRONG POSITION, ROW(0-5) AND COLUMN (0-6)");
            return true;
        } else if (row < 5 && grid[row + 1][col] == '.') {
            System.out.println("THERE IS NO DISC UNDER THIS POSITION, RE-ENTER:");
            return true;
        } else if ((grid[row][col] == 'X') || (grid[row][col] == 'O')) {
            System.out.println("POSITION ALREADY OCCUPIED! RE-ENTER:");
            return true;
        } else {
            grid[row][col] = (turns % 2 == 0 ? 'X' : 'O');
            return false;
        }
    }

    public static boolean winCheck(char[][] grid) {

        for (int row = 0; row < 6; row++) {// horizontal matching
            for (int col = 0; col < 4; col++) {//matching next 3 elements in same row
                char disc = grid[row][col];
                if (disc != '.' && disc == grid[row][col + 1] && disc == grid[row][col + 2] && disc == grid[row][col + 3]) {
                    return true;
                }
            }
        }

        for (int row = 0; row < 3; row++) {//vertical matching
            for (int col = 0; col < 7; col++) {//matching next 3 elements in same col
                char disc = grid[row][col];
                if (disc != '.' && disc == grid[row + 1][col] && disc == grid[row + 2][col] && disc == grid[row + 3][col]) {
                    return true;
                }
            }
        }

        for (int row = 0; row < 3; row++) {// diagonal check (top-left to bottom-right)
            for (int col = 0; col < 4; col++) {
                char disc = grid[row][col];
                if (disc != '.' && disc == grid[row + 1][col + 1] && disc == grid[row + 2][col + 2] && disc == grid[row + 3][col + 3]) {
                    return true;
                }
            }
        }

        for (int row = 5; row > 2; row--) {// diagonal check (bottom left to top right)
            for (int col = 0; col < 4; col++) {
                char disc = grid[row][col];
                if (disc != '.' && disc == grid[row - 1][col + 1] && disc == grid[row - 2][col + 2] && disc == grid[row - 3][col + 3]) {
                    return true;
                }
            }
        }
        return false;//if no condition satisfied
    }

    public static void main(String[] args) {
        System.out.println("=====WELCOME TO CONNECT 4=====");
        System.out.print("---PLAYER 1 = X---\n---PLAYER 2 = O---\n\n");
        char[][] grid = new char[6][7];
        for (int i = 0; i < 6; i++) { // empty space = dot
            for (int j = 0; j < 7; j++) {
                grid[i][j] = '.';
            }
        }

        Scanner sc = new Scanner(System.in);

        boolean win = false, move = false; // move means wrong move actually
        for (int turns = 0; turns < 42; turns++) {
            move = move(grid, turns, sc);

            if (move) { // move validity check
                move = false;
                turns--;
                continue;
            }

            win = winCheck(grid); // check win conditions
            if (win) {
                String winner = (turns % 2 == 0 ? "PLAYER 1" : "PLAYER 2");
                System.out.println(winner + " WINS!!");
                viewGrid(grid);
                break;
            }
        }
        sc.close();
    }
}
