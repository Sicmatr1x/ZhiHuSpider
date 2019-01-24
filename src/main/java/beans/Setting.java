package beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import util.GithubIssueTool;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Setting {
  private String downloadPath;
  private GithubIssueTool githubIssueTool;

  public String getDownloadPath() {
    return downloadPath;
  }

  public void setDownloadPath(String downloadPath) {
    this.downloadPath = downloadPath;
  }

  public GithubIssueTool getGithubIssueTool() {
    return githubIssueTool;
  }

  public void setGithubIssueTool(GithubIssueTool githubIssueTool) {
    this.githubIssueTool = githubIssueTool;
  }

  @Override
  public String toString() {
    return "Setting{" +
        "downloadPath='" + downloadPath + '\'' +
        ", githubIssueTool=" + githubIssueTool +
        '}';
  }
}
