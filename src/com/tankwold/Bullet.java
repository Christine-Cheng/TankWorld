package com.tankwold;

import com.tankwold.enums.Direction;

import java.awt.*;

/**
 * @author Happy
 * @create 2022/5/2-22:37
 **/
public class Bullet {
    private Tank tank;
    private int x, y;
    private final static int BULLET_WIDTH = 10, BULLET_HEIGHT = 10;
    private final static int SPEED = 20;
    private Direction direction;
    private boolean live = true;//子弹存活
    private TankFrame tankFrame = null;
    
    public Bullet(int x, int y, Direction direction, TankFrame tankFrame, Tank tank) {
        this.x = tank.getX() + tank.getTANK_WIDTH() / 2 - BULLET_WIDTH / 2;
        this.y = tank.getY() + tank.getTANK_HEIGHT() / 2 - BULLET_HEIGHT / 2;
        this.direction = direction;
        this.tankFrame = tankFrame;
        this.tank = tank;
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
        //graphics.setColor(Color.red);
        //graphics.fillOval(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        //graphics.setColor(color);
        //子弹:图片代替
        switch (direction) {
            case LEFT:
                graphics.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourceManager.bulletU, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceManager.bulletD, x, y, null);
                break;
        }
    
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
