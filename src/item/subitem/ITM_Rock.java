package item.subitem;

import item.StackingItems;
import main.GamePanel;

public class ITM_Rock extends StackingItems {
    public ITM_Rock(int stackCount, GamePanel gp) {
        super(stackCount);

        super.itemName = "Rocks";
        image = gp.assetsLoader.getItem("rock");
    }
}
