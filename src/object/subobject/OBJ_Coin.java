package object.subobject;

import main.GamePanel;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Coin extends SuperObject {
    public OBJ_Coin(GamePanel gp) throws IOException {
        name = "Coin";
        image = gp.assetsLoader.getObject("coin");
        collision = true;
    }
}
