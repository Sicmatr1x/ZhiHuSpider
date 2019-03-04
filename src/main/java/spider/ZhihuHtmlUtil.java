package spider;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import spider.util.HeadTitleTranslator;
import spider.util.ImgDownloader;
import spider.util.ZhihuImgTranslator;

public class ZhihuHtmlUtil extends HtmlUtil {

  public static final String DOMAIN = "https://www.zhihu.com/";

  /**
   * 获取img dom
   * @param element
   * @return
   */
  public Element translateImgDom(Element element) {
    Elements noscriptNode = element.select("noscript");
    if(noscriptNode.size() == 0){
      return element;
    }
    noscriptNode.first().remove();
    Elements imgElements = element.select("img");
    ZhihuImgTranslator zhihuImgTranslator = new ZhihuImgTranslator();
    for(Element imgElement : imgElements){
      imgElement = zhihuImgTranslator.translate(imgElement);
      this.downloadImg(imgElement);
    }
    return element;
  }

  private Element downloadImg(Element element){
    ImgDownloader imgDownloader = new ImgDownloader();
    return imgDownloader.translate(element, this.title);
  }

  /**
   * 从回答页面的HTML中解析第一个主回答内容
   *
   * @return 回答内容
   */
  public Element getOneAnswer() {
    Elements questionMainColumn = this.doc.select(".Question-mainColumn");
    if (questionMainColumn.size() < 1) {
      System.out.println("error: can not found class=\"Question-mainColumn\" in " + this.address);
    } else {
      Elements cards = questionMainColumn.get(0).select(".AnswerCard");
      Elements answerItems = cards.get(0).select(".AnswerItem");
      Elements richContent = answerItems.get(0).select(".RichContent");
      Elements richText = richContent.get(0).select(".RichText");
      return richText.get(0);
    }
    return null;
  }

  public String getQuestionTitle() {
    Elements question = this.doc.select(".QuestionHeader-title");
    return question.get(0).text();
  }

  public void getAnswerList() {
    // TODO
  }

  public void getArticle() {
    // TODO
  }

  boolean initAddress() {
    if (this.address == null || this.address.length() < DOMAIN.length()) {
      System.out.print("Address is too short:" + this.address);
      return false;
    }
    return true;
  }

  void analysisPage() {
    HeadTitleTranslator headTitleTranslator = new HeadTitleTranslator();
    headTitleTranslator.setFromAddress(this.getAddress());

    int endIndex = this.address.indexOf("/", DOMAIN.length());
    String mode = this.address.substring(DOMAIN.length(), endIndex);
    this.title = this.getQuestionTitle();
    headTitleTranslator.setTitleText(this.title);

    if ("question".equals(mode)) {
      if (this.address.contains("answer")) {
        Element answerElement = this.getOneAnswer(); // 单个回答链接
        answerElement = this.translateImgDom(answerElement);
        this.content = headTitleTranslator.translate(answerElement).html();

      } else {
        this.getAnswerList(); // 问题链接
      }
    } else if ("p".equals(mode)) { // 文章链接
      this.getArticle();
    }
  }

}
