package item.subitem;

import item.StackingItems;
import main.GamePanel;

public class ITM_Stick extends StackingItems {

    public ITM_Stick(int itemCount, GamePanel gp) {
        super(itemCount);
        super.itemName = "Sticks";
        image = gp.assetsLoader.getItem("wood");
    }

}
