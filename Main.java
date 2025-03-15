import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void sleepMillis(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
            System.out.print("\033[H\033[2J");
            clearConsole();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        //String os = System.getProperty("os.name").toLowerCase();
        try {
            // if (os.contains("win")) {
            //     new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            // } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            //}
        } catch (Exception e) {
            System.out.println("Could not clear console.");
        }
    }

    public static int levelPref(Scanner scanner) {
        int choice;
        do {
            System.out.print("Select difficulty level. Type '1' for easy, '2' for medium, and '3' for hard: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }
            choice = scanner.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid input! Try again.");
                sleepMillis(2000);
            }
        } while (choice < 1 || choice > 3);
        return choice;
    }

    public static boolean question(int[][] arr, int[][] model, Scanner scanner) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("=======================================");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    System.out.print("| ");
                }
                System.out.print(arr[i][j]);
                if (j == 8) {
                    System.out.print(" |");
                } else if ((j + 1) % 3 == 0) {
                    System.out.print(" || ");
                } else {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i == 8) {
                System.out.println("=======================================");
            }
        }

        System.out.println("\nWrite it like this: row column number (or 0 0 0 to quit)");
        System.out.print("Write the cell coordinate and number: ");
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int g = scanner.nextInt();
        if(r==0 && c==0 && g==0) {
            return true;
        }
        if (r > 9 || r < 1 || c > 9 || c < 1 || g > 9 || g < 1) {
            System.out.println("Input is out of range");
            return false;
        }

        if (arr[r - 1][c - 1] != 0) {
            System.out.println("This cell is already occupied");
            return false;
        }
        
        if (model[r-1][c-1]==g) {
            arr[r-1][c-1]=g;
            return false;
        }
        if (model[r-1][c-1]!=g) {
            int row=0, col=0;
            if (r%3==0) {
                row=r-3;
            }
            else {
                row=3*(r/3);
            }
            if (c%3==0) {
                col=c-3;
            }
            else {
                col=3*(c/3);
            }
            for (int i=row; i<row+3; i++) {
                for (int j=col; j<col+3; j++) {
                    if (arr[i][j]==g) {
                        System.out.println("This box already contains " + g);
                        return false;
                    }
                }
            }
        }
        if (model[r-1][c-1]!=g) {
            for (int j=0; j<9; j++) {
                if (arr[r-1][j]==g) {
                    
                    System.out.println("This row already contains " + g);
                    return false;
                }
            }
        }
        if (model[r-1][c-1]!=g) {
            for (int i=0; i<9; i++) {
                if (arr[i][c-1]==g) {
                    System.out.println("This column already contains " + g);
                    return false;
                }
            }
            
        }
        if (model[r-1][c-1]!=g) {
            System.out.println("This cell doesn't contain " + g);
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] model = new int[9][9];
        int[][] arr = new int[9][9];
        
        int[][] model_1 = {
            {4, 3, 5, 2, 6, 9, 7, 8, 1},
            {6, 8, 2, 5, 7, 1, 4, 9, 3},
            {1, 9, 7, 8, 3, 4, 5, 6, 2},
            {8, 2, 6, 1, 9, 5, 3, 4, 7},
            {3, 7, 4, 6, 8, 2, 9, 1, 5},
            {9, 5, 1, 7, 4, 3, 6, 2, 8},
            {5, 1, 9, 3, 2, 6, 8, 7, 4},
            {2, 4, 8, 9, 5, 7, 1, 3, 6},
            {7, 6, 3, 4, 1, 8, 2, 5, 9}
        };
        
        int[][] arr_1 = {
            {0, 0, 0, 2, 6, 0, 7, 0, 1},
            {6, 8, 0, 0, 7, 0, 0, 9, 0},
            {1, 9, 0, 0, 0, 4, 5, 0, 0},
            {8, 2, 0, 1, 0, 0, 0, 4, 0},
            {0, 0, 4, 6, 0, 2, 9, 0, 0},
            {0, 5, 0, 0, 0, 3, 0, 2, 8},
            {0, 0, 9, 3, 0, 0, 0, 7, 4},
            {0, 4, 0, 0, 5, 0, 0, 3, 6},
            {7, 0, 3, 0, 1, 8, 0, 0, 0}
        };

        int[][] model_2 = {
            {1, 2, 3, 6, 7, 8, 9, 4, 5},
            {5, 8, 4, 2, 3, 9, 7, 6, 1},
            {9, 6, 7, 1, 4, 5, 3, 2, 8},
            {3, 7, 2, 4, 6, 1, 5, 8, 9},
            {6, 9, 1, 5, 8, 3, 2, 7, 4},
            {4, 5, 8, 7, 9, 2, 6, 1, 3},
            {8, 3, 6, 9, 2, 4, 1, 5, 7},
            {2, 1, 9, 8, 5, 7, 4, 3, 6},
            {7, 4, 5, 3, 1, 6, 8, 9, 2}
        };

        int[][] arr_2 = {
            {0, 2, 0, 6, 0, 8, 0, 0, 0},
            {5, 8, 0, 0, 0, 9, 7, 0, 0},
            {0, 0, 0, 0, 4, 0, 0, 0, 0},
            {3, 7, 0, 0, 0, 0, 5, 0, 0},
            {6, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 8, 0, 0, 0, 0, 1, 3},
            {0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 9, 8, 0, 0, 0, 3, 6},
            {0, 0, 0, 3, 0, 6, 0, 9, 0}
        };
        int[][] model_3 = {
            {5, 8, 1, 6, 7, 2, 4, 3, 9},
            {7, 9, 2, 8, 4, 3, 6, 5, 1},
            {3, 6, 4, 5, 9, 1, 7, 8, 2},
            {4, 3, 8, 9, 5, 7, 2, 1, 6},
            {2, 5, 6, 1, 8, 4, 9, 7, 3},
            {1, 7, 9, 3, 2, 6, 8, 4, 5},
            {8, 4, 5, 2, 1, 9, 3, 6, 7},
            {9, 1, 3, 7, 6, 8, 5, 2, 4},
            {6, 2, 7, 4, 3, 5, 1, 9, 8}
        };
        int[][] arr_3 = {
            {0, 0, 0, 6, 0, 0, 4, 0, 0},
            {7, 0, 0, 0, 0, 3, 6, 0, 0},
            {0, 0, 0, 0, 9, 1, 0, 8, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 5, 0, 1, 8, 0, 0, 0, 3},
            {0, 0, 0, 3, 0, 6, 0, 4, 5},
            {0, 4, 0, 2, 0, 0, 0, 6, 0},
            {9, 0, 3, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 1, 0, 0}
        };
    
     
        int choice = levelPref(scanner);
        if (choice == 1) {
            for (int i = 0; i < 9; i++) {
                System.arraycopy(model_1[i], 0, model[i], 0, 9);
                System.arraycopy(arr_1[i], 0, arr[i], 0, 9);
            }
        }
        else if (choice == 2) {
            for (int i = 0; i < 9; i++) {
                System.arraycopy(model_2[i], 0, model[i], 0, 9);
                System.arraycopy(arr_2[i], 0, arr[i], 0, 9);
            }
        }
        else if (choice == 3) {
            for (int i = 0; i < 9; i++) {
                System.arraycopy(model_3[i], 0, model[i], 0, 9);
                System.arraycopy(arr_3[i], 0, arr[i], 0, 9);
            }
        }
        else {
            System.out.println("Invalid input! Try again.");
            sleepMillis(2);
            main(args);
            return;
        }

        long startTime = System.currentTimeMillis();

        boolean win = false;
        while (!win) {
            int sum = 0;
            if(question(arr, model, scanner)==true){         
                sleepMillis(2);
                main(args);
            };
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sum += arr[i][j];
                }
            }
            if (sum == 405) {
                win = true;
            }
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            scanner.nextLine();
            sleepMillis(2);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;
        long seconds = totalTime%60;
        long minutes = (totalTime/60)%60;
        long hours = (totalTime/3600);
        System.out.println("CONGRATULATIONS YOU WON!");
        if(hours==0){
            System.out.println("You solved the puzzle in "+ minutes + " minutes and " + seconds + " seconds");
        }
        else {
            System.out.println("You solved the puzzle in "+ hours + " hours " + minutes + " minutes and " + seconds + " seconds");
        }
        System.out.println("Do you want to play again?(y/n): ");
        char answer = scanner.next().charAt(0);
        if(answer=='y'){
            sleepMillis(2);
            main(args);
        }
        else {
            System.out.println("Thank you very much for playing Sudoku Game!<3");
        }

    }
}
