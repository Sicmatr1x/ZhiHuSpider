package spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class GitHubCommentHtmlUtil extends HtmlUtil{

  public static final String DOMAIN = "https://github.com/";

  private List<String> commentList;

  public void setCommentList(List<String> commentList) {
    this.commentList = commentList;
  }

  @Override
  void getHtml() throws IOException {
    this.doc = Jsoup.connect(this.address).get();
  }

  @Override
  boolean initAddress() {
    if (this.address == null || this.address.length() < DOMAIN.length()) {
      System.out.print("Address is too short:" + this.address);
      return false;
    }
    return true;
  }

  public List<String> getCommentList() {
    List<String> commentList = new ArrayList<>();
    Elements commentListNode = this.doc.select(".js-discussion");
    Elements commentNode = commentListNode.select(".edit-comment-hide");
    for(int i = 0; i < commentNode.size(); i++) {
      Elements body = commentNode.get(i).select(".markdown-body");
      String text = body.select("a").attr("href");
      commentList.add(text);
    }
    return commentList;
  }

  @Override
  void analysisPage() {
    this.setCommentList(this.getCommentList());
  }
}
