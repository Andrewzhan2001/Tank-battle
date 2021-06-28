package test;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageTest {
    @Test
    public void test() {
        try {
            BufferedImage image = ImageIO.read(new File("C:/Users/zhan/Desktop/java/tanktest/src/images/bulletD.gif"));
            assertNotNull(image);
            //java的文件在需要的时候会被load到内存里，getclassloader就得到这个文件的loader（在class加载根目录src/maven,直接.class就是在当前文件夹)，让他在classpath下面继续找这个文件转换成scream给read
            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
