package spider.util;

import org.jsoup.nodes.Element;

public class ZhihuImgTranslator implements Translator{

  @Override
  public Element translate(Element imgElement) {
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
    return imgElement;
  }
}
