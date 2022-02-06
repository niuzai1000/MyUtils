package cn.wqy.LogUtils;
import cn.wqy.UtilsAnnotation.UtilsAnnotation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Deprecated
@UtilsAnnotation("Log Utils")
public class MyLogger {

    private MyLogger(){}

    public static void log(String log_name, String message) {
        ResourceBundle rb =ResourceBundle.getBundle("LogUtils.Log_directory");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String date = sdf.format(new Date());
        FileOutputStream fos = null;
        log_name = rb.getString("directory") + "\\" +  log_name + ".txt";
        try {
            fos = new FileOutputStream(log_name, true);
            fos.write(date.getBytes());
            fos.write('\t');
            fos.write(message.getBytes());
            fos.write('\n');
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String first_Log(String log_name, String message){
        ResourceBundle rb =ResourceBundle.getBundle("LogUtils.Log_directory");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String log_true_name = log_name + "[" + sdf.format(new Date()) + "]";
        log_name = rb.getString("directory") + "\\" + log_true_name +".txt";
        File f = new File(log_name);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log(log_true_name, message);
        return log_true_name;
    }
}
