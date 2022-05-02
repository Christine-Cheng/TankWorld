package com.tankwold;

import com.tankwold.enums.Direction;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Happy
 * @create 2022/5/1-0:12
 **/
public class TankFrame extends Frame {
    
    Tank myTank = new Tank(200, 200, Direction.DOWN);//Encapsulate the attributes of the tank into the tank class
    
    public TankFrame() {
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("TankWar");
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new MyKeyListener());
    }
    
    /**
     * Inherit Frame class and override paint() method
     * The origin of the coordinates is in the upper left corner
     * <p>
     * paint() method will be called twice when the program starts and the window is blocked
     *
     * @param graphics
     */
    @Override
    public void paint(Graphics graphics) {
        /**
         * In order to not destroy the encapsulation characteristics,
         * let the encapsulated object (tank) handle its own attributes,
         * that is, let the tank draw itself
         */
        myTank.paint(graphics);
    }
    
    /**
     * keyboard listener class
     */
    class MyKeyListener extends KeyAdapter {
        boolean blLeft = false;
        boolean blUp = false;
        boolean blRight = false;
        boolean blDown = false;
        
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            //System.out.println(e.getKeyCode());
            //System.out.println("key pressed");
            //repaint();//repaint()默认调用 paint() 进行刷新页面
            //x += 1;
            //y += 1;
            
            //如果只是上下左右进行判断那么只能进行单个方向移动 不能进行合成方向进行移动.
            //所以进行对按键进行设置Boolean值 按下为true抬起的时候复位为初始值false
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    blLeft = true;
                    break;
                case KeyEvent.VK_UP:
                    blUp = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    blRight = true;
                    break;
                case KeyEvent.VK_DOWN:
                    blDown = true;
                    break;
                default:
                    break;
            }
            setManTankDirection();
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            System.out.println(e.getKeyCode());
            System.out.println("key released");
            // repaint();
            //x += 30;
            //y += 30;
            
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    blLeft = false;
                    break;
                case KeyEvent.VK_UP:
                    blUp = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    blRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                    blDown = false;
                    break;
                default:
                    break;
            }
            setManTankDirection();
        }
        
        private void setManTankDirection() {
            if (blLeft) myTank.setDirection(Direction.LEFT);
            if (blUp) myTank.setDirection(Direction.UP);
            if (blRight) myTank.setDirection(Direction.RIGHT);
            if (blDown) myTank.setDirection(Direction.DOWN);
        }
    }
}
