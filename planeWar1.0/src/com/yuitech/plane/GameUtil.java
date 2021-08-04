package com.yuitech.plane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameUtil {

    //构造器私有
    private GameUtil() {
    }

    public static Image getImage(String path) {
        BufferedImage img = null;
        URL u = GameUtil.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }


    public static void main(String[] args) {
        Image img = GameUtil.getImage("image/plane.png");
        System.out.println(img);
    }
}
