package ui_manager.message;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    Queue<DisplayMessage> msgQueue;
    Font arial;

    public MessageQueue(){
        msgQueue = new LinkedList<>();
        arial = new Font("Arial", Font.BOLD, 30);
    }

    public void addMessage(String message){
        DisplayMessage msg = new DisplayMessage(message);
        msgQueue.add(msg);
    }

    public void renderMessages(Graphics2D g2){
        DisplayMessage[] msgQueueArr = (DisplayMessage[]) msgQueue.toArray();
        int maxMsg = 5;
        if (msgQueueArr.length < maxMsg){
            maxMsg = msgQueueArr.length;
        }

        for (DisplayMessage msg : msgQueueArr){
            msg.cooldown -= 1;
        }
        g2.setFont(arial.deriveFont(30F));
        for (int i = 0; i < maxMsg; i++){
            g2.drawString(msgQueueArr[i].message, 100, 100 + i * 30);
        }

        while (!msgQueue.isEmpty() && msgQueue.peek().cooldown <= 0){
            msgQueue.poll();
        }
    }
}
