package item.subitem;

import item.Blocks;
import main.GamePanel;

public class ITM_Bush extends Blocks {
    public ITM_Bush(GamePanel gp) {
        super.itemName = "Bush";
        image = gp.assetsLoader.getItem("bush");
    }
}
