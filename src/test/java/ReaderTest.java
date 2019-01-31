import beans.Setting;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import util.Reader;

public class ReaderTest {

  @Test
  public void testGetSetting(){
    // When
    String content = Reader.readFile("setting.json");
    // Then
    System.out.println(content);
    Assert.assertNotNull(content);
  }

}
