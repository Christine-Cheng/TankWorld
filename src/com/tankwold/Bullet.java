package com.tankwold;

import com.tankwold.enums.Direction;
import com.tankwold.enums.Group;

import java.awt.*;

/**
 * @author Happy
 * @create 2022/5/2-22:37
 **/
public class Bullet {
    private int x, y;//子弹的起点
    private static int BULLET_WIDTH = ResourceManager.bulletD.getWidth();//默认取用子弹向下的宽度
    private static int BULLET_HEIGHT = ResourceManager.bulletD.getHeight();//默认取用子弹向下的高度
    private final static int SPEED = 10;
    private Direction direction;
    private boolean living = true;//子弹存活
    private TankFrame tankFrame = null;
    private Group group = Group.BAD;//默认子弹是敌方的
    
    public Bullet(int x, int y, Direction direction, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankFrame = tankFrame;
        this.group = group;
    }
    
    
    public void paint(Graphics graphics) {
        //if (!live) {
        //    //此为普通for循环下进行删除,配合TankFrame.java中for (int i = 0; i < bulletList.size(); i++)
        //    tankFrame.bulletList.remove(this);//子弹死了就移除
        //}
        
        Color color = graphics.getColor();
        //graphics.setColor(Color.red);
        //graphics.fillOval(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        //graphics.setColor(color);
        //子弹:图片代替
        switch (direction) {
            case LEFT:
                //BULLET_WIDTH = ResourceManager.bulletL.getWidth();
                //BULLET_HEIGHT = ResourceManager.bulletL.getHeight();
                graphics.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case UP:
                //BULLET_WIDTH = ResourceManager.bulletU.getWidth();
                //BULLET_HEIGHT = ResourceManager.bulletU.getHeight();
                graphics.drawImage(ResourceManager.bulletU, x, y, null);
                break;
            case RIGHT:
                //BULLET_WIDTH = ResourceManager.bulletR.getWidth();
                //BULLET_HEIGHT = ResourceManager.bulletR.getHeight();
                graphics.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            case DOWN:
                //BULLET_WIDTH = ResourceManager.bulletD.getWidth();
                //BULLET_HEIGHT = ResourceManager.bulletD.getHeight();
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;//子弹出边界就从容器中移除标志
        return living;
    }
    
    //碰撞测试
    public void collideWithTank(Tank tank) {
        if (this.group == tank.getGroup()) return;//战友不护害
    
        //todo 用一个rectangle来记录位置 来做碰撞检测
        Rectangle rectangle1 = new Rectangle(this.x, this.y, BULLET_WIDTH, BULLET_HEIGHT);//bullet
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), tank.getTANK_WIDTH(), tank.getTANK_HEIGHT());//tank
        if (rectangle1.intersects(rectangle2)) {
            this.die();//子弹
            tank.die();//坦克
    
            //exploding when bullet collided with tank
            tankFrame.explodedList.add(new Exploded(x, y, tankFrame));
        }
    }
    
    private void die() {
        this.living = false;
    }
    
    
    public Group getGroup() {
        return group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
}
