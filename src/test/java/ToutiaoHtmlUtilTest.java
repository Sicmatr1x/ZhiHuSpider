import java.io.IOException;
import org.junit.Test;
import spider.ToutiaoHtmlUtil;

public class ToutiaoHtmlUtilTest {
  @Test
  public void testGetHtml(){
    ToutiaoHtmlUtil toutiaoHtmlUtil = new ToutiaoHtmlUtil();
    toutiaoHtmlUtil.setAddress("https://www.toutiao.com/i6650295424118686211/");
    try {
      toutiaoHtmlUtil.parse();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(toutiaoHtmlUtil.getTitle());
    System.out.println(toutiaoHtmlUtil.getContent());
  }
}
