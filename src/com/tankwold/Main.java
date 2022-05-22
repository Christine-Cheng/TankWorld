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
    
        //从配置文件中获取要初始化坦克的数量
        int initTankCount = Integer.parseInt((String) PropertyManager.getValue("initTankCount"));
    
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            frame.enemyTankList.add(new Tank(50 + i * 80, 200, Direction.DOWN, Group.BAD, frame));
        }
    
        //初始化音乐
        new Thread(() -> new Audio("audio/war1.wav").loop()).start();
    
        while (true) {
            Thread.sleep(50);
            frame.repaint(); //You can use threads to refresh the window
        }
    }
}
