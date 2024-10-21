package ui_manager;

import main.GamePanel;
import main.UtilityTool;
import object.subobject.OBJ_Coin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UserInterface {
    GamePanel gp;
    Font arial;
//    InputStream inputStream = Font.class.getResourceAsStream("/fonts/minecraft_font.ttf");
//    Font myFont;
//    {
//        assert inputStream != null;
//        myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
//    }
//    Font myFont = Font.createFont(Font.TRUETYPE_FONT, new File("/fonts/minecraft_font.ttf"));
    BufferedImage coinImage, healthBar, healthTexture;
    public boolean messageOn = false;
    public String message = "";
    int msgCounter = 0;
    UtilityTool tool;

//    boolean openInventory = false;
//    boolean openInteractiveUI = false;

    public UserInterface(GamePanel gp) throws IOException, FontFormatException {
        this.gp = gp;
        arial = new Font("Arial", Font.BOLD, 30);
        OBJ_Coin coin = new OBJ_Coin(gp);
        coinImage = coin.image;
        tool = new UtilityTool();

        try{
            healthBar = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/health_bar.png")));
            healthBar = tool.scaleImage(healthBar, 192, 32);
            healthTexture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/health_texture.png")));
            healthTexture = tool.scaleImage(healthTexture, 192, 32);
        } catch (IOException e) {
            throw new IOException();
        }

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        if (gp.keyH.inventoryPressed){
            drawInventoryUI(g2);
        }
        else {
            drawGameUI(g2);
        }
    }

    public void drawGameUI(Graphics2D g2){
        if (messageOn){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize, gp.screenHeight - gp.tileSize);

            msgCounter++;

            if (msgCounter > 120){
                msgCounter = 0;
                messageOn = false;
            }
        }

        for (int i = 0; i < gp.player.inventoryCount.length; i++){
            if (gp.player.inventoryCount[i] > 0){
                g2.drawImage(gp.itemHandler.getItemImage(gp.player.inventory[i]), (int)(gp.tileSize * (gp.maxScreenCol - 1.5 - (1 - (i % 2))* 0.5)), (int)(gp.tileSize * (i + 0.5)*0.6) , null);
            }
        }

        g2.drawImage(healthBar, 10, 10, null);
        g2.setColor(Color.red);
        g2.fillRect(14, 14, (int)(184 * gp.player.currentHP / gp.player.maxHP) , 24);
        g2.drawImage(healthTexture, 10, 10, null);
    }

    public void drawCraftingUI(Graphics2D g2){
        g2.setColor(Color.green);
        g2.fillRect(200, 200, 200, 200);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString("Crafting Placeholder", 300, 300);
    }

    public void drawChestUI(Graphics2D g2){
        g2.setColor(Color.green);
        g2.fillRect(200, 200, 200, 200);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString("Chest Placeholder", 300, 300);
    }

    public void drawInventoryUI(Graphics2D g2){
        g2.setColor(Color.green);
        g2.fillRect(200, 200, 200, 200);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString("Inventory Placeholder", 300, 300);
    }
}

