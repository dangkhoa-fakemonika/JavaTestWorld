package item.subitem;

import item.StackingItems;
import main.GamePanel;

public class ITM_Coin extends StackingItems {
    public ITM_Coin(int itemCount, GamePanel gp) {
        super(itemCount);
        super.itemName = "Coins";
        image = gp.assetsLoader.items[1];
    }
}
