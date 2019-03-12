import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.GroupLayout;

import reader.readFiles;
import reader.utils.CountDown;
import reader.utils.MyClient;
import reader.utils.ipConfig;
import reader.saver;
/*
 * Created by JFormDesigner on Sat Jan 05 19:48:40 CST 2019
 */



/**
 * @author cc
 */
public class reader extends JFrame {
    private String oritext;
    private static String PATH;
    private int selected ;
    static {
        File file = new File("");
        PATH = file.getAbsolutePath();
    }
    private static final String txt_1 = PATH+"/dirList.txt";
    private static final String txt_2 = PATH+"/frameInf.txt";

    private void MyInit() {
        ipAddress.setEnabled(true);
        button2.setEnabled(true);
        label3.setVisible(true);
        label4.setVisible(false);

    }
    public reader() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label3.setVisible(false);
        button3.setEnabled(false);
        textField1.setVisible(false);
        label2.setVisible(false);
        subscript.setEnabled(false);
    }

    private void button1ActionPerformed(ActionEvent e) {
        button5.setEnabled(false);
        selected=1;
        MyInit();
        if (readFiles.show(textArea1,9192,txt_1)) {
            CountDown.Count(2,label5,button1,button3,this);
            subscript.setEnabled(true);
            label5.setVisible(true);
            button3.setEnabled(true);
            oritext = textArea1.getText();
            button1.setEnabled(false);
        }else
            JOptionPane.showMessageDialog(this, "连接超时！请检查服务器9192端口是否打开或被占用", "提示消息",JOptionPane.WARNING_MESSAGE);

    }

    private void button5ActionPerformed(ActionEvent e) {
        label2.setVisible(true);
        textField1.setVisible(true);
        textArea1.setText("");
        button1.setEnabled(false);
        selected =2;
        MyInit();
        if (new readFiles().show(textArea1, 9193, txt_2)) {
            CountDown.Count(2, label5, button5,button3,this);
            subscript.setEnabled(true);
            label5.setVisible(true);
            button3.setEnabled(true);
            oritext = textArea1.getText();
            button5.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "连接超时！请检查服务器9193端口是否打开或被占用", "提示消息", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void button2ActionPerformed(ActionEvent e) {
        String[] subscript2 = subscript.getText().split("\\s+");
        String ip = "";
        String ip1 = ipAddress.getText()+"--";
        String psw = textField1.getText();
        String ip2 = "rtsp:/admin:"+psw +"@"+ipAddress.getText() +"/--../save/--";
        for (String s :
                subscript2) {
            ip1 += (s+",");
            ip2 += (s+",");

        }
        if (selected == 1) {
            ip = ip1.substring(0,ip1.length()-1);
        } else if (selected == 2) {
            ip = ip2.substring(0,ip2.length()-1);
        }
        //System.out.println(ip);
        //String pwd = password.getText();
        if (ip.length()!=0 && ipConfig.isIpv4(ipAddress.getText())){
            textArea1.setText(textArea1.getText()+"\r\n"+ip);
        }else
            JOptionPane.showMessageDialog(this, "IP/密码 为空 或 IP格式不正确！！", "提示消息",JOptionPane.WARNING_MESSAGE);

    }

    private void button3ActionPerformed(ActionEvent e) {
        String ip =null;
        int port = 9191;
        if (selected == 1) {
            ip = txt_1;
            port = 9191;
        } else if (selected == 2) {
            ip = txt_2;
            port = 9194;
        }
        String newTXT = textArea1.getText();
        oritext = newTXT;
        if (newTXT.length() != 0) {
            saver.save(newTXT,ip);
            JOptionPane.showMessageDialog(this, "保存成功！上传中。。", "提示消息",JOptionPane.INFORMATION_MESSAGE);
            if (MyClient.UploadFile(port,ip)) {
                JOptionPane.showMessageDialog(this, "上传成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
                label3.setVisible(false);
                ipAddress.setEnabled(false);
                button2.setEnabled(false);
                label4.setVisible(true);
                button3.setEnabled(false);
                button1.setEnabled(true);
                button5.setEnabled(true);
                label5.setVisible(false);
                textField1.setVisible(false);
                textArea1.setText(" ");
                CountDown.stop();
                saver.save(" ",ip);
                label2.setVisible(false);
                subscript.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "上传失败！请检查服务是否开起", "提示消息", JOptionPane.INFORMATION_MESSAGE);
            }
        }else
            JOptionPane.showMessageDialog(this, "配置文件内容为空！！", "提示消息",JOptionPane.INFORMATION_MESSAGE);


    }

    private void button4ActionPerformed(ActionEvent e) {
        textArea1.setText(oritext);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        button1 = new JButton();
        ipAddress = new JTextField();
        button2 = new JButton();
        label1 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        button5 = new JButton();
        label5 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        label6 = new JLabel();
        subscript = new JTextField();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextPane();
        button3 = new JButton();
        button4 = new JButton();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();

        //======== panel1 ========
        {

            //---- button1 ----
            button1.setText("\u8bfb\u53d6FTP");
            button1.addActionListener(e -> button1ActionPerformed(e));

            //---- ipAddress ----
            ipAddress.setText("192.168.1.1");
            ipAddress.setEnabled(false);

            //---- button2 ----
            button2.setText("\u6dfb\u52a0");
            button2.setEnabled(false);
            button2.addActionListener(e -> button2ActionPerformed(e));

            //---- label1 ----
            label1.setText("IP:");

            //---- label3 ----
            label3.setText("\u8bf7\u5c3d\u5feb\u6dfb\u52a0\u5e76\u4fdd\u5b58\u81f3\u670d\u52a1\u5668");
            label3.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 16));
            label3.setForeground(Color.red);

            //---- label4 ----
            label4.setText("\u8bf7\u5148\u8bfb\u53d6\u914d\u7f6e\u6587\u4ef6");
            label4.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 12));

            //---- button5 ----
            button5.setText("\u8bfb\u53d6RTSP\u89c6\u9891\u6d41");
            button5.addActionListener(e -> button5ActionPerformed(e));

            //---- label5 ----
            label5.setText("\u5012\u8ba1\u65f6\uff1a");

            //---- label2 ----
            label2.setText("\u5bc6\u7801\uff1a");

            //---- label6 ----
            label6.setText("\u8f93\u5165\u56fe\u7247\u5750\u6807(\u4ee5\u7a7a\u683c\u5206\u5272)");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subscript, GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(button5, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                        .addComponent(button1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                            .addComponent(ipAddress, GroupLayout.Alignment.TRAILING)
                                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(label6))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button5, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label4)
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(ipAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2))
                        .addGap(18, 18, 18)
                        .addComponent(label6)
                        .addGap(3, 3, 3)
                        .addComponent(subscript, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
            );
        }

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }

        //---- button3 ----
        button3.setText("\u4fdd\u5b58\u914d\u7f6e\u6587\u4ef6");
        button3.addActionListener(e -> button3ActionPerformed(e));

        //---- button4 ----
        button4.setText("\u6062\u590d\u9ed8\u8ba4");
        button4.addActionListener(e -> button4ActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 773, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(button4, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                            .addGap(95, 95, 95))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button3, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(button4, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                    .addContainerGap())
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JButton button1;
    private JTextField ipAddress;
    private JButton button2;
    private JLabel label1;
    private JLabel label3;
    private JLabel label4;
    private JButton button5;
    private JLabel label5;
    private JLabel label2;
    private JTextField textField1;
    private JLabel label6;
    private JTextField subscript;
    private JScrollPane scrollPane1;
    private JTextPane textArea1;
    private JButton button3;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
