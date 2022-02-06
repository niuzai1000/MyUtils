package cn.wqy.TimeUtils;
import cn.wqy.UtilsAnnotation.UtilsAnnotation;

import java.io.Serializable;
import java.util.Objects;

import static cn.wqy.TimeUtils.MyTime_Type.*;

@UtilsAnnotation("MyTime Utils")
public class MyTime implements Comparable<MyTime> , Serializable {
    private static final long serialVersionUID = 930099323875489780L;
    private long timeMillis = 0;
    private long millis = 0;
    private long seconds = 0;
    private long minutes = 0;
    private long hours = 0;
    private long days = 0;

    public MyTime(long length, MyTime_Type tf) {
        if (length < 0){
            throw new MyTimeException("TimeMillis shouldn't be negative");
        }
        if (tf.equals(MILLIS)) timeMillis = length;
        if (tf.equals(SECOND)) timeMillis = length * 1000;
        if (tf.equals(MINUTE)) timeMillis = length * 1000 * 60;
        if (tf.equals(HOUR)) timeMillis = length * 1000 * 60 * 60;
        if (tf.equals(DAY)) timeMillis = length * 1000 * 60 * 60 * 24;
        reset();
    }

    public MyTime(long time) {
        this(time , MILLIS);
    }

    public MyTime() {}

    /**
     * 根据timeMillis重新设置其他变量
     */
    private void reset() {
        millis = timeMillis - (timeMillis / 1000) * 1000;
        seconds = (timeMillis - millis - (timeMillis / (60 * 1000)) * 60 * 1000) / 1000;
        minutes = (timeMillis - seconds * 1000 - millis - (timeMillis / (60 * 1000 * 60)) * (60 * 60 * 1000))/(60 * 1000);
        hours = (timeMillis - minutes * 1000 * 60 - seconds * 1000 - millis - (timeMillis / (1000 * 60 * 60 * 24) * (1000 * 60 * 60 * 24))) / (1000 * 60 * 60);
        days = timeMillis / (1000 * 60 * 60 * 24);
    }

    @Override
    public String toString(){
        if (days == 0){
            if (hours == 0){
                if (minutes == 0) {
                    if(seconds == 0){
                        return millis + "毫秒";
                    }
                    return seconds + "秒 " + millis + "毫秒";
                }
                return minutes + "分 " + seconds + "秒 " + millis + "毫秒";
            }
            return hours + "时 " + minutes + "分 " + seconds + "秒 " + millis + "毫秒";
        }
        return days + "天 " + hours + "时 " + minutes + "分 " + seconds + "秒 " + millis + "毫秒";
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(long timeMillis) {
        if (timeMillis < 0){
            throw new MyTimeException("timeMillis shouldn't be negative");
        }
        this.timeMillis = timeMillis;
        reset();
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        if (millis < 0 | millis > 999){
            throw new MyTimeException("Millis should be in [0 , 999]");
        }
        addMillis(millis - this.millis);
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        if (seconds < 0 | seconds > 59){
            throw new MyTimeException("Seconds should be in [0 , 60]");
        }
        addSeconds(seconds - this.seconds);
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        if (minutes < 0 | minutes > 59){
            throw new MyTimeException("Minutes should be in [0 , 60]");
        }
        addMinutes(minutes - this.minutes);
    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        if (hours < 0 | hours > 23){
            throw new MyTimeException("Hours shouldn't be negative");
        }
        addHours(hours - this.hours);
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        if (days < 0){
            throw new MyTimeException("Days shouldn't be negative");
        }
        addDays(days - this.days);
    }

    public void add(MyTime t){
        this.timeMillis += t.getTimeMillis();
        reset();
    }

    public void reduce(MyTime t){
        this.timeMillis -= t.getTimeMillis();
        if (timeMillis < 0) throw new MyTimeException("TimeMillis shouldn't be negative");
        reset();
    }

    public void addMillis(long length){
        timeMillis += length;
        reset();
    }

    public void addSeconds(long length){
        timeMillis += length * 1000;
        reset();
    }

    public void addMinutes(long length){
        timeMillis += length  * 1000 * 60;
        reset();
    }

    public void addHours(long length){
        timeMillis += length * 1000 * 60 * 60;
        reset();
    }

    public void addDays(long length){
        timeMillis += length * 1000 * 60 * 60 * 24;
    }

    public void reduceMillis(long length){
        timeMillis -= length;
        if (timeMillis < 0) throw new MyTimeException("TimeMillis shouldn't be negative");
        reset();
    }

    public void reduceSecond(long length){
        timeMillis -= length * 1000;
        if (timeMillis < 0) throw new MyTimeException("TimeMillis shouldn't be negative");
        reset();
    }

    public void reduceMinutes(long length){
        timeMillis -= length * 1000 * 60;
        if (timeMillis < 0) throw new MyTimeException("TimeMillis shouldn't be negative");
        reset();
    }

    public void reduceHours(long length){
        timeMillis -= length * 1000 * 60 * 60;
        if (timeMillis < 0) throw new MyTimeException("TimeMillis shouldn't be negative");
        reset();
    }

    public void reduceDays(long length){
        timeMillis -= length * 1000 * 60 * 60 * 24;
        if (timeMillis < 0) throw new MyTimeException("TimeMillis shouldn't be negative");
        reset();
    }
    public void multiply(int rate){
        timeMillis *= rate;
        reset();
    }

    public void divide(int rate){
        timeMillis /= rate;
        reset();
    }

    public void add(long length , MyTime_Type tf){
        if (tf.equals(MILLIS)) timeMillis += length;
        if (tf.equals(SECOND)) timeMillis += length * 1000;
        if (tf.equals(MINUTE)) timeMillis += length * 1000 * 60;
        if (tf.equals(HOUR)) timeMillis += length * 1000 * 60 * 60;
        if (tf.equals(DAY)) timeMillis += length * 1000 * 60 * 60 * 24;
        reset();
    }

    public void reduce(long length , MyTime_Type tf){
        if (tf.equals(MILLIS)) timeMillis -= length;
        if (tf.equals(SECOND)) timeMillis -= length * 1000;
        if (tf.equals(MINUTE)) timeMillis -= length * 1000 * 60;
        if (tf.equals(HOUR)) timeMillis -= length * 1000 * 60 * 60;
        if (tf.equals(DAY)) timeMillis -= length * 1000 * 60 * 60 * 24;
        if (timeMillis < 0) throw new MyTimeException("TimeMillis shouldn't be negative");
        reset();
    }

    @Override
    public int compareTo(MyTime t) {
        return Long.compare(timeMillis, t.getTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTime time = (MyTime) o;
        return timeMillis == time.timeMillis &&
                millis == time.millis &&
                seconds == time.seconds &&
                minutes == time.minutes &&
                hours == time.hours &&
                days == time.days;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeMillis, millis, seconds, minutes, hours, days);
    }
}
