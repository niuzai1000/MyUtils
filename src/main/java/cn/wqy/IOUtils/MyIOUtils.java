package cn.wqy.IOUtils;

import cn.wqy.UtilsAnnotation.UtilsAnnotation;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@UtilsAnnotation("IO Utils")
public class MyIOUtils {

    private MyIOUtils() {}

    private static void copyDocInit(File sourceFile , File targetFile) throws IOException {
        if (sourceFile == null || targetFile == null) throw new IOException("请正确输入文件类");
        if (!sourceFile.exists()) throw new FileNotFoundException("被复制的文件不存在");
        deleteFile(targetFile);
        makeFileExist(targetFile , sourceFile.isDirectory());
    }

    public static void copyDoc(File sourceFile , File targetFile) throws IOException {
        copyDocInit(sourceFile, targetFile);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            byte[] data = new byte[1024 * 512];
            int read_count;
            while((read_count = fis.read(data)) != -1){
                fos.write(data,0,read_count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close(fis , fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyTextDoc(File sourceFile, File targetFile , Charset targetCharset) throws IOException {
        copyDocInit(sourceFile, targetFile);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            isr = new InputStreamReader(fis , StandardCharsets.UTF_8);
            osw = new OutputStreamWriter(fos , targetCharset);
            char[] data = new char[1024 * 512];
            int read_count;
            while((read_count = isr.read(data)) != -1){
                osw.write(data,0,read_count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close(isr , osw);
                close(fis , fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void makeFileExist(File file , boolean isDirectory) throws IOException{
        if (file == null) throw new IOException("请正确输入文件类");
        if (file.exists()) return;
        if (!isDirectory) {
            if (!(file.getParentFile().exists() || file.getParentFile().mkdirs()))
                throw new IOException("无法创建新文件夹");
            if (!file.createNewFile())
                throw new IOException("无法创建新文件");
        }
        else {
            if (!file.mkdirs())
                throw new IOException("无法创建新文件夹");
        }
    }

    public static void copyFile(File sourceFolder, File targetFolder, boolean saveRootDirectory) throws IOException{
        if (sourceFolder == null || targetFolder == null) throw new IOException("请正确输入文件类");
        if (!sourceFolder.exists()) throw new IOException("请确保原文件存在");
        if (!sourceFolder.isFile() && saveRootDirectory) copyFile0(sourceFolder , new File(targetFolder.getAbsolutePath() + '\\' + sourceFolder.getName()));
        else copyFile0(sourceFolder, targetFolder);
    }

    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        copyFile(sourceFile, targetFile, false);
    }

    private static void copyFile0(File sourceFile, File targetFile) throws IOException {
        makeFileExist(targetFile, sourceFile.isDirectory());
        if (sourceFile.isFile()){
            copyDoc(sourceFile , targetFile);
            return;
        }
        File[] files = sourceFile.listFiles();
        if (files == null) throw new FileNotFoundException("文件夹内文件 " + sourceFile + " 不存在，或出现了其他问题");
        String oldAbsolutePath = sourceFile.getAbsolutePath();
        String newAbsolutePath = targetFile.getAbsolutePath();
        for (File subFile : files){
            copyFile0(subFile, new File(subFile.getAbsolutePath().replace(oldAbsolutePath , newAbsolutePath)));
        }
    }

    public static void deleteFile(File file, boolean saveRootDir) throws IOException {
        if (file == null) throw new IOException("请正确输入文件类");
        if (!file.exists()) return;
        deleteFile0(file);
        if (!file.isFile() && saveRootDir) makeFileExist(file, true);
    }

    public static void deleteFile(File file) throws IOException {
        deleteFile(file , false);
    }

    private static void deleteFile0(File file) throws IOException {
        if (file.isFile()) {
            if (!file.delete()) throw new IOException("不能删除" + file + "文件");
        }
        File[] subFiles = file.listFiles();
        if (subFiles == null) return;
        for (File subFile : subFiles){
            deleteFile0(subFile);
        }
        if (!file.delete()) throw new IOException("文件无法删除");
    }

    public static void close(InputStream is , OutputStream os) throws IOException {
        if (is != null) is.close();
        if (os != null){
            os.flush();
            os.close();
        }
    }

    public static void close(InputStream is) throws IOException {
        close(is , null);
    }

    public static void close(OutputStream os) throws IOException {
        close(null , os);
    }

    public static void close(Reader reader , Writer writer) throws IOException {
        if (reader != null) reader.close();
        if (writer != null){
            writer.flush();
            writer.close();
        }
    }

    public static void close(Reader reader) throws IOException {
        close(reader , null);
    }

    public static void close(Writer writer) throws IOException {
        close(null , writer);
    }

}
