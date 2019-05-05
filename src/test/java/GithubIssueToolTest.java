import beans.IssueComment;
import org.junit.Assert;
import org.junit.Test;
import util.GithubIssueTool;

public class GithubIssueToolTest {

  @Test
  public void testGetCommonList(){
    // Given
    String owner = "Sicmatr1x";
    String repo = "CommandTest";
    String issueNumber = "3";
    GithubIssueTool githubIssueTool = new GithubIssueTool(owner, repo, issueNumber);
    // When
    IssueComment[] issueCommentList = githubIssueTool.getIssueCommentList(true);
    // Then
    for(IssueComment issueComment : issueCommentList){
      System.out.println(issueComment);
    }
    Assert.assertNotNull(issueCommentList);
  }
}
