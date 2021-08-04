package com.yuitech.plane;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyGameFrame extends Frame {

    Image planeImg = GameUtil.getImage("image/plane.png");
    Image bg = GameUtil.getImage("image/bg.jpg");

    Plane p1 = new Plane(planeImg, 100, 100, 6);

    Shell[] shells = new Shell[5];

    Explosion explosion;

    Date start = new Date();
    Date end;
    long period = 0;

    @Override
    public void paint(Graphics g) {     //把g当作一支画笔

        g.drawImage(bg, 0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT, null);

        drawTime(g);

        p1.drawMyself(g);

        for (Shell shell : shells) {
            shell.drawMyself(g);

            boolean crash = shell.getRec().intersects(p1.getRec());
            if (crash) {
//                System.out.println("CRASH!!!");
                p1.hp = false;

                if (explosion == null) {
                    explosion = new Explosion(p1.x, p1.y);
                }
                explosion.drawMyself(g);
            }
        }
    }


    public void drawTime(Graphics g) {
        Color c = g.getColor();
        Font f = getFont();

        g.setColor(Color.white);
        if (p1.hp) {
            period = (System.currentTimeMillis() - start.getTime()) / 1000;
            g.drawString("TIME: " + period, 30, 50);
        } else {
            if (end == null) {
                end = new Date();
                period = (end.getTime() - start.getTime()) / 1000;

            }

            g.setFont(new Font("Consolas", Font.BOLD, 30));
            g.drawString("RESULT: " + period, 170, 220);
        }

        g.setColor(c);
        g.setFont(f);

    }

    //初始化窗口
    public void launchFrame() {
        this.setTitle("Plane War");
        setVisible(true);

        setSize(500, 500);

        setLocation(400, 400);

        //增加关闭窗口的动作
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintThread().start();  //启动重画窗口的线程

        this.addKeyListener(new keyMonitor());  //启动键盘监听

        for (int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }
    }


    /**
     * 定义了一个重置窗口的线程类
     * 定义成内部类是为了方便直接使用窗口类的相关方法
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();      //内部类可以直接使用外部类的成员

                try {
                    Thread.sleep(10);           //1s画20次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class keyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
//            System.out.println("按下： " + e.getKeyCode());
            p1.addDirection(e);
        }

        public void keyReleased(KeyEvent e) {
//            System.out.println("松开： " + e.getKeyCode());
            p1.minusDirection(e);
        }

    }


    private Image offScreenImage = null;

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        }

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        MyGameFrame gameFrame = new MyGameFrame();
        gameFrame.launchFrame();
    }
}


