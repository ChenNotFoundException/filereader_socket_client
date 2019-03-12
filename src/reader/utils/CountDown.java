package reader.utils;

import sun.rmi.runtime.Log;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author chenchen
 * @date 2019/1/8 12:57
 * @Content:
 */
public class CountDown {
    private  static Timer timer = new Timer() ;

    public CountDown() {
        this(timer);
    }

    public CountDown(Timer timer) {
        this.timer = timer;
    }

    public static void stop() {
        timer.cancel();
        timer = new Timer();

    }
    public static void Count(int min, JLabel label, JButton button,JButton upload,JFrame frame) {
            //开始时间
            long start = System.currentTimeMillis();
            //结束时间
            final long end = start + min * 60 * 1000;
            //延迟0毫秒（即立即执行）开始，每隔1000毫秒执行一次
            timer.schedule(new TimerTask() {
                public void run() {
                    //System.out.println("倒计时中。");
                    long show = end-System.currentTimeMillis();
                  //  long hh = show/1000/60/60;
                    long mm = show/1000/60%60;
                    long ss = show/1000%60;
                    //System.out.println(mm+"分"+ss+"秒");
                    label.setText("请在"+mm+"分"+ss+"秒"+"内进行上传");
                }
            }, 0, 1000);
            //计时结束时候，停止全部timer计时计划任务
            timer.schedule(new TimerTask() {
                public void run() {
                    stop();
                    //System.out.println("结束！");
                    button.setEnabled(true);
                    upload.setEnabled(false);
                    label.setText("请重新获取配置"+"\n"+"并在2分钟内完成上传！");
                    JOptionPane.showMessageDialog(frame, "编辑超时！请注意编辑时间不得长于2分钟！", "提示消息",JOptionPane.WARNING_MESSAGE);

                }

            }, new Date(end));

    }
}
