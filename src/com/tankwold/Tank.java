package com.tankwold;

import com.tankwold.enums.Direction;

import java.awt.*;

/**
 * the tank's default properties
 * @author Happy
 * @create 2022/5/2-21:48
 **/
public class Tank {
    private int x, y;
    private Direction direction = Direction.DOWN;//set direction's initial value
    private static final int SPEED = 10;//define step constants
    
    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public void paint(Graphics graphics) {
        graphics.fillRect(x, y, 50, 50);//改变起点让他动起来
    
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
}
