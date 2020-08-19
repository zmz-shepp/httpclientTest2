package Upload;

import common.AutoLogger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class ShopUploadTest {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client= HttpClients.createDefault();
        HttpPost post=new HttpPost("http://www.testingedu.com.cn:8000/index.php/home/Uploadify/imageUp/savepath/head_pic/pictitle/banner/dir/images.html");

       //创建一个multipart格式请
        MultipartEntityBuilder meb=MultipartEntityBuilder.create();
       // meb.addTextBody("id","WU_FILE_0");

        //注意,文件参数名字不一定都叫file,根据自己得接口文档信息进行编写
        meb.addBinaryBody("file",new File("E:\\2222233.png"));

        //用构造器完成构造, 生成一个组合好得httpentity
        HttpEntity me=meb.build();

        //设置为请求,因为multipartentitybuilder默认使用content-type就是multipart/form-data
        post.setEntity(me);


        CloseableHttpResponse uploadResp=client.execute(post);
       String a= EntityUtils.toString(uploadResp.getEntity(),"UTF-8");
        AutoLogger.log.info(a);


    }
}
