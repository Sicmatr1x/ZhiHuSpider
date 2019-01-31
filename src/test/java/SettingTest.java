import beans.Setting;
import org.junit.Assert;
import org.junit.Test;

public class SettingTest {
  @Test
  public void testGgetSetting(){
    Setting setting = Setting.getSetting();
    Assert.assertNotNull(setting);
  }
}
