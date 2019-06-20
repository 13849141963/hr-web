package webservice.test;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import java.io.File;

public class TestUpload {

  public static void main(String[] args) throws Exception{
    //此处应为上传的文件，本处测试使用的是
    File f = new File("D:" + File.separatorChar + "open-controler" + File.separatorChar + "debuglogs" + File.separatorChar + "aaaa.png");
    Part[] parts = {
        new StringPart("verify", "2"),
        new StringPart("elecMatSource","3"),
        new StringPart("busiNo",""),
        new StringPart("type","189"),
        new StringPart("cardType",""),
        new StringPart("cardNo",""),
        new StringPart("uniqNo","61213144"),
        new StringPart("fileNo","1"),
        new StringPart("filetype","png"),
        new StringPart("format","json"),
        new StringPart("sign","自己计算出的签名"),
        new StringPart("methodName","fileservice.uploadByUniqNo"),//调用的文件服务的方法名
        new StringPart("timeStamp","1515048838688"),
        new StringPart("appkey","自己的appkey"),
        new FilePart(f.getName(), f)
    };
    String url = "https://apics.fesco.com.cn/uploadFileTest";
    String r = FileSdk.uploadFileByParam(url, null, parts);
    System.out.println(r);
  }

}
class FileSdk {

  public FileSdk() {
  }


  public static String uploadFileByParam(String targetURL, byte[] data, Part[] parts) {
    PostMethod filePost = new PostMethod(targetURL);
    String result = "";

    try {
      filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
      HttpClient client = new HttpClient();
      client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
      int status = client.executeMethod(filePost);
      if (status == 200) {
        System.out.println("上传成功");
      } else {
        System.out.println("上传失败");
      }

      result = filePost.getResponseBodyAsString();
    } catch (Exception var10) {
      var10.printStackTrace();
    } finally {
      filePost.releaseConnection();
    }

    return result;
  }

}
