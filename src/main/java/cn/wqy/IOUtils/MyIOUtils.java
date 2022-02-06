package cn.wqy.IOUtils;

import cn.wqy.UtilsAnnotation.UtilsAnnotation;

import java.io.*;

@UtilsAnnotation("IO Utils")
public class MyIOUtils {

    private MyIOUtils() {}

    public static void copyFile(File oldFile , File newFile) throws FileNotFoundException {
        if (!oldFile.exists()) throw new FileNotFoundException("被复制的文件不存在");
        if (!newFile.exists()) {
            try {
                if (!newFile.createNewFile()) throw new FileNotFoundException("无法在新路径下创建文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(oldFile);
            fos = new FileOutputStream(newFile);
            byte[] data = new byte[1024 * 512 * 16];
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

    public static void copyFolder(File oldFolder , File newFolder) throws IOException {
        copyFolder(oldFolder , newFolder , false);
    }

    public static void copyFolder(File oldFolder , File newFolder , boolean onlyRoot) throws IOException {
        File[] files = oldFolder.listFiles();
        if (files == null) throw new FileNotFoundException("文件夹 " + oldFolder + " 不存在，或出现了其他问题");
        String oldAbsolutePath = oldFolder.getAbsolutePath();
        String newAbsolutePath = newFolder.getAbsolutePath();
        for (File oldFile : files){
            File newFile = new File(oldFile.getAbsolutePath().replace(oldAbsolutePath , newAbsolutePath));
            if (!newFile.exists()){
                boolean succeed;
                if (oldFile.isFile()) {
                    if (!newFile.getParentFile().exists()) succeed = newFile.getParentFile().mkdirs();
                    else succeed = true;
                    if (!succeed) throw new IOException("无法创建新文件夹");
                    succeed = newFile.createNewFile();
                    if (!succeed) throw new IOException("无法创建新文件");
                }
                else if (oldFile.isDirectory()) {
                    succeed = newFile.mkdirs();
                    if (!succeed) throw new IOException("无法创建新文件夹");
                }
            }
            if (oldFile.isFile()) copyFile(oldFile , newFile);
            else if (!onlyRoot && oldFile.isDirectory()) {
                copyFolder(oldFile, newFile);
            }
        }
    }

    public static void deleteFolder(File folder) throws IOException {
        deleteFolder(folder , false);
    }

    public static void deleteFolder(File folder , boolean onlyRoot) throws IOException {
        File[] files = folder.listFiles();
        if (files == null) throw new FileNotFoundException("文件夹 " + folder + " 不存在，或出现了其他问题");
        for (File file : files){
            if (file.isFile()) {
                boolean succeed = file.delete();
                if (!succeed) throw new IOException("文件无法删除");
            }
            else if (!onlyRoot && file.isDirectory()) {
                deleteFolder(file , false);
            }
        }
        boolean succeed = folder.delete();
        if (!succeed) throw new IOException("文件夹无法删除");
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
}
