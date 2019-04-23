import java.io.IOException;
import java.util.List;
import org.junit.Test;
import spider.GitHubCommentHtmlUtil;

public class GitHubCommentHtmlUtilTest {

  @Test
  public void test() {
    GitHubCommentHtmlUtil gitHubCommentHtmlUtil = new GitHubCommentHtmlUtil();
    gitHubCommentHtmlUtil.setAddress("https://github.com/Sicmatr1x/CommandTest/issues/3");
    try {
      gitHubCommentHtmlUtil.parse();
      List<String> commentList = gitHubCommentHtmlUtil.getCommentList();
      for (int i = 0; i < commentList.size(); i++) {
        System.out.println(commentList.get(i));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
