import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Planets");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsPanel gp = new GraphicsPanel(800, 800);
        frame.add(gp);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gp.startThread();
    }
}