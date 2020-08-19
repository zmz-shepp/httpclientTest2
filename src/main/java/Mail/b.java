package Mail;

import common.AutoLogger;
import common.ExcelReader;
import common.ExcelWriter;
import common.Report;
import inter.KeywordOfInter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class b {
    @BeforeMethod
    public void a(){
        System.out.println("b1");
    }
    @Test
    public void b(){
        System.out.println("b2");
    }
     @Test
    public void c(){
        System.out.println("b3");
    }
    @Test
    public void d(){
        System.out.println("b4");
    }


    @AfterSuite
    public  void s(){
        System.out.println("bbbbbb执行完成");
    }

    }

