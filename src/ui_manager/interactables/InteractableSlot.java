package ui_manager.interactables;

import item.Item;
import main.GamePanel;

import java.awt.*;

public class InteractableSlot {
    public Item hostItem;
    public Rectangle itemBox;
    public boolean isInsertable = true;

    public InteractableSlot(int col, int row, GamePanel gp, boolean isInsertable){
        hostItem = null;
        itemBox = new Rectangle();
        itemBox.x = col;
        itemBox.y = row;
        itemBox.width = gp.tileSize;
        itemBox.height = gp.tileSize;
        this.isInsertable = isInsertable;
    }

}
