package post;

import com.alibaba.fastjson.JSONPath;
import common.AutoLogger;
import inter.HttpClientKw;

public class TestingLoginTest {
    //用于存储参数的map
   // static Map<String, String> paramMap;




    public static void main(String[] args) {
        HttpClientKw key=new HttpClientKw();


      //  paramMap=new HashMap<String,String>();






        //第一步通过调用auth接口货期token值
        String authResult=key.doPost("http://www.testingedu.com.cn:8081/inter/HTTP/auth","", "tokenValue","");

        AutoLogger.log.info("auth接口的返回信息"+authResult);
        String a=JSONPath.read(authResult,"token").toString();
        if (new Integer(200).equals(JSONPath.read(authResult,"status"))){
            AutoLogger.log.info("测试成功");
        }else {
            AutoLogger.log.info("测试失败");
        }

    // paramMap.put("token", JSONPath.read(authResult, "$.token").toString());
       //AutoLogger.log.info("token的值是"+paramMap.get("token"));

        //创建一个map用于添加头域
//        Map<String, String> headerMap=new HashMap<String,String>();
//        //添加token和它的值作为键值对存储到map中
//        headerMap.put("token", paramMap.get("token"));
//        //添加headermap
//        key.addHeader(headerMap);


        //调用register注册接口
        String  registerResult=key.doPost("http://www.testingedu.com.cn:8081/inter/HTTP//register","username=5253699&pwd=1235&nickname=117&describe=557","tokenValue",a);
        AutoLogger.log.info("register注册接口返回"+registerResult);
        if (new String("注册成功").equals(JSONPath.read(registerResult,"msg"))){
            AutoLogger.log.info("测试成功");
        }else {
            AutoLogger.log.info("测试失败");
        }


      //调用login接口
    String login =key.doPost("http://www.testingedu.com.cn:8081/inter/HTTP/login","username=5253699&password=1235","tokenValue", a);
    String  b=JSONPath.read(login,"$.userid").toString();
      AutoLogger.log.info("login接口返回信息是"+login);
        if (new String("恭喜您，登录成功").equals(JSONPath.read(login,"msg"))){
            AutoLogger.log.info("测试成功");
        }else {
            AutoLogger.log.info("测试失败");
        }


      //调用查询接口  传递id  从login接口中返回，参数传递
        String userInfoResult=key.doPost("http://www.testingedu.com.cn:8081/inter/HTTP/getUserInfo","id="+b,"tokenValue",a);
        AutoLogger.log.info(userInfoResult);
        if (new String("查询成功").equals(JSONPath.read(userInfoResult,"msg"))){
            AutoLogger.log.info("测试成功");
        }else {
            AutoLogger.log.info("测试失败");
        }


        //调用登出接口
        String logOutResult=key.doPost("http://www.testingedu.com.cn:8081/inter/HTTP/logout", "","token",a);
        AutoLogger.log.info(logOutResult);
   }
}
