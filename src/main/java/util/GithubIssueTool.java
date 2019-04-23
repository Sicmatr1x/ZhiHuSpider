package util;

import beans.IssueComment;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import spider.GitHubCommentHtmlUtil;

public class GithubIssueTool {
  private final static String GET_ISSUE_COMMENT_LIST_URL = "https://api.github.com/repos/:owner/:repo/issues/:number/comments";

  private String owner;
  private String repo;
  private String issueNumber;

  public GithubIssueTool() {

  }

  public GithubIssueTool(String owner, String repo, String issueNumber) {
    this.owner = owner;
    this.repo = repo;
    this.issueNumber = issueNumber;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getRepo() {
    return repo;
  }

  public void setRepo(String repo) {
    this.repo = repo;
  }

  public String getIssueNumber() {
    return issueNumber;
  }

  public void setIssueNumber(String issueNumber) {
    this.issueNumber = issueNumber;
  }

  @Override
  public String toString() {
    return "GithubIssueTool{" +
        "owner='" + owner + '\'' +
        ", repo='" + repo + '\'' +
        ", issueNumber='" + issueNumber + '\'' +
        '}';
  }

  private String initURL(){
    String URL = GET_ISSUE_COMMENT_LIST_URL;
    URL = URL.replace(":owner", this.owner).replace(":repo", this.repo).replace(":number", this.issueNumber);
    return URL;
  }

  @JsonIgnore
  public IssueComment[] getIssueCommentList(boolean isGitHubApi){
    if(isGitHubApi) {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        IssueComment[] issueCommentList = objectMapper.readValue(this.getCommonList(), IssueComment[].class);
        return issueCommentList;
      } catch (JsonParseException e) {
        e.printStackTrace();
      } catch (JsonMappingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      GitHubCommentHtmlUtil gitHubCommentHtmlUtil = new GitHubCommentHtmlUtil();
      gitHubCommentHtmlUtil.setAddress("https://github.com/" + this.owner + "/" + this.repo + "/" + this.issueNumber); // https://github.com/Sicmatr1x/CommandTest/issues/3
      try {
        gitHubCommentHtmlUtil.parse();
        List<String> commentList = gitHubCommentHtmlUtil.getCommentList();
        IssueComment[] issueCommentList = new IssueComment[commentList.size()];
        for (int i = 0; i < commentList.size(); i++) {
//          System.out.println(commentList.get(i));
          issueCommentList[i] = new IssueComment();
          issueCommentList[i].setBody(commentList.get(i));
        }
        return issueCommentList;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  private String getCommonList(){
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
