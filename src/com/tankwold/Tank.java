package com.tankwold;

import com.tankwold.enums.Direction;
import com.tankwold.enums.Group;

import java.awt.*;
import java.util.Random;

/**
 * the tank's default properties
 *
 * @author Happy
 * @create 2022/5/2-21:48
 **/
public class Tank {
    private int x, y;//坦克的起点
    private int TANK_WIDTH = ResourceManager.tankD.getWidth();//默认取用tank向下的宽度
    private int TANK_HEIGHT = ResourceManager.tankD.getHeight();//默认取用tank向下的高度
    private Direction direction = Direction.DOWN;//set direction's initial value
    private static final int SPEED = 10;//define step constants
    private boolean moving = true;//set default moving status
    private TankFrame tankFrame = null;
    private boolean living = true;//is tank survive
    private Group group = Group.BAD;//default tank's group is bad
    private Random random = new Random();
    
    {
        switch (this.direction) {
            case LEFT:
                TANK_WIDTH = ResourceManager.tankL.getWidth();
                TANK_HEIGHT = ResourceManager.tankL.getHeight();
                break;
            case UP:
                TANK_WIDTH = ResourceManager.tankU.getWidth();
                TANK_HEIGHT = ResourceManager.tankU.getHeight();
                break;
            case RIGHT:
                TANK_WIDTH = ResourceManager.tankR.getWidth();
                TANK_HEIGHT = ResourceManager.tankR.getHeight();
                break;
            case DOWN:
                TANK_WIDTH = ResourceManager.tankD.getWidth();
                TANK_HEIGHT = ResourceManager.tankD.getHeight();
                break;
        }
    }
    
    public Tank(int x, int y, Direction direction, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankFrame = tankFrame;
        this.group = group;
    }
    
    
    public void paint(Graphics graphics) {
        if (!living)
            tankFrame.enemyTankList.remove(this);//if tank is dead , tank will not paint , and remove tank from container
    
        Color color = graphics.getColor();
        //graphics.setColor(Color.yellow);
        //graphics.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);//改变起点让他动起来
        //graphics.setColor(color);
        //坦克:图片代替
        switch (direction) {
            case LEFT:
                graphics.drawImage(ResourceManager.tankL, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourceManager.tankU, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceManager.tankR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceManager.tankD, x, y, null);
                break;
        }
        move();
    }
    
    private void move() {
        if (!moving) return;//when tank stops,straight return;
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
    
        if (random.nextInt(10) > 8) this.fire();//随机开火
    }
    
    /**
     * 开火
     * 由于开火这个属性属于Tank,但是Tank自己new的Bullet不能够信息显示开火发射子弹的信息.只有TankFrame这个才可以显示画面
     * 如果Tank需要把new的Bullet放到TankFrame进行显示,那么Tank必须可以持有TankFrame的引用.即是,在成员变量中添加TankFrame属性
     */
    public void fire() {
        //tankFrame.bullet = new Bullet(this.x, this.y, this.direction);//单个子弹不够用
    
        //计算子弹的具体位置:根据坦克图片的大小,和左上角的位置计算子弹左上角的位置
        int BULLET_WIDTH = 0;//子弹的宽
        int BULLET_HEIGHT = 0;//子弹的高
        int bulletX = 0;
        int bulletY = 0;
        switch (direction) {
            case LEFT:
                BULLET_WIDTH = ResourceManager.bulletL.getWidth();
                BULLET_HEIGHT = ResourceManager.bulletL.getHeight() + 1;//末尾常数微调
                bulletX = this.x + this.getTANK_WIDTH() / 2 - this.getTANK_HEIGHT() / 2 - BULLET_WIDTH / 2 + 4;//末尾常数微调
                bulletY = this.y + this.getTANK_HEIGHT() / 2 - BULLET_HEIGHT / 2;
                break;
            case UP:
                BULLET_WIDTH = ResourceManager.bulletU.getWidth() - 2;//末尾常数微调
                BULLET_HEIGHT = ResourceManager.bulletU.getHeight();
                bulletX = this.x + this.getTANK_WIDTH() / 2 - BULLET_WIDTH / 2;
                bulletY = this.y + this.getTANK_HEIGHT() / 2 - this.getTANK_WIDTH() / 2 - BULLET_HEIGHT / 2 + 4;//末尾常数微调
                break;
            case RIGHT:
                BULLET_WIDTH = ResourceManager.bulletR.getWidth();
                BULLET_HEIGHT = ResourceManager.bulletR.getHeight();
                bulletX = this.x + this.getTANK_WIDTH() / 2 + this.getTANK_HEIGHT() / 2 - BULLET_WIDTH / 2 - 4;//末尾常数微调
                bulletY = this.y + this.getTANK_HEIGHT() / 2 - BULLET_HEIGHT / 2;
                break;
            case DOWN:
                BULLET_WIDTH = ResourceManager.bulletD.getWidth() + 2;//末尾常数微调
                BULLET_HEIGHT = ResourceManager.bulletD.getHeight();
                bulletX = this.x + this.getTANK_WIDTH() / 2 - BULLET_WIDTH / 2;
                bulletY = this.y + this.getTANK_HEIGHT() / 2 + this.getTANK_WIDTH() / 2 - BULLET_HEIGHT / 2 - 4;//末尾常数微调
                break;
        }
    
        tankFrame.bulletList.add(new Bullet(bulletX, bulletY, this.direction, this.group, this.tankFrame));
    }
    
    public void die() {
        this.living = false;
    }
    
    public Group getGroup() {
        return group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    
    public int getTANK_WIDTH() {
        return TANK_WIDTH;
    }
    
    public int getTANK_HEIGHT() {
        return TANK_HEIGHT;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public boolean isMoving() {
        return moving;
    }
    
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
