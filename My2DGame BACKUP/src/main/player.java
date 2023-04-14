package main;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class player extends Entity {

    static BufferedImage image;
    static GamePanel gp;
    static KeyHandler keyH;

    public static int screenX;
    public static int screenY;

    static boolean isPlayerSliding;
    static boolean isMoveKeyPressed;
    static boolean wasMoveKeyPressed;
    static double nextSlideDistance;

    public player(GamePanel gp, KeyHandler keyH) {

        player.gp = gp;
        player.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {

        worldX = gp.maxWorldCol / 2 * gp.tileSize;
        worldY = (gp.maxWorldRow - 1) / 2 * gp.tileSize;
        speed = 6;
        direction = "down";

    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/amogusup1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/amogusup2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/amogusdown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/amogusdown2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/amogusleft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/amogusdown2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/amogusright1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/amogusright2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update() {

        if (keyH.downPressed == true || keyH.upPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true) {

            isMoveKeyPressed = true;

            if (keyH.upPressed) {

                direction = "up";
                worldY -= speed;

            } else if (keyH.downPressed) {

                direction = "down";
                worldY += speed;

            } else if (keyH.leftPressed) {

                direction = "left";
                worldX -= speed;

            } else if (keyH.rightPressed) {

                direction = "right";
                worldX += speed;

            }

            spriteCounter++;
            if (spriteCounter > 8) {

                if (spriteNum == 1) {
                    spriteNum = 2;

                } else if (spriteNum == 2) {
                    spriteNum = 1;

                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 2;
        }

        double slideRate = 0.75;
        double slideStart = 0.5;
        double stopSlidingAt = 0.5;

        double newSlideRate = nextSlideDistance * slideRate;

        if (nextSlideDistance <= stopSlidingAt) {
            nextSlideDistance = speed * slideStart;
        }

        if (isMoveKeyPressed == false && wasMoveKeyPressed == true) {
            isPlayerSliding = true;
        }

        if (isPlayerSliding == true) {

            switch (direction) {

                case "up":
                    if (nextSlideDistance > stopSlidingAt) {

                        worldY -= nextSlideDistance;
                        nextSlideDistance = newSlideRate;

                    }
                    break;

                case "down":
                    if (nextSlideDistance > stopSlidingAt) {

                        worldY += nextSlideDistance;
                        nextSlideDistance = newSlideRate;

                    }
                    break;

                case "left":
                    if (nextSlideDistance > stopSlidingAt) {

                        worldX -= nextSlideDistance;
                        nextSlideDistance = newSlideRate;

                    }
                    break;

                case "right":
                    if (nextSlideDistance > stopSlidingAt) {

                        worldX += nextSlideDistance;
                        nextSlideDistance = newSlideRate;

                    }
                    break;
            }

            if (nextSlideDistance <= stopSlidingAt) {
                isPlayerSliding = false;
            }
        }
        /*
         * System.out.print("[" + GamePanel.currentFrameNum / 3600 + ":" +
         * GamePanel.currentFrameNum / 60 + ":"
         * + GamePanel.currentFrameNum % 60 + "] ");
         * System.out.print("worldX: " + worldX + ", worldY: " + worldY);
         * System.out.println(", direction: " + direction);
         */
    }

    public static void draw(Graphics2D g2) {

        image = null;

        switch (direction) {

            case "up":

                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }

                break;

            case "down":

                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }

                break;

            case "left":

                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }

                break;

            case "right":

                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }

                break;
        }

        wasMoveKeyPressed = isMoveKeyPressed;
        isMoveKeyPressed = false;

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}