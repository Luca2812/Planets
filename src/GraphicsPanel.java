import javax.swing.JPanel;
import java.awt.*;

public class GraphicsPanel extends JPanel implements Runnable {
    private final int WINDOW_LENGTH;
    private final int WINDOW_HEIGHT;
    private int frameCount;
    private Planet[] planets;

    public GraphicsPanel(int length, int height) {
        WINDOW_LENGTH = length;
        WINDOW_HEIGHT = height;
        setPreferredSize(new Dimension(WINDOW_LENGTH, WINDOW_HEIGHT));
        setDoubleBuffered(true);
        frameCount = 0;
        planets=new Planet[] {
                new Planet(new Vector(400, 400), new Vector(0, -0.5), 100),
                new Planet(new Vector(600, 500), new Vector(0, 0.5), 100),
                new Planet(new Vector(700, 300), new Vector(-0.1, -0.2), 100),
                new Planet(new Vector(700, 700), new Vector(0.1, -0.1), 100),
                new Planet(new Vector(50, 400), new Vector(0.1, -0.1), 100)
        };
    }

    @Override
    public void run() {
        int fps = 60;
        long nanosPerFrame = 1_000_000_000/fps;
        long nextFrame = System.nanoTime()+nanosPerFrame;
        while(true) {
            if(System.nanoTime()>nextFrame) {
                nextFrame += nanosPerFrame;
                update();
                repaint();
            }
        }
    }

    private void update() {
        frameCount++;
        for(int i = 0; i<planets.length; i++) {
            planets[i].acc=new Vector(0, 0);
        }

        for(int i = 0; i<planets.length; i++) {
            for(int j = 0; j<planets.length; j++) {
                if(i!=j) {
                    planets[i].addForce(planets[i].forceTo(planets[j]));
                }
            }
        }

        for(int i = 0; i<planets.length; i++) {
            planets[i].update();
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D)graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /* start drawing */

        // draw Planets
        for(int i = 0; i<planets.length; i++) {
            planets[i].draw(g);
        }

        // draw framecount
        g.setFont(new Font("Consolas", Font.PLAIN, 24));
        g.drawString(frameCount + "", 0, g.getFontMetrics().getAscent());

        /* end drawint */
        g.dispose();
    }

    public void startThread() {
        new Thread(this).start();
    }
}