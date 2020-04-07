package kakurosolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            String str = "game_" + i + ".txt";
            Queue<Queue<Cell>> board = readFromFile(str);
            if (board != null) {
                System.out.println("Puzzle: " + str);
                new KakuroSolver(board);
            }
        }
    }

    private static Queue<Queue<Cell>> readFromFile(String fileName) {
        File file = new File(System.getProperty("user.dir") + "/resources/" + fileName);
        Queue<Queue<Cell>> board = null;
        Queue<Cell> queue;

        try {
            Scanner scanner = new Scanner(file);

            int rows = scanner.nextInt();
            int cols = scanner.nextInt();

            if (rows <= 2 || cols <= 2) {
                System.out.println("Error - Dimensions are less than two");
                return null;
            }

            board = new Queue<>(rows);
            queue = new Queue<>(cols);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String next = scanner.next();
                    switch (next) {
                        case "Puzzle":
                            queue.enqueue(new Cell());
                            break;
                        case "Clue":
                            int across = scanner.nextInt();
                            int down = scanner.nextInt();
                            if (down >= 0 && across >= 0) {
                                queue.enqueue(new Cell(across, down));
                            }
                            break;
                        default:
                            Cell f = new Cell();
                            f.setIsPuzzle(false);
                            queue.enqueue(f);
                            break;
                    }

                    if (queue.isFull()) {
                        board.enqueue(queue);
                        queue = new Queue<>(cols);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException | NumberFormatException | NoSuchElementException e) {
            System.out.println(e.toString());
        }

        return board;
    }
}
