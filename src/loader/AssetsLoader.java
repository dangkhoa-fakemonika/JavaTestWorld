package loader;

import main.GamePanel;
import main.UtilityTool;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class AssetsLoader {
    public BufferedImage[] tiles, items, windows, objects;
    public BufferedImage healthBar, healthEffect;
    GamePanel gp;

    String[] tileNames = {"floor", "water", "wall", "grass", "bush"};
    String[] itemNames = {"pickaxe", "coin_item", "rock", "wood", "wall_item", "bush_item"};
    String[] objectNames = {"box", "coin", "gate"};

    public AssetsLoader(GamePanel gp) throws IOException {
        this.gp = gp;
        UtilityTool tool = new UtilityTool();
        tiles = new BufferedImage[10];
        items = new BufferedImage[10];
        windows = new BufferedImage[5];
        objects = new BufferedImage[3];
        try {
            for (int index = 0; index < tileNames.length; index++){
                tiles[index] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + tileNames[index] + ".png")));
                tiles[index] = tool.scaleImage(tiles[index], gp.tileSize, gp.tileSize);
            }
            for (int index = 0; index < itemNames.length; index++){
                items[index] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/" + itemNames[index] + ".png")));
                items[index] = tool.scaleImage(items[index], (int)(gp.tileSize * 0.75), (int)(gp.tileSize * 0.75));
            }

            for (int index = 0; index < objectNames.length; index++){
                objects[index] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/" + objectNames[index] + ".png")));
                objects[index] = tool.scaleImage(objects[index], gp.tileSize, gp.tileSize);
            }
        } catch (IOException e){
            throw new IOException();
        }
    }

    public BufferedImage getTile(String tileName){
        return switch (tileName){
            case "floor" -> tiles[0];
            case "water" -> tiles[1];
            case "wall" -> tiles[2];
            case "grass" -> tiles[3];
            case "bush" -> tiles[4];
            default -> tiles[0];
        };
    }

    public BufferedImage getItem(String itemName){
        return switch (itemName){
            case "pickaxe" -> items[0];
            case "coin_item" -> items[1];
            case "rock" -> items[2];
            case "wood" -> items[3];
            case "wall_item" -> items[4];
            case "bush_item" -> items[5];
            default -> items[0];
        };
    }

    public BufferedImage getObject(String objectName){
        return switch (objectName){
            case "box" -> objects[0];
            case "coin" -> objects[1];
            case "gate" -> objects[2];
            default -> objects[0];
        };
    }
}
