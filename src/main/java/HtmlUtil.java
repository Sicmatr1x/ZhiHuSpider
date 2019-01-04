import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtil {

  private static final String ZHIHU_DOMAIN = "https://www.zhihu.com/";

  public HtmlUtil(String address) {
    this.address = address;
  }

  private String address;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  private Document doc;

  public Document getDoc() {
    return doc;
  }

  public void getHtml() throws IOException {
    this.doc = Jsoup.connect(this.address).get();
  }

  /**
   * 从回答页面的HTML中解析第一个主回答内容
   * @return 回答内容
   */
  public Element getOneAnswer(){
    Elements questionMainColumn = this.doc.select(".Question-mainColumn");
    if(questionMainColumn.size() < 1){
      System.out.println("error: can not found class=\"Question-mainColumn\" in " + this.address);
    }else{
      Elements cards = questionMainColumn.get(0).select(".AnswerCard");
      Elements answerItems = cards.get(0).select(".AnswerItem");
      Elements richContent = answerItems.get(0).select(".RichContent");
      Elements richText = richContent.get(0).select(".RichText");
      return richText.get(0);
    }
    return null;
  }

  public void getAnswerList(){
    // TODO
  }

  public void getArticle(){
    // TODO
  }

  public void parse(){
    if(this.address == null || this.address.length() < ZHIHU_DOMAIN.length()){
      System.out.print("Address is too short");
      return;
    }

    int endIndex = this.address.indexOf("/", ZHIHU_DOMAIN.length());
    String mode = this.address.substring(ZHIHU_DOMAIN.length(), endIndex);

    if("question".equals(mode)){
      if(this.address.contains("answer")){
        this.getOneAnswer(); // 单个回答链接
      }else{
        this.getAnswerList(); // 问题链接
      }
    }else if("p".equals(mode)){ // 文章链接
      this.getArticle();
    }
  }



}
