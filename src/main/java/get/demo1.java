package get;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class demo1 {
    @Test
    public static void main(String[] args) throws IOException {
        /**
         * 星座运势免费
         */

        //创建httpclient实力化对象,作为客户端,完成发包
        CloseableHttpClient client= HttpClients.createDefault();

        //创建HttpPGET方法,填写url
        HttpGet git =new HttpGet("http://web.juhe.cn:8080/constellation/getAll?key=c975156b793c6bc4b5d78674595be3f5&consName=天秤座&type=month");

        //设置请求体,直接用原始的字符串形式,再指定content-type格式
       // StringEntity loginParam=new StringEntity("key=c975156b793c6bc4b5d78674595be3f5&consName=天秤座&type=month");

        git.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        CloseableHttpResponse LoginRe=client.execute(git);
        String LoginResult= EntityUtils.toString(LoginRe.getEntity());
        System.out.print("返回结果是"+LoginResult);


    }
}
