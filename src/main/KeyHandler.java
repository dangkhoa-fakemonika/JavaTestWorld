package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean rightTool, leftTool, shifted;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = true;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S){
            downPressed = true;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }

        if (code == KeyEvent.VK_OPEN_BRACKET){
            leftTool = true;
        }

        if (code == KeyEvent.VK_CLOSE_BRACKET){
            rightTool = true;
        }

        if (code == KeyEvent.VK_SHIFT){
            shifted = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }

        if (code == KeyEvent.VK_OPEN_BRACKET){
            leftTool = false;
        }

        if (code == KeyEvent.VK_CLOSE_BRACKET){
            rightTool = false;
        }

        if (code == KeyEvent.VK_SHIFT){
            shifted = false;
        }
    }
}
