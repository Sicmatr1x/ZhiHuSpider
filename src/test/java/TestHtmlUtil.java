import java.io.IOException;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;

public class TestHtmlUtil {

  @Test
  public void testGetHtml(){
    // Given
    String address = "https://www.zhihu.com/question/20584141/answer/82003627";
    HtmlUtil htmlUtil = new HtmlUtil(address);

    // When
    try {
      htmlUtil.getHtml();
      System.out.println(htmlUtil.getDoc().title());
      System.out.println(htmlUtil.getDoc().body());
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Then
    Assert.assertNotNull(htmlUtil.getDoc());
  }

  @Test
  public void testGetOneAnswer(){
    // Given
    String address = "https://www.zhihu.com/question/20584141/answer/82003627";
    HtmlUtil htmlUtil = new HtmlUtil(address);

    // When
    try {
      htmlUtil.getHtml();
      Element answer = htmlUtil.getOneAnswer();
      System.out.println(answer.html());

      // Then
      Assert.assertNotNull(answer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
