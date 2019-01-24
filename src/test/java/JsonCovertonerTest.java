import beans.IssueComment;
import org.junit.Assert;
import org.junit.Test;
import util.GetGithubIssueTool;
import util.JsonCovertoner;

public class JsonCovertonerTest {

  @Test
  public void test(){
    // Given
    String owner = "Sicmatr1x";
    String repo = "CommandTest";
    String issueNumber = "1";
    GetGithubIssueTool getGithubIssueTool = new GetGithubIssueTool(owner, repo, issueNumber);
    JsonCovertoner jsonCovertoner = new JsonCovertoner();
    String responseJson = getGithubIssueTool.getCommonList();
    // When
    IssueComment[] issueCommentList = jsonCovertoner.getIssueCommentList(responseJson);
    // Then
    for(IssueComment issueComment : issueCommentList){
      System.out.println(issueComment);
    }
    Assert.assertNotNull(issueCommentList);
  }
}
