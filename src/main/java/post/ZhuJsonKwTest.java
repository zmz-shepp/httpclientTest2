package post;

import common.AutoLogger;
import inter.HttpClientKw;

public class ZhuJsonKwTest {
    public static void main(String[] args) {
        HttpClientKw  key=new HttpClientKw();
        String result=key.doGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php","query=180.149.139.248&resource_id=6006&ie=utf8&oe=gbk");
        AutoLogger.log.info(result);
    }
}
