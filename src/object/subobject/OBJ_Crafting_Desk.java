package object.subobject;

import main.GamePanel;
import object.SuperObject;

public class OBJ_Crafting_Desk extends SuperObject {
    public OBJ_Crafting_Desk(GamePanel gp){
        name = "Crafting_Desk";
        image = gp.assetsLoader.getObject("crafting_desk");
        collision = true;
    }
}
