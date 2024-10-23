package ui_manager;

import item.Item;
import item.subitem.ITM_Coin;
import item.subitem.ITM_Pickaxe;
import main.GamePanel;
import ui_manager.interactables.InteractableSlot;

public class ItemDragUI {
    InteractableSlot[] slots;
    GamePanel gp;
    Item holding;

    int mouseHoverIndex = -1;

    public ItemDragUI(GamePanel gp){
        this.gp = gp;
        slots = new InteractableSlot[16];
        holding = null;

        for (int i = 0; i < slots.length; i++){
            slots[i] = new InteractableSlot(200 + (i % 4) * 48, 200 + (i / 4) * 48, gp);
        }

        slots[0].hostItem = new ITM_Coin(2, gp);
        slots[3].hostItem = new ITM_Coin(2, gp);
        slots[4].hostItem = new ITM_Coin(2, gp);

        slots[6].hostItem = new ITM_Pickaxe(gp);

    }

    public void updateCursor(){
//        if (holding != null){
//            System.out.println(holding.itemName + " " + gp.mouseH.isPickingUp);
//        }
//        else System.out.println("nothing " + gp.mouseH.isPickingUp);


        for (int i = 0; i < slots.length; i++){
            int itemX = slots[i].itemBox.x;
            int itemY = slots[i].itemBox.y;
            if (itemX <= gp.motionH.mouseX && gp.motionH.mouseX <= itemX + gp.tileSize
                    && itemY <= gp.motionH.mouseY && gp.motionH.mouseY <= itemY + gp.tileSize){
                gp.motionH.isInCell = true;
                mouseHoverIndex = i;

                if (slots[i].hostItem != null)
                    gp.motionH.hasItem = true;
                return;
            }
        }
        gp.motionH.hasItem = false;
        gp.motionH.isInCell = false;
        mouseHoverIndex = -1;
    }

    public void detectPickUpItem(){
        System.out.println("finding pick");
        if (mouseHoverIndex != -1 &&
                gp.motionH.isInCell &&
                !gp.mouseH.isPickingUp &&
                holding == null &&
                gp.mouseH.clickAction
        ){
            holding = slots[mouseHoverIndex].hostItem;
            slots[mouseHoverIndex].hostItem = null;
            gp.mouseH.isPickingUp = true;
            gp.mouseH.clickAction = false;
        }
    }

    public void detectDropItem(){
        System.out.println("finding drops");
        if (mouseHoverIndex != -1 &&
                holding != null &&
                gp.motionH.isInCell &&
                gp.mouseH.isPickingUp &&
                gp.mouseH.clickAction
        ){
            if (slots[mouseHoverIndex].hostItem == null){
                slots[mouseHoverIndex].hostItem = holding;
                holding = null;
                gp.mouseH.isPickingUp = false;
            }
            else {
                Item tempItem = slots[mouseHoverIndex].hostItem;
                slots[mouseHoverIndex].hostItem = holding;
                holding = tempItem;
            }
            gp.mouseH.clickAction = false;
        }
    }
}
