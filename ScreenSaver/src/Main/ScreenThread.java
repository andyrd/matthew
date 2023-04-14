package Main;
import javax.swing.*;

import static Main.Main.panel;
import static java.lang.Thread.sleep;
public class ScreenThread extends Thread {
    protected int FPS = 60;
    protected Thread thread;
    protected boolean running;
    protected int i;

    public void run() {
        double drawInterval = FPS/1_000_000_000;
        double nextDrawTime = System.nanoTime() + drawInterval;
        running = true;
        while (running) {
            panel.update();
            try {
                i++;
                System.out.println("frame: " + i);
                double remainingDrawTime = System.nanoTime() - nextDrawTime;
                remainingDrawTime = remainingDrawTime / 10_000;
                sleep((long) remainingDrawTime);
                nextDrawTime = System.nanoTime() + drawInterval;
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stopScreenThread() {
        running = false;
    }
}
