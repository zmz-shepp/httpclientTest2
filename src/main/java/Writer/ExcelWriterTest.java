package Writer;

import common.AutoLogger;
import common.ExcelReader;
import common.ExcelWriter;
import common.Report;
import inter.KeywordOfInter;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ExcelWriterTest {

    public static KeywordOfInter inter = new KeywordOfInter();

     @Test
    public  void main() {

        //打开excel文件，读取workbook对象
        ExcelReader cases = new ExcelReader("E:\\httpclientTest\\cases\\HTTPLogin.xlsx");

        //添加时间戳 ，开始时间在这里，你要么就把这个时间丢过去

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd-HHmm");
        Date now=new Date();
        String nowTime=sdf.format(now);
        SimpleDateFormat timef=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fstime=timef.format(now);
        ExcelWriter  res=new ExcelWriter( "E:\\httpclientTest\\cases\\HTTPLogin.xlsx","E:\\httpclientTest\\cases\\999999999999"+nowTime+".xlsx");



        //遍历sheet页,基于sheet页个数进行遍历
        for (int sheetIndex = 0; sheetIndex < cases.getTotalSheetNo(); sheetIndex++) {
            //指定使用当前的sheet页
            cases.useSheetByIndex(sheetIndex);

            //同时也要指定结果文件切换sheet页
            res.useSheetByIndex(sheetIndex);


            //读取当前sheet页中的每一行信息
            for (int rowIndex = 0; rowIndex < cases.rows; rowIndex++) {
                //读取每一行,存储到List中
                List<String> rowContent = cases.readLine(rowIndex);
                System.out.print(rowContent);
                if (rowContent.get(3)!=null && rowContent.get(3).length()>0){
                    //基于每个list的第四个元素也就是每一行第四个单元格内容决定执行那个关键字。
                    switch (rowContent.get(3)) {
                        case "post":
                            String postResp=  inter.testPost(rowContent.get(4), rowContent.get(5));
                            res.writeCell(rowIndex,11,postResp);

                            break;
                        case "saveParam":
                            inter.saveParam(rowContent.get(4), rowContent.get(5));
                            res.writeCell(rowIndex,10,"PASS");
                            res.writeCell(rowIndex,7,"equal");
                            res.writeCell(rowIndex,8,"$.status");

                            break;
                        case "addHeader":
                            inter.addHeader(rowContent.get(4));
                            res.writeCell(rowIndex,10,"PASS");
                            res.writeCell(rowIndex,7,"equal");
                            res.writeCell(rowIndex,8,"$.status");

                            break;
                        case "clearHeader":

                            inter.clearHeader();
                           res.writeCell(rowIndex, 10, "PASS");
                            res.writeCell(rowIndex,7,"equal");
                            res.writeCell(rowIndex,8,"$.status");

                            break;
                        default:
                            AutoLogger.log.info("没有匹配到关键字");
                            break;





                    }

                    switch (rowContent.get(7)) {
                        case "equal":
                            boolean result = inter.assertSame(rowContent.get(8), rowContent.get(9));
                            if (result) {
                                //往对应的行的第10列
                                res.writeCell(rowIndex,10,"PASS");
                                AutoLogger.log.info("外部断言通过");
                            } else {
                                res.writeFailCell(rowIndex,10,"FAIL");
                                AutoLogger.log.info("外部断言失败");
                            }
                            break;
                    }
                }

            }
        }
        res.save();
        Report ad=new Report();
        ad.sendreport("E:\\httpclientTest\\cases\\999999999999"+nowTime+".xlsx",fstime);

        //System.out.println("allure生成报告--mvn io.qameta.allure:allure-maven:serve");
    }
}