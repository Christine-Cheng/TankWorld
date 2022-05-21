package com.tankwold;

import java.awt.*;

/**
 * @author Happy
 * @create 2022/5/2-22:37
 **/
public class Exploded {
    
    private int x, y;
    private static int EXPLODED_WIDTH = ResourceManager.explodeArr[0].getWidth();
    private static int EXPLODED_HEIGHT = ResourceManager.explodeArr[0].getHeight();
    
    //private boolean living = true;
    private TankFrame tankFrame = null;
    private int step = 0;//爆炸的步骤
    
    
    public Exploded(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    
        //new Audio("audio/explode.wav").play();
        new Thread(() -> new Audio("audio/explode.wav").play()).start();//启动一个线程干活,这样爆炸就不会卡顿了
    }
    
    
    public void paint(Graphics graphics) {
        graphics.drawImage(ResourceManager.explodeArr[step++], x, y, null);
        if (step >= ResourceManager.explodeArr.length) {
            //step = 0;
            tankFrame.explodedList.remove(this);
        }
    }
    
    public static int getExplodedWidth() {
        return EXPLODED_WIDTH;
    }
    
    public static int getExplodedHeight() {
        return EXPLODED_HEIGHT;
    }
}
