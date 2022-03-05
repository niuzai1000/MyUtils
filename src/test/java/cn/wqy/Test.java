package cn.wqy;

import cn.wqy.IOUtils.MyIOUtils;
import cn.wqy.StatisticsUtils.MyStatisticUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws IOException {
//        for (int i = 0; i < 10000; i++) {
//            int[] array = new int[6];
//            for (int j = 0; j < 6; j++) {
//                array[j] = new Random().nextInt(6);
//            }
//            System.out.println(Arrays.toString(array));
//            System.out.println(Arrays.toString(MyStatisticUtils.getMode(array)));
//        }
        MyIOUtils.copyFile(new File("E:\\Music") , new File("E:\\TestFolder") , true);
        MyIOUtils.deleteFile(new File("E:\\TestFolder\\Music") , false);
    }
}
