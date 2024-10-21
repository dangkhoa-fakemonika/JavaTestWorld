package object.subobject;

import main.GamePanel;
import object.SuperObject;

public class OBJ_Box extends SuperObject {
    public OBJ_Box(GamePanel gp) {
        name = "Box";
        image = gp.assetsLoader.getObject("box");
    }
}
