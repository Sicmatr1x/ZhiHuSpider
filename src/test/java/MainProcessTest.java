import java.io.IOException;
import main.MainProcess;
import org.junit.Test;
import spider.HtmlUtil;
import spider.ZhihuZhuanlanHtmlUtil;

public class MainProcessTest {
  @Test
  public void testDownloadWebPage(){
    // Given
    MainProcess mainProcess = new MainProcess();
    mainProcess.initSetting();
    mainProcess.initHtmlUtilMap();
//    mainProcess.initCommandList();
    String address = "https://zhuanlan.zhihu.com/p/58820047?utm_source=ZHShareTargetIDMore&utm_medium=social&utm_oi=599879765319094272";
    HtmlUtil htmlUtil = mainProcess.getHtmlUtilMap().get(ZhihuZhuanlanHtmlUtil.DOMAIN);
    // When
    try {
      mainProcess.downloadWebPage(address, htmlUtil);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
