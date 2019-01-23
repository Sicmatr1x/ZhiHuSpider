import org.junit.Assert;
import org.junit.Test;
import util.GetGithubIssueTool;

public class GetGithubIssueToolTest {

  @Test
  public void testGetCommonList(){
    // Given
    String owner = "Sicmatr1x";
    String repo = "CommandTest";
    String issueNumber = "1";
    GetGithubIssueTool getGithubIssueTool = new GetGithubIssueTool(owner, repo, issueNumber);
    // When
    String responseJson = getGithubIssueTool.getCommonList();
    // Then
    Assert.assertNotNull(responseJson);
  }
}
