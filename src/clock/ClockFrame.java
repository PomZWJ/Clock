package clock;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ClockFrame extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 3622363857456545360L;
    private int BigAngle = 30;
    private int SmallAngle = 6;
    private int BigLong = 270;
    private int SmallLong = 290;
    private Line2D.Double hourline;
    private Line2D.Double minuteline;
    private Line2D.Double secondline;
    private int hour;
    private int minute;
    private int second;

    public ClockFrame() {
        hourline = new Line2D.Double(350, 300, 350, 300);
        minuteline = new Line2D.Double(350, 300, 350, 300);
        secondline = new Line2D.Double(350, 300, 350, 300);
        // 写Swing.Timer匿名内部类，根据每次获得的时间设置时分秒指针的位置，再重写paintComponent方法
        Timer t = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calendar = new GregorianCalendar();
                hour = calendar.get(Calendar.HOUR);
                minute = calendar.get(Calendar.MINUTE);
                second = calendar.get(Calendar.SECOND);
                hourline.x2 = 350 + Math.cos(hour * (Math.PI / 6) - Math.PI / 2) * 200;
                hourline.y2 = 300 + Math.sin(hour * (Math.PI / 6) - Math.PI / 2) * 200;
                minuteline.x2 = 350 + Math.cos(minute * (Math.PI / 30) - Math.PI / 2) * 230;
                minuteline.y2 = 300 + Math.sin(minute * (Math.PI / 30) - Math.PI / 2) * 230;
                secondline.x2 = 350 + Math.cos(second * (Math.PI / 30) - Math.PI / 2) * 280;
                secondline.y2 = 300 + Math.sin(second * (Math.PI / 30) - Math.PI / 2) * 280;
                repaint();
            }
        });
        t.start();

    }

    // 重写paintComponent方法
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // 画圆
        Arc2D A = new Arc2D.Double(50, 0, 600, 600, 0, 360, Arc2D.OPEN);
        g2.draw(A);
        // 画大刻度
        for (int i = 0; i < 12; i++) {
            Line2D L1 = new Line2D.Float((float) getX1(i, BigAngle, BigLong), (float) getY1(i, BigAngle, BigLong),
                    (float) getX2(i, BigAngle, BigLong), (float) getY2(i, BigAngle, BigLong));
            g2.draw(L1);
            // 画小刻度
            for (int j = 0; j < 4; j++) {

                Line2D L2 = new Line2D.Float((float) getX1(j + 1 + i * 5, SmallAngle, SmallLong),
                        (float) getY1(j + 1 + i * 5, SmallAngle, SmallLong),
                        (float) getX2(j + 1 + i * 5, SmallAngle, SmallLong),
                        (float) getY2(j + 1 + i * 5, SmallAngle, SmallLong));
                g2.draw(L2);
            }
        }
        // 显示时钟上小时的数字
        g2.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        g2.drawString("12", 325, 60);
        g2.drawString("3", 595, 312);
        g2.drawString("6", 340, 565);
        g2.drawString("9", 83, 310);
        g2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        g2.drawString("1", 470, 80);
        g2.drawString("2", 565, 175);
        g2.drawString("4", 567, 435);
        g2.drawString("5", 475, 525);
        g2.drawString("7", 215, 525);
        g2.drawString("8", 125, 435);
        g2.drawString("10", 120, 180);
        g2.drawString("11", 210, 90);
        // 画时分秒
        g2.draw(hourline);
        g2.draw(minuteline);
        g2.draw(secondline);

    }

    // 获得X1坐标
    public double getX1(int i, int Angle, int Long) {
        double x1 = 0;
        if (i >= 0 && i <= 3) {
            x1 = 350 + Math.cos(Math.toRadians(Angle * i)) * Long;
        } else if (i >= 4 && i <= 6) {
            x1 = 350 - Math.sin(Math.toRadians(Angle * i - 90)) * Long;
        } else if (i >= 7 && i <= 9) {
            x1 = 350 - Math.cos(Math.toRadians(Angle * i - 180)) * Long;
        } else {
            x1 = 350 + Math.sin(Math.toRadians(Angle * i - 270)) * Long;
        }
        return x1;
    }

    // 获得Y1坐标
    public double getY1(int i, int Angle, int Long) {
        double y1 = 0;
        if (i >= 0 && i <= 3) {
            y1 = 300 - Math.sin(Math.toRadians(Angle * i)) * Long;
        } else if (i >= 4 && i <= 6) {
            y1 = 300 - Math.cos(Math.toRadians(Angle * i - 90)) * Long;
        } else if (i >= 7 && i <= 9) {
            y1 = 300 + Math.sin(Math.toRadians(Angle * i - 180)) * Long;
        } else {
            y1 = 300 + Math.cos(Math.toRadians(Angle * i - 270)) * Long;
        }
        return y1;
    }

    // 获得X2坐标
    public double getX2(int i, int Angle, int Long) {
        double x2 = 0;
        if (i >= 0 && i <= 3) {
            x2 = 350 + Math.cos(Math.toRadians(Angle * i)) * 300;
        } else if (i >= 4 && i <= 6) {
            x2 = 350 - Math.sin(Math.toRadians(Angle * i - 90)) * 300;
        } else if (i >= 7 && i <= 9) {
            x2 = 350 - Math.cos(Math.toRadians(Angle * i - 180)) * 300;
        } else {
            x2 = 350 + Math.sin(Math.toRadians(Angle * i - 270)) * 300;
        }
        return x2;
    }

    // 获得Y2坐标
    public double getY2(int i, int Angle, int Long) {
        double y2 = 0;
        if (i >= 0 && i <= 3) {
            y2 = 300 - Math.sin(Math.toRadians(Angle * i)) * 300;
        } else if (i >= 4 && i <= 6) {
            y2 = 300 - Math.cos(Math.toRadians(Angle * i - 90)) * 300;
        } else if (i >= 7 && i <= 9) {
            y2 = 300 + Math.sin(Math.toRadians(Angle * i - 180)) * 300;
        } else {
            y2 = 300 + Math.cos(Math.toRadians(Angle * i - 270)) * 300;
        }
        return y2;
    }
}
