import beans.Setting;
import org.junit.Assert;
import org.junit.Test;
import util.GithubIssueTool;

public class SettingTest {
  @Test
  public void testGetSetting(){
    Setting setting = Setting.getSetting();
    Assert.assertNotNull(setting);
  }

  @Test
  public void testWriteSetting(){
    // Given
    Setting setting = new Setting();
    setting.setDownloadPath("/Users/sicmatr1x/Downloads/webpage");
    GithubIssueTool githubIssueTool = new GithubIssueTool();
    githubIssueTool.setIssueNumber("3");
    githubIssueTool.setOwner("Sicmatr1x");
    githubIssueTool.setRepo("CommandTest");
    setting.setGithubIssueTool(githubIssueTool);
    setting.setLastWorkId(458436585);
    // When
    Setting.writeSetting(setting);
    // Then
    Assert.assertNotNull(setting);
  }
}
