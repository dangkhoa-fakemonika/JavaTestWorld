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

    public void addItem(String itemName){
        switch (itemName){
            case "Coins":
                if (gp.player.inventoryCount[1] == 0){
                    gp.player.inventoryCount[1] = 1;
                }
                else if (gp.player.inventoryCount[1] < items[1].stackLimit){
                    gp.player.inventoryCount[1]++;
                }
                break;
            case "Rocks":
                if (gp.player.inventoryCount[2] == 0){
                    gp.player.inventoryCount[2] = 1;
                }
                else if (gp.player.inventoryCount[2] < items[2].stackLimit){
                    gp.player.inventoryCount[2]++;
                }
                break;
            case "Sticks":
                if (gp.player.inventoryCount[3] == 0){
                    gp.player.inventoryCount[3] = 1;
                }
                else if (gp.player.inventoryCount[3] < items[3].stackLimit){
                    gp.player.inventoryCount[3]++;
                }
                break;
            case "Wall":
                if (gp.player.inventoryCount[4] == 0){
                    gp.player.inventoryCount[4] = 1;
                }
                else if (gp.player.inventoryCount[4] < items[4].stackLimit){
                    gp.player.inventoryCount[4]++;
                }
                break;
            case "Bush":
                if (gp.player.inventoryCount[5] == 0){
                    gp.player.inventoryCount[5] = 1;
                }
                else if (gp.player.inventoryCount[5] < items[5].stackLimit){
                    gp.player.inventoryCount[5]++;
                }
                break;
            default:
                break;
        }
    }

    public void removeItem(String itemName){

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
