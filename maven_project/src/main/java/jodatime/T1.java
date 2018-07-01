package jodatime;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class T1 {
    private static void generateDateTime() {
        // 方法一：取系统点间
        DateTime dt1 = new DateTime();

        // 方法二：通过java.util.Date对象生成
        DateTime dt2 = new DateTime(new Date());

        // 方法三：指定年月日点分秒生成(参数依次是：年,月,日,时,分,秒,毫秒)
        DateTime dt3 = new DateTime(2012, 5, 20, 13, 14, 0, 0);

        // 方法四：ISO8601形式生成
        DateTime dt4 = new DateTime("2012-05-20"); // 经测试2012-05-20 可以 20120520则不行
        // 输出： dt4 = 2012-05-20T00:00:00.000+08:00, dt4.toString("yyyy-MM-dd") = 2012-05-20
        // 这说明 DateTime对象默认输出的是包含年月日时分秒等信息的，如果想输出指定格式的字符串，则必须的写成DateTime.toString("格式符号")
        System.out.println("dt4 = " + dt4 + ", dt4.toString(\"yyyy-MM-dd\") = " + dt4.toString("yyyy-MM-dd"));
        
        DateTime dt5 = new DateTime("2012-05-20T18:14:00");

        // 只需要年月日的时候
        LocalDate localDate = new LocalDate(2009, 9, 6);// September 6, 2009

        // 只需要时分秒毫秒的时候
        LocalTime localTime = new LocalTime(13, 30, 26, 0);// 1:30:26PM

        System.out.println("dt1 = " + dt1.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println("dt2 = " + dt2.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println("dt3 = " + dt3.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println("dt4 = " + dt4.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println("dt5 = " + dt5.toString("yyyy年MM月dd日HH点mm分ss秒"));
        // 输出 2009年09月06日��点��分��秒 有乱码是因为localDate只存储年月日 不存储时分秒
        System.out.println(localDate.toString("yyyy年MM月dd日HH点mm分ss秒"));

        // 输出 ����年��月��日13点30分26秒 有乱码是因为localDate只存储时分秒 不存储年月日
        System.out.println(localTime.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println();
    }

    public static void main(String[] args) {
        generateDateTime();
    }

}
