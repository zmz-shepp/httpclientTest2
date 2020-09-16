package Mail;

import common.Report;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMailTest {
    public static void main(String[] args) {

        //这么获取，是你执行这个类的时候的时间，跟开始时间没有关系啊

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = df.format(new Date());
        System.out.println(startTime);

       //这个才是开始时间啊 11120200831-1535.
        Report ad=new Report();
        ad.sendreport("E:\\httpclientTest\\cases\\11120200831-1535.xlsx",startTime);




    }
}
