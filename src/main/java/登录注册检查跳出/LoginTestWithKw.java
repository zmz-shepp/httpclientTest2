package 登录注册检查跳出;

import inter.KeywordOfInter;
import org.testng.annotations.Test;


@Test
public class LoginTestWithKw {
    public static void main(String[] args) {
        KeywordOfInter key=new KeywordOfInter();
        key.testPost("http://www.testingedu.com.cn:8081/inter/HTTP/auth","");
        key.saveParam("saveParam","$.token");
        key.assertContains("status","200");

        //添加头域信息到httpclientkw的map中，接下来所有的请求都能够使用这个头域
        key.addHeader("{\"token\":\"{saveParam}\"}");

        //login登录请求
        key.testPost("http://www.testingedu.com.cn:8081/inter/HTTP/login", "username=roy3&password=123456");
        key.saveParam("idValue","$.userid");
        key.assertSame("$.msg","恭喜您，登录成功");

        //UserInfo查询接口
        key.testPost("http://www.testingedu.com.cn:8081/inter/HTTP/getUserInfo","id={idValue}");
        key.assertSame("$.msg","查询成功");

        //登出接口
        key.testPost("http://www.testingedu.com.cn:8081/inter/HTTP/logout", "");
    }
}
