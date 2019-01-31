import java.io.IOException;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;
import spider.ZhihuHtmlUtil;

public class ZhihuHtmlUtilTest {

  @Test
  public void testGetHtml(){
    // Given
    String address = "https://www.zhihu.com/question/20584141/answer/82003627";
    ZhihuHtmlUtil htmlUtil = new ZhihuHtmlUtil();
    htmlUtil.setAddress(address);

    // When
    try {
      boolean isParseSuccess = htmlUtil.parse();
      System.out.println(htmlUtil.getDoc().title());
      System.out.println(htmlUtil.getDoc().body());

      // Then
      Assert.assertTrue(isParseSuccess);
      Assert.assertNotNull(htmlUtil.getDoc());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testGetOneAnswer(){
    // Given
    String address = "https://www.zhihu.com/question/20584141/answer/82003627";
    ZhihuHtmlUtil htmlUtil = new ZhihuHtmlUtil();
    htmlUtil.setAddress(address);

    // When
    try {
      boolean isParseSuccess = htmlUtil.parse();
      Element answer = htmlUtil.getOneAnswer();
      System.out.println(answer.html());

      // Then
      Assert.assertTrue(isParseSuccess);
      Assert.assertNotNull(answer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testGetQuestionTitle(){
    // Given
    String address = "https://www.zhihu.com/question/20584141/answer/82003627";
    ZhihuHtmlUtil htmlUtil = new ZhihuHtmlUtil();
    htmlUtil.setAddress(address);

    // When
    try {
      boolean isParseSuccess = htmlUtil.parse();
      String title = htmlUtil.getQuestionTitle();
      System.out.println(title);

      // Then
      Assert.assertTrue(isParseSuccess);
      Assert.assertNotNull(title);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
