package ui_manager;

import item.Item;
import item.subitem.ITM_Coin;
import item.subitem.ITM_Pickaxe;
import main.GamePanel;
import ui_manager.interactables.InteractableSlot;

import java.awt.*;

public class ItemDragUI {
    InteractableSlot[] slots;
    GamePanel gp;
    Item holding;

    int mouseHoverIndex = -1;

    public ItemDragUI(GamePanel gp, int[][] pos){
        this.gp = gp;
        slots = new InteractableSlot[16];
        holding = null;

        for (int i = 0; i < slots.length; i++){
            if (pos[i][0] != -1)
                slots[i] = new InteractableSlot(pos[i][0], pos[i][1], gp, pos[i][2] == 1);
        }

        slots[5].isInsertable = false;

        slots[0].hostItem = new ITM_Coin(2, gp);
        slots[3].hostItem = new ITM_Coin(2, gp);
        slots[4].hostItem = new ITM_Coin(2, gp);

        slots[6].hostItem = new ITM_Pickaxe(gp);

    }

    public void updateCursor(){
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
        if (mouseHoverIndex != -1 &&
                gp.motionH.isInCell &&
                !gp.mouseH.isPickingUp &&
                holding == null &&
                gp.mouseH.clickAction
        ){
            if (slots[mouseHoverIndex].hostItem != null){
                holding = slots[mouseHoverIndex].hostItem;
                slots[mouseHoverIndex].hostItem = null;
                gp.mouseH.isPickingUp = true;
            }
            gp.mouseH.clickAction = false;
        }
    }

    public void detectDropItem(){
        if (mouseHoverIndex != -1 &&
                holding != null &&
                gp.motionH.isInCell &&
                gp.mouseH.isPickingUp &&
                gp.mouseH.clickAction
        ){
            if (slots[mouseHoverIndex].isInsertable)
            {
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
            }
            gp.mouseH.clickAction = false;
        }
    }

    public void loadUI(Graphics2D g2){
        updateCursor();

        if (gp.motionH.isInCell)
            if (!gp.mouseH.isPickingUp)
                detectPickUpItem();
            else
                detectDropItem();

        for (int i = 0; i < 16; i++){
            if (slots[i] != null){
                int itemX = slots[i].itemBox.x;
                int itemY = slots[i].itemBox.y;
                if (slots[i].hostItem != null)
                    g2.drawImage(
                            slots[i].hostItem.image,
                            itemX,
                            itemY,
                            null);
                if (itemX <= gp.motionH.mouseX && gp.motionH.mouseX <= itemX + gp.tileSize
                        && itemY <= gp.motionH.mouseY && gp.motionH.mouseY <= itemY + gp.tileSize){
                    g2.setColor(new Color(1f, 1f, 1f, .4f));
                    g2.fillRect(itemX, itemY, gp.tileSize, gp.tileSize);
                }
            }
            int itemX = gp.motionH.mouseX - gp.tileSize/2;
            int itemY = gp.motionH.mouseY - gp.tileSize/2;

            if (holding != null)
                g2.drawImage(
                        holding.image,
                        itemX,
                        itemY,
                        null);
        }
    }
}
