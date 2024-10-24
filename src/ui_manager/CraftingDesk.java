package ui_manager;

import item.Item;
import item.subitem.ITM_Coin;
import main.GamePanel;

import java.util.Objects;

public class CraftingDesk {
    Item[] items;
    int[] itemCount;
    ItemDragUI dragUI;
    boolean crafted = false;
    boolean takeItem = false;
    GamePanel gp;

    public CraftingDesk(GamePanel gp, ItemDragUI dragUI){
        items = new Item[10];
        itemCount = new int[10];
        this.gp = gp;
        this.dragUI = dragUI;
    }

    public void parseCrafting(){
        if (!crafted && dragUI.slots[12].hostItem != null && dragUI.slots[13].hostItem != null && dragUI.slots[14].hostItem != null){
            if (Objects.equals(dragUI.slots[12].hostItem.itemName, "Coins") &&
                    Objects.equals(dragUI.slots[13].hostItem.itemName, "Coins") &&
                    Objects.equals(dragUI.slots[14].hostItem.itemName, "Coins")) {
                dragUI.slots[15].hostItem = new ITM_Coin(2, gp);
                crafted = true;
                takeItem = false;
            }
        }

        if (crafted && (dragUI.slots[12].hostItem == null || dragUI.slots[13].hostItem == null || dragUI.slots[14].hostItem == null)){
            dragUI.slots[15].hostItem = null;
            takeItem = false;
            crafted = false;
        }

        if (crafted && dragUI.slots[15].hostItem == null){
            takeItem = true;
        }

        if (crafted && takeItem){
            dragUI.slots[12].hostItem = null;
            dragUI.slots[13].hostItem = null;
            dragUI.slots[14].hostItem = null;
            crafted = false;
            takeItem = false;
        }
    }

}
