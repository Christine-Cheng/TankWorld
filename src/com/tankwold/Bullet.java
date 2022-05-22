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
    private final static int SPEED = 15;
    private Direction direction;
    private boolean living = true;//子弹存活
    private TankFrame tankFrame = null;
    private Group group = Group.BAD;//默认子弹是敌方的
    Rectangle rect = new Rectangle();//初始化的时候就进行new Rectangle,这样属性就跟着对象走了
    
    public Bullet(int x, int y, Direction direction, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankFrame = tankFrame;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = BULLET_WIDTH;
        rect.height = BULLET_HEIGHT;
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
        //update rectangle 更新边界检测矩形边界
        rect.x = this.x;
        rect.y = this.y;
    
        live();
    }
    
    public boolean live() {
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;//子弹出边界就从容器中移除标志
        return living;
    }
    
    //碰撞测试
    public void collideWithTank(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;//战友不护害
        }
        
        /*用一个rectangle来记录位置 来做碰撞检测 但是用此种方式,占用内存,每次都要进行new Rectangle;更好的方式是把他作为属性,再new Bullet的时候就初始化了
        //在做碰撞检测的时候,是在遍历所有子弹和坦克的边界进行检测,这样的遍历次数是子弹数目m*坦克数目n,Rectangle的对象个数是m*n*2这样就在频繁new对象,垃圾回收器任务繁重.
        Rectangle rectangle1 = new Rectangle(this.x, this.y, BULLET_WIDTH, BULLET_HEIGHT);//bullet
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), tank.getTANK_WIDTH(), tank.getTANK_HEIGHT());//tank*/
        if (rect.intersects(tank.rect)) {
            this.die();//子弹
            tank.die();//坦克
        
            int explodedX = tank.getX() + tank.getTANK_WIDTH() / 2 - Exploded.getExplodedWidth() / 2;
            int explodedY = tank.getY() + tank.getTANK_HEIGHT() / 2 - Exploded.getExplodedHeight() / 2;
        
            //exploding when bullet collided with tank
            tankFrame.explodedList.add(new Exploded(explodedX, explodedY, tankFrame));
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
