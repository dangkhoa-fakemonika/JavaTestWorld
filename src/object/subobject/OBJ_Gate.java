package object.subobject;

import main.GamePanel;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Gate extends SuperObject {
    public OBJ_Gate(GamePanel gp) throws IOException {
        name = "Gate";
        image = gp.assetsLoader.getObject("gate");
        collision = true;
    }
}
