public class Collision {

    public static void checkPlayerCollision(player p, int dx, int dy) {

        // Check collision with map tiles
        int tileX = (int) ((p.worldX + dx) / GamePanel.tileSize); //cannot make a static refrence to non-static feild
        int tileY = (int) ((p.worldY + dy) / GamePanel.tileSize); // cannot make a static refrence to non-static feild

        // Check collision with solid tiles
        if (GamePanel.tileM.mapTileNum[tileY][tileX] == 1) { // cannot make a static refrence to non-static feild
            dx = 0;
            dy = 0;
        }

        // Check collision with other entities
        for (Entity entity : GamePanel.entities) { //.entities is an unresolved feild
            if (entity == p) {
                continue;
            }
            if (entity.getBounds().intersects(p.getBounds(dx, dy))) {//getBounds is undefined
                dx = 0;
                dy = 0;
                break;
            }
        }

        p.setDx(dx); // does not exist for the type player
        p.setDy(dy); // does not exist for the type player
    }
}
