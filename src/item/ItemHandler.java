package item;

import item.subitem.*;
import main.GamePanel;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ItemHandler {
    GamePanel gp;
    public Item[] items;

    public ItemHandler(GamePanel gp) throws IOException {
        this.gp = gp;
        items = new Item[6];

        items[0] = new ITM_Pickaxe(gp);
        items[1] = new ITM_Coin(20, gp);
        items[2] = new ITM_Rock(20, gp);
        items[3] = new ITM_Stick(20, gp);
        items[4] = new ITM_Wall(gp);
        items[5] = new ITM_Bush(gp);
    }


    public BufferedImage getItemImage(String itemName){
        return switch (itemName) {
            case "Pickaxe" -> items[0].image;
            case "Coins" -> items[1].image;
            case "Rocks" -> items[2].image;
            case "Sticks" -> items[3].image;
            case "Wall" -> items[4].image;
            case "Bush" -> items[5].image;
            default -> items[0].image;
        };

    }


}
