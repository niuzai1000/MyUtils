package cn.wqy;

import cn.wqy.StatisticsUtils.MyStatisticUtils;

public class Test {
    public static void main(String[] args) {
        int[] arrays = new int[]{10 , 20 , 30,  40};
        System.out.println(MyStatisticUtils.getAverage(arrays));
    }
}
