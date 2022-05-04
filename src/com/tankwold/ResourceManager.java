package com.tankwold;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 对图片资源进行同意管理
 * @author Happy
 * @create 2022/5/3-23:00
 **/
public class ResourceManager {
    public static BufferedImage tankLeft, tankUp, tankRight, tankDown;
    
    
    static {
        try {
            tankLeft = ImageIO.read(new File("src/images/tankL.gif"));
            tankLeft = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankUp = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankRight = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankDown = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
