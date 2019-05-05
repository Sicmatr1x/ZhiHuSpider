import beans.Setting;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import spider.GitHubCommentHtmlUtil;
import spider.HtmlUtil;
import spider.ZhihuHtmlUtil;
import spider.ZhihuZhuanlanHtmlUtil;
import spider.util.FilenameChecker;
import util.Writer;

public class easy {

  private Map<String, HtmlUtil> htmlUtilMap = new HashMap<>();

  @Before
  public void initHtmlUtilMap() {
    htmlUtilMap.put(ZhihuHtmlUtil.DOMAIN, new ZhihuHtmlUtil());
    htmlUtilMap.put(ZhihuZhuanlanHtmlUtil.DOMAIN, new ZhihuZhuanlanHtmlUtil());
  }

  public void downloadWebPage(String address, HtmlUtil htmlUtil) throws IOException {
    try{
      htmlUtil.setAddress(address);
      htmlUtil.parse();
      String fileName = FilenameChecker.getLegalFileName(htmlUtil.getTitle());
      Writer.writeFile(htmlUtil.getContent(),
          Setting.getSetting().getDownloadPath() + "/" + fileName + ".html");
    }catch(java.lang.NullPointerException error){
      System.out.println("[downloadWebPage]:fail:" + address);
      return;
    }
  }

  @Test
  public void startWithGitHubCommentHtmlUtil() {
    GitHubCommentHtmlUtil gitHubCommentHtmlUtil = new GitHubCommentHtmlUtil();
    gitHubCommentHtmlUtil.setAddress("https://github.com/Sicmatr1x/CommandTest/issues/3");
    try {
      gitHubCommentHtmlUtil.parse();
    } catch (IOException e) {
      e.printStackTrace();
    }
    List<String> commentList = gitHubCommentHtmlUtil.getCommentList();
    String lastWorkComment = "https://zhuanlan.zhihu.com/p/64246057?utm_source=ZHShareTargetIDMore&utm_medium=social&utm_oi=599879765319094272";
    for (int i = 0; i < commentList.size(); i++) {
      String curComment = commentList.get(i);
      if (lastWorkComment == null || "".equals(lastWorkComment) || lastWorkComment.equals(curComment)) {
        boolean isRecognizedDomain = false;
        for (Map.Entry<String, HtmlUtil> entry : this.htmlUtilMap.entrySet()) {
          if (curComment.contains(entry.getKey())) {
            try {
              this.downloadWebPage(curComment, entry.getValue());
            } catch (IOException e) {
              e.printStackTrace();
            }
            System.out.println("load analyzer:Domain=" + entry.getKey() + ":class=" + entry.getValue());
            isRecognizedDomain = true;
            break;
          }
          if (!isRecognizedDomain) {// 若下载页面没有对应的解析器
            // 使用默认解析器下载
          }
        }
      }
      try {
        Thread.sleep(30 * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
