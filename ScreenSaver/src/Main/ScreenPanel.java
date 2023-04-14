package Main;
import java.awt.*;
import javax.swing.JPanel;

public class ScreenPanel extends JPanel {

    protected Thread thread;
    protected static Graphics g;
    int angle = Shape.getAngle();
    int speed = Shape.getSpeed();
    double y = Shape.getY();
    double x = Shape.getX();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.fillRect((int)x,(int)y,Shape.getWidth(),Shape.getHeight());
    }
    public ScreenPanel() {
        startScreenThread();
    }
    public void startScreenThread() {
        ScreenThread screenThread = new ScreenThread();
        thread = new Thread(screenThread);
        thread.start();
    }
    public void update() {
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);

        x += dx;
        y += dy;

        if(x < 0) {
            x = 0;
            angle += 90;
        } else if(x + Shape.getWidth() >= Main.screenWidth) {
            x = Main.screenWidth - Shape.getWidth();
            angle += 90;
        }
        if(y < 0) {
            y = 0;
            angle += 90;
        } else if(y + Shape.getHeight() >= Main.screenHeight) {
            y = Main.screenHeight - Shape.getHeight();
            angle += 90;
        }

        if(angle >= 360) angle -= 360;
        repaint();
    }
}
