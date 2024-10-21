package item;

import main.UtilityTool;

import java.awt.image.BufferedImage;

public class Item {
    public String itemName;
    public String itemId;
    public BufferedImage image;
    public boolean isUseAblePrimary = false;
    public boolean isUseAbleSecondary = false;
    public int stackLimit = 1;
    public int durability = -1;
    public UtilityTool tool = new UtilityTool();

    public void usePrimary(){

    }

    public void useSecondary(){

    }
}
