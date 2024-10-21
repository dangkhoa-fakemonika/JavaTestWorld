package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public int hasKey = 0;
    public final int maxHP;
    public int currentHP;

    public BufferedImage[]
        up = new BufferedImage[4],
        down = new BufferedImage[4],
        left = new BufferedImage[4],
        right = new BufferedImage[4];
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 0;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = true;

    public Entity(int maxHP){
        this.maxHP = maxHP;
        currentHP = maxHP;
    }
}
