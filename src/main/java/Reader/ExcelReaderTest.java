package Reader;

import common.AutoLogger;
import common.ExcelReader;
import inter.KeywordOfInter;

import java.util.List;

public class ExcelReaderTest {

    public static KeywordOfInter inter = new KeywordOfInter();

    public static void main(String[] args) {

        //打开excel文件，读取workbook对象
        ExcelReader cases = new ExcelReader("E:\\httpclient\\cases\\HTTPLogin.xlsx");
        //遍历sheet页,基于sheet页个数进行遍历
        for (int sheetIndex = 0; sheetIndex < cases.getTotalSheetNo(); sheetIndex++) {
            //指定使用当前的sheet页
            cases.useSheetByIndex(sheetIndex);
            //System.out.print("++++++++++++++++++++++++当前使用的sheet页是"+cases.getSheetName(sheetIndex)+"+++++++++++++++++++++++++++++");
            //读取当前sheet页中的每一行信息
            for (int rowIndex = 0; rowIndex < cases.rows; rowIndex++) {
                //读取每一行,存储到List中
                List<String> rowContent = cases.readLine(rowIndex);
                System.out.print(rowContent);
                //基于每个list的第四个元素也就是每一行第四个单元格内容决定执行那个关键字。
                switch (rowContent.get(3)) {
                    case "post":
                        inter.testPost(rowContent.get(4), rowContent.get(5));
                        break;
                    case "saveParam":
                        inter.saveParam(rowContent.get(4), rowContent.get(5));
                        break;
                    case "addHeader":
                        inter.addHeader(rowContent.get(4));
                        break;
                    case "clearHeader":
                        inter.clearHeader();
                        break;
                    default:
                        AutoLogger.log.info("没有匹配到关键字");
                        break;
                }

                switch (rowContent.get(7)) {
                    case "equal":
                        boolean result = inter.assertSame(rowContent.get(8), rowContent.get(9));
                        if (result) {
                            AutoLogger.log.info("外部断言通过");
                        } else {
                            AutoLogger.log.info("外部断言失败");
                        }
                }
            }
        }
    }
}