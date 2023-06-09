package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/map02.txt");

    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO
                    .read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO
                    .read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/wall.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO
                    .read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/water.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO
                    .read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/dirt.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO
                    .read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/stone.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO
                    .read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/brick.png")));

            tile[6] = new Tile();
            tile[6].image = ImageIO
                    .read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/sand.png")));

            tile[7] = new Tile();
            tile[7].image = ImageIO
                    .read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/gravel.png")));

            tile[8] = new Tile();
            tile[8].image = ImageIO
                    .read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/tree.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = 0;

                    if (col < numbers.length) {
                        num = Integer.parseInt(numbers[col]);
                    }

                    mapTileNum[col][row] = num;
                    col++;

                    // System.out.println("TILE LOADED col: " + col + " row: " + row + " -- " +
                    // num);
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
        System.out.println("MAP " + filePath + " LOADED");
    }

    public void draw(Graphics2D g2) {

        int worldRow = 0;
        int worldCol = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;

            if (worldCol == gp.maxWorldCol) {

                worldCol = 0;
                worldRow++;
            }
        }
    }
}