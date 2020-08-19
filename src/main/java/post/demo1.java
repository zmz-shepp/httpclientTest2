package post;

import common.AutoLogger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class demo1 {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client= HttpClients.createDefault();

        HttpPost loginPost=new HttpPost("http://www.testingedu.com.cn:8000/index.php?m=Home&c=User&a=do_login&t=0.31671045103291373");

        StringEntity  loginParm=new StringEntity("username=13800138006&password=123456&verify_code=1");
        loginParm.setContentType("application/x-www-form-urlencoded");
        loginParm.setContentEncoding("UTF-8");
        loginPost.setEntity(loginParm);

        //loginPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");

        CloseableHttpResponse a=client.execute(loginPost);


        AutoLogger.log.info("返回结果是"+EntityUtils.toString(a.getEntity()));




    }
}
