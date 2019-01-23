package util;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetGithubIssueTool {
  private final static String GET_ISSUE_COMMENT_LIST_URL = "https://api.github.com/repos/:owner/:repo/issues/:number/comments";

  private String owner;
  private String repo;
  private String issueNumber;

  public GetGithubIssueTool(String owner, String repo, String issueNumber) {
    this.owner = owner;
    this.repo = repo;
    this.issueNumber = issueNumber;
  }

  private String initURL(){
    String URL = GET_ISSUE_COMMENT_LIST_URL;
    URL = URL.replace(":owner", this.owner).replace(":repo", this.repo).replace(":number", this.issueNumber);
    return URL;
  }

  public String getCommonList(){
    CloseableHttpClient httpclient = HttpClients.createDefault();
    try {
      // 创建httpget.
      HttpGet httpget = new HttpGet(this.initURL());
      System.out.println("executing request " + httpget.getURI());
      // 执行get请求.
      CloseableHttpResponse response = httpclient.execute(httpget);
      try {
        // 获取响应实体
        HttpEntity entity = response.getEntity();
        // 打印响应状态
//        System.out.println(response.getStatusLine());
        if (entity != null) {
          // 打印响应内容
          return EntityUtils.toString(entity);
        }
      } finally {
        response.close();
      }
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      // 关闭连接,释放资源
      try {
        httpclient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
