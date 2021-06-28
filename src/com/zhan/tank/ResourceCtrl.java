package com.zhan.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ResourceCtrl {
    public static BufferedImage tankL, tankU, tankR, tankD;
    public static BufferedImage BtankL, BtankU, BtankR, BtankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];
    static {// ReourceCtrl class 文件被load到内存的时候自动执行
        try {// class loader 负责把.class文件加载到jvm中
            tankU = ImageIO.read(ResourceCtrl.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
            tankL = ImageUtil.rotateImage(tankU, -90);
            tankR = ImageUtil.rotateImage(tankU, 90);
            tankD = ImageUtil.rotateImage(tankU, 180);

            BtankU = ImageIO.read(ResourceCtrl.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));
            BtankL = ImageUtil.rotateImage(BtankU, -90);
            BtankR = ImageUtil.rotateImage(BtankU, 90);
            BtankD = ImageUtil.rotateImage(BtankU, 180);

            bulletU = ImageIO.read(ResourceCtrl.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceCtrl.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
