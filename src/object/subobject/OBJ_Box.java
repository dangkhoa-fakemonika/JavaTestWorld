package object.subobject;

import item.Item;
import main.GamePanel;
import object.SuperObject;

public class OBJ_Box extends SuperObject {
    Item[] containingItems;
    public OBJ_Box(GamePanel gp) {
        name = "Box";
        image = gp.assetsLoader.getObject("box");
        containingItems = new Item[6];
    }
}
