package reader;

import reader.utils.MyClient;


import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import static java.lang.System.out;

/**
 * @author chenchen
 * @date 2019/1/5 19:58
 * @Content:
 */
public class readFiles {
   // private static String savefile = "D:\\222.txt";
    public static boolean show(JTextPane pane,int port,String Savefile) {
//        try {
//            http.receive();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Boolean finish = MyClient.getFile(port,Savefile);
        if (finish) {
            File f=new File(Savefile);
            File fileParent = f.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(f.exists())
                try{

                    FileInputStream in=new FileInputStream(f);

                    byte[] buf =new byte[20480];

                    int len=in.read(buf);

                    String  s =new String(buf,0,len);

                   // out.println(s); // 这里用out就行了
                    Document docs = pane.getDocument();//获得文本对象
                    try {
                        docs.insertString(docs.getLength(), s + '\n',pane.getCharacterAttributes() );//对文本进行追加
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    } pane.setText(s);

                }catch(Exception e){

                    out.println(e.getMessage());

                }
        }
        return finish;
    }

}
