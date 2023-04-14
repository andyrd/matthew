package Java.Main;

import javax.swing.JFrame;

public class Launch implements RuntimeSettings {

    public static JFrame window = new JFrame(windowTitle);
    public static GameHandler gameHandler = new GameHandler();
    public static GraphicsHandler graphicsHandler = new GraphicsHandler();

    public static void main(String[] args) {
        window.add(graphicsHandler);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(screenWidth, screenHeight);
        window.setResizable(resizable);
        window.setVisible(true);
    }

    // Create and add every sprite/element that needs to be created before the
    // first tick
    public static void addDefaultScripts() {

    }
}