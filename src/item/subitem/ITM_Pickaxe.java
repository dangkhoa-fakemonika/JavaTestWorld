package item.subitem;

import item.ToolItem;
import main.GamePanel;

public class ITM_Pickaxe extends ToolItem {
    public ITM_Pickaxe(GamePanel gp) {
        super.itemName = "Pickaxe";
        image = gp.assetsLoader.getItem("pickaxe");
    }
}
