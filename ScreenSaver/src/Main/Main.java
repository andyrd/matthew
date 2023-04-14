package Main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static ScreenPanel panel;
    public static int screenWidth = 1200;
    public static int screenHeight = 800;

    public static void main(String[] args) {

        JFrame window = new JFrame("ScreenSaver");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        window.setPreferredSize(new Dimension(screenWidth,screenHeight));
        window.setResizable(false);
        window.setBackground(Color.black);
        window.setLocationRelativeTo(null);

        window.pack();

        panel = new ScreenPanel();
        window.add(panel);
    }
}
