import org.junit.Assert;
import org.junit.Test;
import spider.util.FilenameChecker;

public class FilenameCheckerTest {

  @Test
  public void getLegalFileName(){
    // Given
    String filename = "「AlphaStar」在《星际争霸2》项目内部测试以10:0战绩获胜，线下直播赛以0:1落败？.html";
    String expected = "「AlphaStar」在《星际争霸2》项目内部测试以10_0战绩获胜，线下直播赛以0_1落败？.html";
    // When
    String result = FilenameChecker.getLegalFileName(filename);
    // Then
    Assert.assertEquals(expected, result);
  }

}
