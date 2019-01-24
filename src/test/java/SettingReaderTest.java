import beans.Setting;
import org.junit.Assert;
import org.junit.Test;
import util.SettingReader;

public class SettingReaderTest {

  @Test
  public void testGetSetting(){
    // When
    Setting setting = SettingReader.getSetting();
    // Then
    System.out.println(setting);
    Assert.assertNotNull(setting);
  }
}
