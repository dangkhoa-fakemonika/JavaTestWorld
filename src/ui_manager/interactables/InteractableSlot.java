package ui_manager.interactables;

import item.Item;
import main.GamePanel;

import java.awt.*;

public class InteractableSlot {
    public Item hostItem;
    public Rectangle itemBox;

    public InteractableSlot(int col, int row, GamePanel gp){
        hostItem = null;
        itemBox = new Rectangle();
        itemBox.x = col;
        itemBox.y = row;
        itemBox.width = gp.tileSize;
        itemBox.height = gp.tileSize;
    }

}
