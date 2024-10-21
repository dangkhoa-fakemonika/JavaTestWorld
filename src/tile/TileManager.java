package tile;

import entity.Player;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/map_large_test.txt");
        getTileImage();
    }

    public void getTileImage() throws IOException {
        setup(0, "floor", false, 999);
        setup(1, "water", false, 999);
        setup(2, "wall", true, 4);
        setup(3, "grass", false, 999);
        setup(4, "bush", true, 4);
    }

    public void setup(int index, String imageName, boolean collision, int resistance) throws IOException {
        tiles[index] = new Tile();
        tiles[index].image = gp.assetsLoader.getTile(imageName);
        tiles[index].collision = collision;
        tiles[index].hardness = resistance;
    }

    public void loadMap(String mapName) throws IOException {
        try {
            InputStream is = getClass().getResourceAsStream(mapName);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < gp.maxWorldRow; row++){
                String line = br.readLine();
                String[] numbers = line.split(" ");

                for (int col = 0; col < gp.maxWorldCol; col++){
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
            }
            br.close();

        } catch (IOException e){
            throw new IOException();
        }
    }

    public void breakTile(){
        if ((gp.player.mapX != gp.player.facingX || gp.player.mapY != gp.player.facingY) && mapTileNum[gp.player.facingX][gp.player.facingY] == 2)
            mapTileNum[gp.player.facingX][gp.player.facingY] = 0;
    }

    public void placeTile(){
        if ((gp.player.mapX != gp.player.facingX || gp.player.mapY != gp.player.facingY) && mapTileNum[gp.player.facingX][gp.player.facingY] == 2)
            mapTileNum[gp.player.facingX][gp.player.facingY] = 0;
    }

    public void draw(Graphics2D g2){
        for (int row = 0; row < gp.maxWorldRow; row++){
            for (int col = 0; col < gp.maxWorldCol; col++){
                int tileNum = mapTileNum[col][row];
                int worldX = col*gp.tileSize;
                int worldY = row*gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                if (worldX + gp.tileSize - gp.player.worldX > - gp.player.screenX &&
                    worldX - gp.tileSize - gp.player.worldX < gp.player.screenX &&
                    worldY + gp.tileSize - gp.player.worldY > - gp.player.screenY &&
                    worldY - gp.tileSize - gp.player.worldY < gp.player.screenY){
                    g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
                }
            }
        }

        g2.drawRect(gp.player.facingX * gp.tileSize - gp.player.worldX + gp.player.screenX, gp.player.facingY * gp.tileSize - gp.player.worldY + gp.player.screenY, gp.tileSize, gp.tileSize);

    }
}
