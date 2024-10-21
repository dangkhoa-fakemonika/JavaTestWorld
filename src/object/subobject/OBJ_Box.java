package object.subobject;

import main.GamePanel;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Box extends SuperObject {
    public OBJ_Box(GamePanel gp) throws IOException {
        name = "Box";
        image = gp.assetsLoader.objects[0];
    }
}
