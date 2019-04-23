package main;

import beans.IssueComment;
import beans.Setting;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import spider.GitHubCommentHtmlUtil;
import spider.HtmlUtil;
import spider.ZhihuHtmlUtil;
import spider.ZhihuZhuanlanHtmlUtil;
import spider.util.FilenameChecker;
import util.GithubIssueTool;
import util.Writer;

public class MainProcess {

  private Setting setting;

  private GithubIssueTool githubIssueTool;

  private IssueComment[] issueCommentList;

  private Map<String, HtmlUtil> htmlUtilMap = new HashMap<>();

  public Map<String, HtmlUtil> getHtmlUtilMap() {
    return htmlUtilMap;
  }

  public void initSetting() {
    this.setting = Setting.getSetting();
    this.githubIssueTool = this.setting.getGithubIssueTool();
  }

  public void initHtmlUtilMap() {
    htmlUtilMap.put(ZhihuHtmlUtil.DOMAIN, new ZhihuHtmlUtil());
    htmlUtilMap.put(ZhihuZhuanlanHtmlUtil.DOMAIN, new ZhihuZhuanlanHtmlUtil());
  }

  public void initCommandList() {
    this.issueCommentList = this.githubIssueTool.getIssueCommentList(false);
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

  public int downloadWebPageList(int begIndex) throws IOException {
    int downloadNum = 0;
    for (int i = begIndex + 1; i < this.issueCommentList.length; i++) {
      String address = this.issueCommentList[i].getBody();
      downloadNum++;
      System.out.println("[MainProcess]:" + this.issueCommentList[i]);
      boolean isRecognizedDomain = false;
      for (Map.Entry<String, HtmlUtil> entry : this.htmlUtilMap.entrySet()) {
        if (address.contains(entry.getKey())) {
          this.downloadWebPage(address, entry.getValue());
          System.out.println("load analyzer:Domain=" + entry.getKey() + ":class=" + entry.getValue());
          isRecognizedDomain = true;
          break;
        }
        if (!isRecognizedDomain) {// 若下载页面没有对应的解析器
          // 使用默认解析器下载
        }
      }

      try {
        System.out.println("sleep");
        Thread.sleep(60 * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    this.setting.setLastWorkId(this.issueCommentList[this.issueCommentList.length - 1].getId());
    return downloadNum;
  }

  public int process() throws IOException {
    // 在获取到的issueCommentList里找上次保存的 comment id
    int begIndex = this.issueCommentList.length;
    for (int i = 0; i < this.issueCommentList.length; i++) {
      if (this.issueCommentList[i].getId() == this.setting.getLastWorkId()) {
        begIndex = i;
      }
    }

    int downloadNum = 0;
    if (begIndex == this.issueCommentList.length) { // 没找到

    } else if (begIndex == this.issueCommentList.length - 1) { // 上次爬取到了最后一个元素
      return 0;
    } else {// 在上次爬取到的 comment 之后还有新的 comment
      downloadNum = this.downloadWebPageList(begIndex);
    }
    return downloadNum;
  }

  private void saveSetting(){
    Setting.writeSetting(this.setting);
  }

  public void run() {
    this.initSetting();
    this.initHtmlUtilMap();
    this.initCommandList();
    try {
      this.process();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.saveSetting();
  }

  public static void main(String[] args) {
    MainProcess mainProcess = new MainProcess();
    mainProcess.run();
    System.out.println("finish");
  }
}
