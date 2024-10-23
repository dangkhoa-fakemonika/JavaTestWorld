package main;

import entity.Player;
import handlers.KeyHandler;
import handlers.MotionHandler;
import handlers.MouseHandler;
import item.ItemHandler;
import loader.AssetsLoader;
import object.SuperObject;
import tile.TileManager;
import ui_manager.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 64;
    public final int maxWorldRow = 64;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    public KeyHandler keyH = new KeyHandler();
    public MouseHandler mouseH = new MouseHandler(this);
    public MotionHandler motionH = new MotionHandler();
    Thread gameThread;
    public Sound sound = new Sound();
    public AssetsLoader assetsLoader = new AssetsLoader(this);

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public SuperObject[] objects = new SuperObject[10];

    public UserInterface userInterface = new UserInterface(this);

    public AssetSetter aSetter = new AssetSetter(this);
    public TileManager tileM = new TileManager(this);
    public ItemHandler itemHandler = new ItemHandler(this);


    int FPS = 60;

    public GamePanel() throws IOException, FontFormatException {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(motionH);
        this.setFocusable(true);
    }

    public void setupGame() throws Exception {
        aSetter.setObject();
        sound.isMuted = true;
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = 1e9/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1){
                try {
                    update();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
            }
        }
    }

    public void update() throws Exception {
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        for (SuperObject object : objects){
            if (object != null){
                object.draw(g2, this);
            }
        }

        player.draw(g2);
        userInterface.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i) throws Exception {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSFX(int i) throws Exception {
        sound.setFile(i);
        sound.play();
    }
}
