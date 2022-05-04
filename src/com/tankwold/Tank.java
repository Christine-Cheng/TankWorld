package com.tankwold;

import com.tankwold.enums.Direction;

import java.awt.*;

/**
 * the tank's default properties
 *
 * @author Happy
 * @create 2022/5/2-21:48
 **/
public class Tank {
    private int x, y;
    private int TANK_WIDTH = 50, TANK_HEIGHT = 50;
    private Direction direction = Direction.DOWN;//set direction's initial value
    private static final int SPEED = 10;//define step constants
    private boolean moving = false;//set default moving status
    private TankFrame tankFrame = null;
    
    public Tank(int x, int y, Direction direction, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankFrame = tankFrame;
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
    
    public void paint(Graphics graphics) {
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
    }
    
    /**
     * 开火
     * 由于开火这个属性属于Tank,但是Tank自己new的Bullet不能够信息显示开火发射子弹的信息.只有TankFrame这个才可以显示画面
     * 如果Tank需要把new的Bullet放到TankFrame进行显示,那么Tank必须可以持有TankFrame的引用.即是,在成员变量中添加TankFrame属性
     */
    public void fire() {
        //tankFrame.bullet = new Bullet(this.x, this.y, this.direction);//单个子弹不够用
        tankFrame.bulletList.add(new Bullet(this.x, this.y, this.direction, this.tankFrame, this));
    }
}
