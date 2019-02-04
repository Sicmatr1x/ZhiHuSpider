package spider;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import spider.util.ImgDownloader;
import spider.util.ImgTranslator;

public class ZhihuHtmlUtil extends HtmlUtil implements ImgTranslator {

  public static final String DOMAIN = "https://www.zhihu.com/";

  /**
   * 获取img dom
   * @param element
   * @return
   */
  @Override
  public Element translate(Element element) {
    element.select("noscript").first().remove();
    Elements imgElements = element.select("img");
    for(Element imgElement : imgElements){
      String srcAddress = imgElement.attr("data-original");
      String rawWidth = imgElement.attr("data-rawwidth");
      String rawHeight = imgElement.attr("data-rawheight");
      imgElement.attr("src", srcAddress);
      imgElement.attr("style", "width:" + rawWidth + ";height:" + rawHeight);
      imgElement.removeAttr("data-caption");
      imgElement.removeAttr("data-size");
      imgElement.removeAttr("data-rawwidth");
      imgElement.removeAttr("data-rawheight");
      imgElement.removeAttr("data-default-watermark-src");
      imgElement.removeAttr("data-original");
      imgElement.removeAttr("data-actualsrc");
      this.downloadImg(imgElement);
    }
    return element;
  }

  private Element downloadImg(Element element){
    ImgDownloader imgDownloader = new ImgDownloader();
    return imgDownloader.translate(element);
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
    int endIndex = this.address.indexOf("/", DOMAIN.length());
    String mode = this.address.substring(DOMAIN.length(), endIndex);

    if ("question".equals(mode)) {
      if (this.address.contains("answer")) {
        Element answerElement = this.getOneAnswer(); // 单个回答链接
        answerElement = this.translate(answerElement);
        this.content = answerElement.html();
        this.title = this.getQuestionTitle();
      } else {
        this.getAnswerList(); // 问题链接
      }
    } else if ("p".equals(mode)) { // 文章链接
      this.getArticle();
    }
  }

}
