package com.tankwold;

import com.tankwold.enums.Direction;
import com.tankwold.enums.Group;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Happy
 * @create 2022/5/1-0:12
 **/
public class TankFrame extends Frame {
    //set tank's default direction and starting coordinates
    Tank myTank = new Tank(200, 400, Direction.UP, Group.GOOD, this);//Encapsulate the attributes of the tank into the tank class
    //Bullet bullet = new Bullet(300, 300, Direction.DOWN);//单个子弹不够用
    List<Bullet> bulletList = new ArrayList<>();//set bullet's container
    List<Tank> enemyTankList = new ArrayList<>();
    List<Exploded> explodedList = new ArrayList<>();
    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;//the game screen size
    
    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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
     * 利用双缓冲技术解决屏幕闪烁的问题
     * 1.在内存中新建一个Image,然后新建画笔Graphics,设置颜色,然后画出画布,设置颜色,然后把画笔给下一步
     * 2.显示
     * <p>
     * 用双缓冲解决闪烁问题（不重要）
     * 调用repaint()的时候会先调用 update()
     * 截获update
     * 首先把该画出来的东西（坦克， 子弹）先画在内存的图片中，图片大小和游戏画面一致
     * 把内存中图片一次性画到屏幕上（内存的内容复制到显存）
     */
    Image offScreenImage = null;
    
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }
    
    
    /**
     * 用forEach的bug
     * for (Bullet bullet : bulletList) {
     * //遍历每一个子弹
     * bullet.paint(graphics);
     * }
     * Bullet.java中删除子弹
     * if (!live) {
     * tankFrame.bulletList.remove(this);//子弹死了就移除
     * }
     * forEach用的的是集合内部的Iterator,在该迭代器迭代的时候是不允许其他方式(类)进行添加或者删除,只能在此处进行删除修改.
     * 若要在Bullet.java中对失效的的子弹进行删除,那么不能够用forEach
     * <p>
     * 所以用以下的方式,或者Iterator方式进行迭代
     * for (int i = 0; i < bulletList.size(); i++) {
     * bulletList.get(i).paint(graphics);
     * }
     * Bullet.java中删除子弹
     * if (!live) {
     * tankFrame.bulletList.remove(this);//子弹死了就移除
     * }
     */
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
        Color color = graphics.getColor();
        graphics.setColor(Color.white);
        //当前的子弹在飞出页面后依旧在内存中存在,这样就存在内存满溢出泄露
        //Java中的内存泄露和容器有关系,如果容器中的引用用完了不清除那么容易出现内存泄露的问题
        graphics.drawString("子弹的数目:" + bulletList.size(), 10, 60);
        graphics.drawString("敌军的数目:" + enemyTankList.size(), 10, 80);
        graphics.drawString("爆炸的数量:" + explodedList.size(), 10, 100);
        graphics.setColor(color);
    
        /**
         * In order to not destroy the encapsulation characteristics,
         * let the encapsulated object (tank) handle its own attributes,
         * that is, let the tank draw itself
         */
        myTank.paint(graphics);
    
        //bullet.paint(graphics);//单个子弹不够用
        //for (int i = 0; i < bulletList.size(); i++) {
        //    //遍历每一个子弹
        //    bulletList.get(i).paint(graphics);
        //}
    
        //迭代删除失效的子弹
        for (Iterator<Bullet> bulletIterator = bulletList.iterator(); bulletIterator.hasNext(); ) {
            Bullet bullet = bulletIterator.next();
            if (!bullet.live()) {
                bulletIterator.remove();//Iterator可以进行同步删除
            }
            bullet.paint(graphics);
        }
    
        //paint enemy tanks
        for (int i = 0; i < enemyTankList.size(); i++) {
            enemyTankList.get(i).paint(graphics);
        }
    
        //exploded.paint(graphics);
        //paint exploded
        for (int i = 0; i < explodedList.size(); i++) {
            explodedList.get(i).paint(graphics);
        }
    
        //test bullet collides with tank
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < enemyTankList.size(); j++) {
                Bullet bullet = bulletList.get(i);
                Tank tank = enemyTankList.get(j);
                bullet.collideWithTank(tank);
            }
        }
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
                case KeyEvent.VK_CONTROL:
                    myTank.fire();//按control键发射子弹
                default:
                    break;
            }
            setManTankDirection();
        }
        
        private void setManTankDirection() {
            if (!blLeft && !blUp && !blRight && !blDown) {//stop
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);//move
                if (blLeft) myTank.setDirection(Direction.LEFT);
                if (blUp) myTank.setDirection(Direction.UP);
                if (blRight) myTank.setDirection(Direction.RIGHT);
                if (blDown) myTank.setDirection(Direction.DOWN);
            }
            
        }
    }
}
