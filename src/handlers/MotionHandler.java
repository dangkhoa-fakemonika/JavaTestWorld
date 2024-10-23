package handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MotionHandler implements MouseMotionListener {
    boolean dragging = false;
    public boolean isInCell = false;
    public boolean hasItem = false;
    public int mouseX, mouseY;

    @Override
    public void mouseDragged(MouseEvent e) {
        dragging = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
