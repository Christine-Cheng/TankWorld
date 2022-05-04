package com.tankwold;

import com.tankwold.enums.Direction;
import com.tankwold.enums.Group;

/**
 * @author Happy
 * @create 2022/4/20-0:55
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();
        
        //初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            frame.enemyTankList.add(new Tank(50 + i * 80, 200, Direction.DOWN, Group.BAD, frame));
        }
        while (true) {
            Thread.sleep(50);
            frame.repaint(); //You can use threads to refresh the window
        }
    }
}
