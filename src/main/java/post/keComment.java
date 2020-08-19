package post;

import com.alibaba.fastjson.JSONPath;
import common.AutoLogger;
import org.apache.http.HttpConnection;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class keComment {
    public static void main(String[] args) throws IOException {
       CloseableHttpClient client= HttpClients.createDefault();
        HttpGet get=new HttpGet("https://ke.qq.com/cgi-bin/comment_new/course_comment_list?cid=315793&count=10&page=0&filter_rating=0&bkn=1880206902&r=0.6353740272814727");

        //直接请求的时候会有referer错误，添加referer头域
       get.addHeader("referer","https://ke.qq.com/course/315793?taid=9631386852184465");

        CloseableHttpResponse resp=client.execute(get);
        String josnResult= EntityUtils.toString(resp.getEntity(),"UTF-8");

        //日志输出
        AutoLogger.log.info(josnResult);


        /**
         * jsonPath循环输出
         */
        String comment="";
        for (int i=0;i<10;i++) {
          comment=   JSONPath.read(josnResult, "$.result.items["+i+"].first_comment").toString();
            AutoLogger.log.info(comment);
        }

    }
}


