import org.junit.Assert;
import org.junit.Test;
import util.GithubIssueTool;

public class GithubIssueToolTest {

  @Test
  public void testGetCommonList(){
    // Given
    String owner = "Sicmatr1x";
    String repo = "CommandTest";
    String issueNumber = "1";
    GithubIssueTool githubIssueTool = new GithubIssueTool(owner, repo, issueNumber);
    // When
    String responseJson = githubIssueTool.getCommonList();
    // Then
    Assert.assertNotNull(responseJson);
  }
}
