package reader;


import java.io.FileWriter;
import java.io.IOException;

/**
 * @author chenchen
 * @date 2019/1/5 20:30
 * @Content:
 */
public class saver {
   // private static String savefile = "D:\\222.txt";
    public static void save(String s,String Savefile) {
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(Savefile);
            fwriter.write(s);
            //System.out.println(s);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
//                http.send();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
