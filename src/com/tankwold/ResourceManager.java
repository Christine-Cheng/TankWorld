package com.tankwold;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 对图片资源进行同意管理
 *
 * @author Happy
 * @create 2022/5/3-23:00
 **/
public class ResourceManager {
    public static BufferedImage tankL, tankU, tankR, tankD;//tank Left Up Right Down
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;//bullet Left Up Right Down
    
    
    static {
        try {
            tankL = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            
            bulletL = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
