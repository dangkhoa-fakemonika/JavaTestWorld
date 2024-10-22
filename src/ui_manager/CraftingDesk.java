package ui_manager;

import item.Item;
import main.GamePanel;

public class CraftingDesk {
    Item[] items;
    int[] itemCount;

    Item craftItem1;
    Item craftItem2;
    Item resultItem;
    Item pickUpItem;

    public CraftingDesk(GamePanel gp){
        items = new Item[10];
        itemCount = new int[10];
        for (int i = 0; i < 10; i++){
            items[i] = gp.player.inventory.items[i];
            itemCount[i] = gp.player.inventory.itemCount[i];
        }
        craftItem1 = null;
        craftItem2 = null;
        resultItem = null;
        pickUpItem = null;
    }


}
