package com.studytankworld;

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
    
    int x = 200;
    int y = 200;
    
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
        System.out.println("哈哈哈");
        //The first two parameters are the starting point coordinates
        //The last two parameters are width and height
        graphics.fillRect(x, y, 50, 50);//改变起点让他动起来
        graphics.setColor(Color.red);
        graphics.fill3DRect(400, 400, 50, 30, false);
        x += 1;
        y += 1;
    }
    
    /**
     * keyboard listener class
     */
    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            System.out.println(e.getKeyCode());
            System.out.println("key pressed");
            //repaint();//repaint()默认调用 paint() 进行刷新页面
            //x += 1;
            //y += 1;
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            System.out.println(e.getKeyCode());
            System.out.println("key released");
            // repaint();
            //x += 30;
            //y += 30;
        }
    }
}
