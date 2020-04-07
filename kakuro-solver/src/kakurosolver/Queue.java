package kakurosolver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Queue<T> {

    private final int size;
    private final T[] queue;
    private int front;
    private int rear;

    private static final int GAP = 1;

    public Queue(int size) {
        this.size = size;
        queue = (T[]) new Object[size];
        front = -1;
        rear = -1;
    }

    public T[] getQueueArr() {
        return queue;
    }

    public Object[] getQueue() {
        return queue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return (front == -1 && rear == -1);
    }

    public boolean isFull() {
        return (rear + 1) % size == front;
    }

    public void enqueue(T value) {
        if (!isFull()) {
            if (isEmpty()) {
                front++;
                rear++;
                queue[rear] = value;
            } else {
                rear = (rear + 1) % size;
                queue[rear] = value;
            }
        }
    }

    public Cell getElementFrom(int row, int col) {
        if (queue[row] instanceof Queue) {
            Queue q = (Queue) queue[row];
            if (q.getQueueArr()[col] instanceof Cell) {
                return (Cell) q.getQueueArr()[col];
            }
        }

        return null;
    }

    public void displayGUI() {
        Queue q = (Queue) getQueue()[0];
        JPanel mainPanel = new JPanel();
        JPanel kakuroPanel = new JPanel(new GridLayout(queue.length, q.getQueueArr().length, GAP, GAP));
        kakuroPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        kakuroPanel.setBackground(Color.BLACK);

        for (T obj : queue) {
            if (obj instanceof Queue) {
                Queue qu = (Queue) obj;
                for (int i = 0; i < qu.getQueueArr().length; i++) {
                    if (qu.getQueueArr()[i] instanceof Cell) {
                        Cell cell = (Cell) qu.getQueueArr()[i];
                        if (!cell.getBackground().equals(Color.GRAY)) {
                            if (cell.isPuzzle()) {
                                cell.setText(String.valueOf(cell.getValue()));
                                cell.setBackground(Color.WHITE);
                            } else {
                                cell.setText("   ");
                                cell.setBackground(Color.LIGHT_GRAY);
                            }
                        }
                        cell.setPreferredSize(new Dimension(50, 50));
                        kakuroPanel.add(cell);
                    }
                }
            }
        }

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(kakuroPanel, BorderLayout.CENTER);

        JFrame frame = new JFrame("Solution");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
