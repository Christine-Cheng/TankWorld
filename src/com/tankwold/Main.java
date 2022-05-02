package com.tankwold;

/**
 * @author Happy
 * @create 2022/4/20-0:55
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame=new TankFrame();
        while (true) {
            Thread.sleep(50);
            frame.repaint(); //You can use threads to refresh the window
        }
    }
}
