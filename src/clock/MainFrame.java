package clock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MainFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 3849542409431537937L;
    private Thread t;
    // 放置时间的标签
    private JLabel j = new JLabel();
    // 放置时间标签的面板，让标签处于中间
    private JPanel p = new JPanel();

    // 设置主界面
    public MainFrame() {
        setTitle("时钟");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 窗体设为不可最大化
        setResizable(false);
        // 窗体居中
        Toolkit toolkit = getToolkit();
        Dimension dm = toolkit.getScreenSize();
        setLocation((dm.width - getWidth()) / 2, (dm.height - getHeight()) / 2);
        j.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        // 创建线程，一直获取时间
        t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    String time = String.format("%tT", Calendar.getInstance());
                    j.setText(time);
                    try {
                        Thread.sleep(500);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        t.start();
        // 为容器添加控件
        p.add(j);
        getContentPane().add(p, BorderLayout.NORTH);
        // 添加时钟指针面板
        getContentPane().add(new ClockFrame());
    }

    public static void main(String[] args) {
        // 调用系统UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 保证组件状态的可确定性
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });

    }

}
