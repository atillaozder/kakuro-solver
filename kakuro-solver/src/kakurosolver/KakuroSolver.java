package kakurosolver;

public class KakuroSolver {

    private final Queue<Queue<Cell>> kakuro;

    public KakuroSolver(Queue<Queue<Cell>> kakuro) {
        this.kakuro = kakuro;
        startSolving();
    }

    private void startSolving() {
        if (solve(kakuro, 0, 0)) {
            System.out.println("Solution:");
            kakuro.displayGUI();
        } else {
            System.out.println("Cannot Solve the Puzzle");
        }
    }

    private boolean solve(Queue<Queue<Cell>> board, int row, int col) {
        if (row == board.getSize()) {
            return true;
        }

        Queue q = (Queue) board.getQueue()[0];
        if (col == q.getSize()) {
            return solve(board, row + 1, 0);
        }

        if (!board.getElementFrom(row, col).isPuzzle()) {
            return solve(board, row, col + 1);
        }

        for (int i = 1; i <= 9; i++) {
            if (isValidRow(board, row, col, i) && isValidCol(board, row, col, i)) {
                board.getElementFrom(row, col).setValue(i);
                if (solve(board, row, col + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isValidRow(Queue<Queue<Cell>> board, int row, int col, int value) {
        int sum = value;
        int totalValue = 0;

        for (int i = col - 1; i >= 0; i--) {
            if (!board.getElementFrom(row, i).isPuzzle()) {
                totalValue = board.getElementFrom(row, i).getAcross();
                break;
            }
            sum += board.getElementFrom(row, i).getValue();
            if (board.getElementFrom(row, i).getValue() == value) {
                return false;
            }
        }

        if (sum > totalValue) {
            return false;
        }

        Queue q = (Queue) board.getQueue()[0];
        if (col == q.getQueueArr().length - 1) {
            if (sum < totalValue) {
                return false;
            }
        } else if (!board.getElementFrom(row, col + 1).isPuzzle()) {
            if (sum < totalValue) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidCol(Queue<Queue<Cell>> board, int row, int col, int value) {
        int sum = value;
        int totalValue = 0;

        for (int i = row - 1; i >= 0; i--) {
            if (!board.getElementFrom(i, col).isPuzzle()) {
                totalValue = board.getElementFrom(i, col).getDown();
                break;
            }
            sum += board.getElementFrom(i, col).getValue();
            if (board.getElementFrom(i, col).getValue() == value) {
                return false;
            }
        }

        if (sum > totalValue) {
            return false;
        }

        if (row == board.getSize() - 1) {
            if (sum < totalValue) {
                return false;
            }
        } else if (!board.getElementFrom(row + 1, col).isPuzzle()) {
            if (sum < totalValue) {
                return false;
            }
        }

        return true;
    }
}
