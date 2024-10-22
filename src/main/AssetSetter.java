package main;

import object.subobject.OBJ_Box;
import object.subobject.OBJ_Coin;
import object.subobject.OBJ_Crafting_Desk;
import object.subobject.OBJ_Gate;

import java.io.IOException;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject() throws IOException {

        gp.objects[0] = new OBJ_Coin(gp);
        gp.objects[0].worldX = 5 * gp.tileSize;
        gp.objects[0].worldY = 10 * gp.tileSize;

        gp.objects[2] = new OBJ_Box(gp);
        gp.objects[2].worldX = 7 * gp.tileSize;
        gp.objects[2].worldY = 10 * gp.tileSize;

        gp.objects[1] = new OBJ_Crafting_Desk(gp);
        gp.objects[1].worldX = 4 * gp.tileSize;
        gp.objects[1].worldY = 4 * gp.tileSize;
    }
}
