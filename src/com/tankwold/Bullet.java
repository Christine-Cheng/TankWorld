package com.tankwold;

import com.tankwold.enums.Direction;

import java.awt.*;

/**
 * @author Happy
 * @create 2022/5/2-22:37
 **/
public class Bullet {
    private int x, y;
    private final static int SPEED = 10;
    private Direction direction;
    private final static int WIDTH = 10, HEIGHT = 10;
    
    public Bullet(int x, int y, Direction direction) {
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
    }
    
}
