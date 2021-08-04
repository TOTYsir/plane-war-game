package com.yuitech.plane;

import java.awt.*;

public class Explosion {
    double x, y;

    int count;

    static Image[] imgs = new Image[16];

    static {
        for (int i = 0; i < 16; i++) {
            imgs[i] = GameUtil.getImage("image/explosion/e" + (i + 1) + ".gif");
            imgs[i].getWidth(null);
            imgs[i].getHeight(null);
        }
    }

    public void drawMyself(Graphics g) {
        if (count < 16) {
            g.drawImage(imgs[count], (int) x, (int) y, null);
            count++;
        }
    }

    public Explosion() {
    }

    public Explosion(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
