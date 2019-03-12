package reader.utils;

import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import reader.saver;
/**
 * @author chenchen
 * @date 2019/1/6 15:06
 * @Content:
 */
public class MyClient {
    private static int val = 20480;
    private static String iphost = "192.168.0.121";

    public static boolean UploadFile(int port,String localfile) {
        try {
            Socket socket = new Socket(iphost,port);
            //准备两个流，文件输入流，socket输入流
            //本地的输入流
            FileInputStream fis = new FileInputStream(localfile);
            //把本地的输入流发出去
            OutputStream ous = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(ous);

            System.out.println("开始读发送文件……！");
            //先获取文件大小
            File file = new File(localfile);
            long fileSize = file.length();
            if (fileSize == 0) {
                return false;
            }
            byte[] bytes = new byte[val];

            //算出即将发送字节数组的字数
            long times = fileSize/val+1;
            //算出最后一组字节数组的有效字节数
            int lastBytes = (int)fileSize%20480;
            //1.发送字节数组长度
            oos.writeInt(val);
            //2.发送次数
            oos.writeLong(times);
            oos.flush();
            //3.最后一次字节个数
            oos.writeInt(lastBytes);
            oos.flush();

            //读取字节数组长度的字节，返回读取字节数中的数据个数
            int value = fis.read(bytes);
            while(value != -1){
                //偏移字节数读取
                oos.write(bytes,0,value);
                oos.flush();
                value = fis.read(bytes);
            }
            System.out.println("文件发送完毕！");
            //Thread.sleep(2000);
            //关闭流
            fis.close();
            ous.close();
            socket.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getFile(int port,String address) {

            BufferedInputStream bis = null;
            Socket socket = new Socket();
            int len=0;
            try {
                try {
                    // 通过IP地址和端口号创建一个Socket对象
                    SocketAddress remoteAddr = new InetSocketAddress(iphost,port);
                    // socket = new Socket(iphost, port);
                    socket.connect(remoteAddr,6*1000);
                    // 客户端读取文件
                    bis = new BufferedInputStream(socket.getInputStream());
                    // 每次读1024个字节
                    byte[] b = new byte[20480];
                    //当读取的字节不为空 循环打印下载的内容
                    while ((len = bis.read(b)) != -1) {
                        //System.out.println(new String(b, 0, len));
                        saver.save(new String(b, 0, len),address);
                    }
                    return true;
                } catch (Exception e) {
                    System.out.println("连接超时");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                //关闭客户端的输入流对象 和 Socket对象
                try {
                    if(bis!=null) bis.close();
                    if(socket!=null) socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
