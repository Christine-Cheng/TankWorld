package com.tankwold;

import com.tankwold.enums.Direction;

import java.awt.*;

/**
 * @author Happy
 * @create 2022/5/2-22:37
 **/
public class Bullet {
    private int x, y;
    private final static int SPEED = 20;
    private Direction direction;
    private final static int WIDTH = 10, HEIGHT = 10;
    private boolean live = true;//子弹存活
    private TankFrame tankFrame = null;
    
    public Bullet(int x, int y, Direction direction, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankFrame = tankFrame;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public void paint(Graphics graphics) {
        if (!live) {
            //此为普通for循环下进行删除,配合TankFrame.java中for (int i = 0; i < bulletList.size(); i++)
            tankFrame.bulletList.remove(this);//子弹死了就移除
        }
        
        Color color = graphics.getColor();
        graphics.setColor(Color.red);
        graphics.fillOval(x, y, WIDTH, HEIGHT);
        graphics.setColor(color);
        move();
    }
    
    //the bullet move
    private void move() {
        switch (direction) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        
        live();
    }
    
    public boolean live() {
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;//子弹出边界就从容器中移除标志
        return live;
    }
}
