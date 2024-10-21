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
        UtilityTool tool = new UtilityTool();
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
        int newCol = (gp.player.solidArea.x + gp.player.worldX + gp.player.solidArea.width / 2);
        int newRow = (gp.player.solidArea.y + gp.player.worldY + gp.player.solidArea.height / 2);
        int oldCol = newCol, oldRow = newRow;

        switch (gp.player.direction){
            case "up":
                if ((newRow / gp.tileSize) > 0) newRow -= gp.tileSize; break;
            case "down":
                if ((gp.worldHeight - gp.tileSize - newRow) / gp.tileSize > 0) newRow += gp.tileSize; break;
            case "left":
                if (newCol / gp.tileSize > 0) newCol -= gp.tileSize; break;
            case "right":
                if ((gp.worldWidth - gp.tileSize - newCol)/ gp.tileSize > 0) newCol += gp.tileSize; break;
            default: break;
        }

        int oldTileCol = oldCol / gp.tileSize,
                oldTileRow = oldRow / gp.tileSize,
                newTileCol = newCol / gp.tileSize,
                newTileRow = newRow / gp.tileSize;

        if ((oldTileCol != newTileCol || oldTileRow != newTileRow) && mapTileNum[newTileCol][newTileRow] == 2)
            mapTileNum[newTileCol][newTileRow] = 0;
    }

    public void placeTile(){
        int newCol = (gp.player.solidArea.x + gp.player.worldX + gp.player.solidArea.width / 2) / gp.tileSize;
        int newRow = (gp.player.solidArea.y + gp.player.worldY + gp.player.solidArea.height / 2)  / gp.tileSize;

        switch (gp.player.direction){
            case "up":
                if (newRow > 0) newRow--; break;
            case "down":
                if (newRow < gp.maxWorldRow - 1) newRow++; break;
            case "left":
                if (newCol > 0) newCol--; break;
            case "right":
                if (newCol < gp.maxWorldCol - 1) newCol++; break;
            default: break;
        }

        if (mapTileNum[newCol][newRow] != 2) mapTileNum[newCol][newRow] = 2;
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

        int newCol = (gp.player.solidArea.x + gp.player.worldX + gp.player.solidArea.width / 2);
        int newRow = (gp.player.solidArea.y + gp.player.worldY + gp.player.solidArea.height / 2);

        switch (gp.player.direction){
            case "up":
                if ((newRow / gp.tileSize) > 0) newRow -= gp.tileSize; break;
            case "down":
                if ((gp.worldHeight - gp.tileSize - newRow) / gp.tileSize > 0) newRow += gp.tileSize; break;
            case "left":
                if (newCol / gp.tileSize > 0) newCol -= gp.tileSize; break;
            case "right":
                if ((gp.worldWidth - gp.tileSize - newCol)/ gp.tileSize > 0) newCol += gp.tileSize; break;
            default: break;
        }

        int tileCol = newCol / gp.tileSize;
        int tileRow = newRow / gp.tileSize;

        g2.drawRect(tileCol * gp.tileSize - gp.player.worldX + gp.player.screenX, tileRow * gp.tileSize - gp.player.worldY + gp.player.screenY, gp.tileSize, gp.tileSize);

    }
}
