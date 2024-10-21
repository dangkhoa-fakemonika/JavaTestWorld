package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX, screenY;
    int handCooldown = 30, handCounter = 0;
    boolean useHand = false;
    String handUsed = "";
    public String[] inventory;
    public int[] inventoryCount;
    public int facingX = 0, facingY = 0, mapX = 0, mapY = 0;

    public Player(GamePanel gp, KeyHandler keyH) throws IOException {
        super(100);
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/ 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;
        inventory = new String[10];
        inventoryCount = new int[10];

        solidArea = new Rectangle(12, 20, (int) (gp.tileSize * 0.5), (int) (gp.tileSize * 0.5));
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 6;
        speed = 8;
        direction = "down";

        inventory[0] = "Pickaxe";
        inventoryCount[0] = 1;
        inventory[1] = "Coins";
        inventoryCount[1] = 1;
        inventory[2] = "Rocks";
        inventoryCount[2] = 1;
        inventory[3] = "Sticks";
        inventoryCount[3] = 1;
        inventory[4] = "Wall";
        inventoryCount[4] = 1;
        inventory[5] = "Bush";
        inventoryCount[5] = 1;

    }

    public void getPlayerImage() throws IOException {
        try{
            for (int i = 0; i < 4; i++){
                up[i] = setup("sus_up_" + i);
                down[i] = setup("sus_down_" + i);
                left[i] = setup("sus_left_" + i);
                right[i] = setup("sus_right_" + i);
            }
        }
        catch (IOException e){
            throw new IOException();
        }
    }

    public BufferedImage setup(String imageName) throws IOException {
        UtilityTool tool = new UtilityTool();
        try {
            BufferedImage newImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName + ".png")));
            return tool.scaleImage(newImage, gp.tileSize, gp.tileSize);
        } catch (IOException e){
            throw new IOException();
        }
    }

    public void update() throws Exception {
        boolean moving = false;

        if (keyH.upPressed){
            direction = "up";
            moving = true;
        }
        else if (keyH.downPressed){
            direction = "down";
            moving = true;
        }

        if (keyH.leftPressed){
            direction = "left";
            moving = true;
        }
        else if (keyH.rightPressed){
            direction = "right";
            moving = true;
        }

        if (keyH.shifted){
            moving = false;
        }

        if (keyH.leftTool && !useHand){
            handSecondary();
            useHand = true;
        }

        else if (keyH.rightTool && !useHand){
            handPrimary();
            useHand = true;
        }

        collisionOn = false;
        gp.collisionChecker.checkTile(this);

        int objIndex = gp.collisionChecker.checkObject(this, true);
        pickUpObject(objIndex);
        updateFacing();

        if (!collisionOn && moving){
            switch (direction){
                case "up":
                    if (worldY >= 0)
                        worldY -= speed;
                    break;
                case "down":
                    if (worldY <= gp.worldHeight - gp.tileSize)
                        worldY += speed;
                    break;
                case "left":
                    if (worldX >= 0)
                        worldX -= speed;
                    break;
                case "right":
                    if (worldX <= gp.worldWidth - gp.tileSize)
                        worldX += speed;
                    break;
                default:
                    break;
            }
        }

        if (!moving){
            spriteNum = 0;
        }
        else {
            spriteCounter++;
            if (spriteCounter > 12){
                gp.playSFX(1);
                spriteNum++;
                spriteNum %= 4;
                spriteCounter = 0;
            }
        }

        if (!moving && useHand){
            handCounter++;

            if (handCounter >= handCooldown){
                handCounter = 0;
                useHand = false;
                handUsed = "";
            }
        }
    }

    public void updateFacing(){
        int newCol = (gp.player.solidArea.x + gp.player.worldX + gp.player.solidArea.width / 2);
        int newRow = (gp.player.solidArea.y + gp.player.worldY + gp.player.solidArea.height / 2);
        mapX = newCol;
        mapY = newRow;

        switch (gp.player.direction){
            case "up":
                if ((newRow / gp.tileSize) > 0) newRow -= gp.tileSize; break;
            case "down":
                if ((gp.worldHeight - gp.tileSize - newRow) / gp.tileSize > 0) newRow += gp.tileSize; break;
            case "left":
                if (newCol / gp.tileSize > 0) newCol -= gp.tileSize; break;
            case "right":
                if ((gp.worldWidth - gp.tileSize - newCol)/ gp.tileSize > 0) newCol += gp.tileSize; break;
            default: break;
        }

        facingX = newCol / gp.tileSize;
        facingY = newRow / gp.tileSize;
    }

    public void handPrimary(){
        gp.tileM.breakTile();
    }

    public void handSecondary(){
        currentHP -= 10;
    }

    public void interact(){

    }

    public void pickUpObject(int index){
        if (index != 999){
            switch (gp.objects[index].name){
                case "Coin":
                    gp.objects[index] = null;
                    hasKey++;
                    break;
                case "Gate":
                    if (hasKey > 0){
                        gp.objects[index] = null;
                        hasKey--;
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction){
            case "up":
                image = up[spriteNum];
                break;
            case "down":
                image = down[spriteNum];
                break;
            case "left":
                image = left[spriteNum];
                break;
            case "right":
                image = right[spriteNum];
                break;
            default:
                break;
        }

        g2.drawImage(image, screenX, screenY, null);
        g2.drawRect(solidArea.x + screenX, solidArea.y + screenY, solidArea.width, solidArea.height);
    }
}
