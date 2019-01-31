package spider.util;


import org.jsoup.nodes.Element;

public interface Translator {
  Element translate(Element element);
}
