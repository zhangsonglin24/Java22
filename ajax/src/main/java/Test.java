import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException{

      /*  CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/save");
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username","kangkang"));
        params.add(new BasicNameValuePair("address","shanghai"));

        httpPost.setEntity(new UrlEncodedFormEntity(params));

        for(int i = 0;i < 10;i++){
            httpClient.execute(httpPost);
        }*/



    /*   CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://ww1.sinaimg.cn/mw690/824de770jw1epwcnivby6j20go0p00x4.jpg");
        HttpResponse response = httpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode() == 200){
            InputStream inputStream = response.getEntity().getContent();
            OutputStream outputStream = new FileOutputStream("D:/xx.jpg");

            IOUtils.copy(inputStream,outputStream);

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        }
        httpClient.close();

*/

        //创建一个可以发送请求的客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个GET请求方式
        HttpGet httpGet = new HttpGet("http://www.kaishengit.com");
        //发送请求并接受服务端响应
        HttpResponse response = httpClient.execute(httpGet);

        //获取响应的状态码
        int stateCode = response.getStatusLine().getStatusCode();

        if(stateCode == 200){
            //获取响应输入流
            InputStream inputStream = response.getEntity().getContent();

            String result = IOUtils.toString(inputStream,"UTF-8");
            inputStream.close();

            System.out.println(result);
        }else{
            System.out.println("服务器异常" + stateCode);
        }

        httpClient.close();

    }
}
