package References;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** @see http://stackoverflow.com/questions/4331699 */
public class ButtonBorder extends JPanel {

    private static final int N = 8;
    private static final int SIZE = 75;

    public ButtonBorder() {
        super(new GridLayout(N, N));
        this.setPreferredSize(new Dimension(N * SIZE, N * SIZE));
        for (int i = 0; i < N * N; i++) {
            this.add(new ChessButton(i));
        }
    }

    private static class ChessButton extends JButton {

        public ChessButton(int i) {
            super(i / N + "," + i % N);
            this.setOpaque(true);
//            this.setBorderPainted(false);
            if ((i / N + i % N) % 2 == 1) {
                this.setBackground(Color.gray);
            }
        }
    }

    private void display() {
        JFrame f = new JFrame("ButtonBorder");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ButtonBorder().display();
            }
        });
    }
}