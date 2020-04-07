package kakurosolver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Cell extends JLabel {

    private boolean isPuzzle;
    private final int down, across;
    private int value;

    public Cell() {
        this.isPuzzle = true;
        this.down = 0;
        this.across = 0;
        this.value = 0;
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
    }

    public Cell(int across, int down) {
        this.isPuzzle = false;
        this.down = down;
        this.across = across;
        this.value = 0;
        setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(Color.GRAY);
        setOpaque(true);
    }

    public int getDown() {
        return this.down;
    }

    public int getAcross() {
        return this.across;
    }

    public boolean isPuzzle() {
        return this.isPuzzle;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        if (this.isPuzzle) {
            this.value = value;
        } else {
            this.value = 0;
        }
    }

    public void setIsPuzzle(boolean isPuzzle) {
        this.isPuzzle = isPuzzle;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (!isPuzzle && getBackground().equals(Color.GRAY)) {
            grphcs.drawLine((int) getX(), (int) getY(), getWidth(), getHeight());
            Graphics2D g2 = (Graphics2D) grphcs;
            Line2D crossLine = new Line2D.Double(getAlignmentX(), getAlignmentY(), getWidth(), getHeight());
            g2.draw(crossLine);
            String downStr = String.valueOf(down);
            String acrossStr = String.valueOf(across);
            setText("<html>" + acrossStr + "<br/><br/>" + downStr + "</html>");
        }
    }

    @Override
    public void setPreferredSize(Dimension dmnsn) {
        super.setPreferredSize(dmnsn);
    }

    @Override
    public String toString() {
        if (isPuzzle) {
            return "" + value;
        } else {
            return "across: " + across + " down: " + down;
        }
    }
}
