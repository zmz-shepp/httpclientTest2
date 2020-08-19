package post;

import com.alibaba.fastjson.JSONPath;
import common.AutoLogger;
import inter.HttpClientKw;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 *       住逻辑登录   https://b.zhulogic.com/next/sign?redirect=%2F   （错误登录）
 *       断言
 */

public class zhujsontest {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient  client= HttpClients.createDefault();

        HttpPost post=new HttpPost("https://b.zhulogic.com/designer_api/account/login_quick_pc");

        StringEntity jsonParm=new StringEntity("{\"phone\":\"15114557896\",\"code\":\"\",\"messageType\":3,\"registration_type\":1,\"channel\":\"zhulogic\",\"unionid\":\"\"}");
        jsonParm.setContentType("application/json");
        jsonParm.setContentEncoding("charset=UTF-8");

        post.setEntity(jsonParm);

        CloseableHttpResponse jsonPesp=client.execute(post);

        String result= EntityUtils.toString(jsonPesp.getEntity(),"UTF-8");




        //断言

        String uniResult= HttpClientKw.DeCode(result);

        AutoLogger.log.info("返回结果是"+uniResult);

          String status_code=JSONPath.read(uniResult,"status_code").toString();

          AutoLogger.log.info(status_code);

          if (new String("1001").equals(status_code)){
              AutoLogger.log.info("测试通过");
          }
    }
}
