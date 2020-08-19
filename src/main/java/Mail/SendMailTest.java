package Mail;

import common.Report;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMailTest {
    public static void main(String[] args) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = df.format(new Date());
        System.out.println(startTime);

        Report ad=new Report();
        ad.sendreport("E:\\httpclient\\cases\\成功78888820200814-1846.xlsx",startTime);




    }
}
