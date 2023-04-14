package Main;
import java.awt.Image;
import java.awt.Rectangle;

public class Shape {
    private static Image image;
    private static int x = 400;
    private static int y = 400;
    private static int width = 48;
    private static int height = 48;
    private Rectangle bounds;
    public static int angle = 45;
    public static int speed = 4;

    public Shape(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        bounds = new Rectangle(x, y, width, height);
    }

    public static Image getImage() {
        return image;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setX(int x) {
        this.x = x;
        bounds.x = x;
    }

    public void setY(int y) {
        this.y = y;
        bounds.y = y;
    }
    public static int getAngle() {
        return angle;
    }
    public static int getSpeed() {
        return speed;
    }
}

