package cn.wqy.StatisticsUtils;

import cn.wqy.UtilsAnnotation.UtilsAnnotation;

import java.util.Arrays;

@UtilsAnnotation("Statistics Utils")
public class MyStatisticUtils {

    private MyStatisticUtils(){}

    public static double getAverage(int[] numArray){
        Arrays.sort(numArray);
        int sum = 0;
        for (int num : numArray){
            sum += num;
        }
        return (double) sum / numArray.length;
    }

    public static double getAverage(long[] numArray){
        Arrays.sort(numArray);
        long sum = 0;
        for (long num : numArray){
            sum += num;
        }
        return (double) sum / numArray.length;
    }

    public static double getAverage(double[] numArray){
        Arrays.sort(numArray);
        double sum = 0;
        for (double num : numArray){
            sum += num;
        }
        return sum / numArray.length;
    }

    public static double getMedian(int[] numArray){
        Arrays.sort(numArray);
        if (numArray.length % 2 == 0){
            return (double) (numArray[numArray.length/2] + numArray[numArray.length/2 + 1]) / 2;
        }else {
            return numArray[(numArray.length + 1) / 2];
        }
    }

    public static double getMedian(long[] numArray){
        Arrays.sort(numArray);
        if (numArray.length % 2 == 0){
            return (double) (numArray[numArray.length/2] + numArray[numArray.length/2 + 1]) / (double) 2;
        }else {
            return numArray[(numArray.length + 1) / 2];
        }
    }

    public static double getMedian(double[] numArray){
        Arrays.sort(numArray);
        if (numArray.length % 2 == 0){
            return (numArray[numArray.length/2] + numArray[numArray.length/2 + 1]) / (double) 2;
        }else {
            return numArray[(numArray.length + 1) / 2];
        }
    }

    public static int[] getMode(int[] numArray){
        Arrays.sort(numArray);
        int[] mode = new int[numArray.length];
        int modeNum = 0;
        int maxCount = 1;
        int count = 1;
        for (int i = 0 ; i < numArray.length - 1 ; i++){
            if (numArray[i] == numArray[i + 1]){
                count++;
            }else{
                if (maxCount < count){
                    maxCount = count;
                }
                count = 1;
            }
        }
        if (maxCount < count){
            maxCount = count;
        }
        count = 1;
        for (int i = 0 ; i < numArray.length - 1 ; i++){
            if (numArray[i] == numArray[i + 1]){
                count++;
            }else {
                if (maxCount == count){
                    mode[modeNum] = numArray[i];
                    modeNum++;
                }
                count = 1;
            }
        }
        if ((maxCount == count && maxCount != 1) | (maxCount == 1)){
            mode[modeNum] = numArray[numArray.length - 1];
            modeNum ++;
        }
        int[] simple_Mode = new int[modeNum];
        if (modeNum >= 0) System.arraycopy(mode, 0, simple_Mode, 0, modeNum);
        return simple_Mode;
    }

    public static long[] getMode(long[] numArray){
        Arrays.sort(numArray);
        long[] mode = new long[numArray.length];
        int modeNum = 0;
        int maxCount = 1;
        int count = 1;
        for (int i = 0 ; i < numArray.length - 1 ; i++){
            if (numArray[i] == numArray[i + 1]){
                count++;
            }else{
                if (maxCount < count){
                    maxCount = count;
                }
                count = 1;
            }
        }
        if (maxCount < count){
            maxCount = count;
        }
        count = 1;
        for (int i = 0 ; i < numArray.length - 1 ; i++){
            if (numArray[i] == numArray[i + 1]){
                count++;
            }else {
                if (maxCount == count){
                    mode[modeNum] = numArray[i];
                    modeNum++;
                }
                count = 1;
            }
        }
        if ((maxCount == count && maxCount != 1) | (maxCount == 1)){
            mode[modeNum] = numArray[numArray.length - 1];
            modeNum ++;
        }
        long[] simple_Mode = new long[modeNum];
        if (modeNum >= 0) System.arraycopy(mode, 0, simple_Mode, 0, modeNum);
        return simple_Mode;
    }

    public static double[] getMode(double[] numArray){
        Arrays.sort(numArray);
        double[] mode = new double[numArray.length];
        int modeNum = 0;
        int maxCount = 1;
        int count = 1;
        for (int i = 0 ; i < numArray.length - 1 ; i++){
            if (numArray[i] == numArray[i + 1]){
                count++;
            }else{
                if (maxCount < count){
                    maxCount = count;
                }
                count = 1;
            }
        }
        if (maxCount < count){
            maxCount = count;
        }
        count = 1;
        for (int i = 0 ; i < numArray.length - 1 ; i++){
            if (numArray[i] == numArray[i + 1]){
                count++;
            }else {
                if (maxCount == count){
                    mode[modeNum] = numArray[i];
                    modeNum++;
                }
                count = 1;
            }
        }
        if ((maxCount == count && maxCount != 1) | (maxCount == 1)){
            mode[modeNum] = numArray[numArray.length - 1];
            modeNum ++;
        }
        double[] simple_Mode = new double[modeNum];
        if (modeNum >= 0) System.arraycopy(mode, 0, simple_Mode, 0, modeNum);
        return simple_Mode;
    }

    public static double getVariance(int[] numArray){
        Arrays.sort(numArray);
        double average = getAverage(numArray);
        double variance = 0;
        for (int num : numArray){
            variance += (num - average) * (num - average);
        }
        return variance / numArray.length;
    }

    public static double getVariance(long[] numArray){
        Arrays.sort(numArray);
        double average = getAverage(numArray);
        double variance = 0;
        for (long num : numArray){
            variance += (num - average) * (num - average);
        }
        return (double) variance / numArray.length;
    }

    public static double getVariance(double[] numArray){
        Arrays.sort(numArray);
        double average = getAverage(numArray);
        double variance = 0;
        for (double num : numArray){
            variance += (num - average) * (num - average);
        }
        return variance / numArray.length;
    }

    public static double getStandard_Deviation(int[] numArray){
        return Math.sqrt(getVariance(numArray));
    }

    public static double getStandard_Deviation(long[] numArray){
        return Math.sqrt(getVariance(numArray));
    }

    public static double getStandard_Deviation(double[] numArray){
        return Math.sqrt(getVariance(numArray));
    }
}
