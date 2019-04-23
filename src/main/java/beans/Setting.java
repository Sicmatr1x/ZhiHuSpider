package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import util.GithubIssueTool;
import util.Reader;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Setting {

  private static String SETTING_FILE_NAME = "setting.json";

  private String downloadPath;
  private GithubIssueTool githubIssueTool;
  private int lastWorkId;
  private String lastWorkBody;

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

  public int getLastWorkId() {
    return lastWorkId;
  }

  public void setLastWorkId(int lastWorkId) {
    this.lastWorkId = lastWorkId;
  }

  public String getLastWorkBody() {
    return lastWorkBody;
  }

  public void setLastWorkBody(String lastWorkBody) {
    this.lastWorkBody = lastWorkBody;
  }

  @Override
  public String toString() {
    return "Setting{" +
        "downloadPath='" + downloadPath + '\'' +
        ", githubIssueTool=" + githubIssueTool +
        ", lastWorkId=" + lastWorkId +
        ", lastWorkBody='" + lastWorkBody + '\'' +
        '}';
  }

  public static Setting getSetting() {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = Reader.readFile(SETTING_FILE_NAME);
    try {
      Setting setting = objectMapper.readValue(json, Setting.class);
      return setting;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void writeSetting(Setting setting) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      File file = new File(SETTING_FILE_NAME);
      if (!file.exists()) {
        file.createNewFile();
      }
      file.delete();
      FileOutputStream out = new FileOutputStream(file, true);
      objectMapper.writeValue(out, setting);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
