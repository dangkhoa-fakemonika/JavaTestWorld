package item.subitem;

import item.Blocks;
import main.GamePanel;

public class ITM_Wall extends Blocks {
    public ITM_Wall(GamePanel gp) {

        super.itemName = "Wall";
        image = gp.assetsLoader.getItem("wall");
    }
}
