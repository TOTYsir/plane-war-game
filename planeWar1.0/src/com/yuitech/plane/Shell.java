package com.yuitech.plane;

import java.awt.*;

public class Shell extends GameObject {

    double degree;  //角度，炮弹沿着指定的角度飞行

    public Shell() {
        x = 200;
        y = 200;

        degree = Math.random() * Math.PI * 2;

        width = 6;
        height = 6;

        speed = 3;
    }

    @Override
    public void drawMyself(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.orange);

        g.fillOval((int) x, (int) y, width, height);

        g.setColor(c);

        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);

        if (y > Constant.GAME_HEIGHT - this.height || y < 35) {
            degree = -degree;
        }
        if (x > Constant.GAME_WIDTH - this.width || x < 0) {
            degree = Math.PI - degree;
        }
    }
}
