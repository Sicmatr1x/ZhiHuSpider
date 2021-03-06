import java.io.IOException;
import main.MainProcess;
import org.junit.Test;
import spider.HtmlUtil;
import spider.ZhihuHtmlUtil;
import spider.ZhihuZhuanlanHtmlUtil;

public class MainProcessTest {
  @Test
  public void testDownloadWebPage(){
    // Given
    MainProcess mainProcess = new MainProcess();
    mainProcess.initSetting();
    mainProcess.initHtmlUtilMap();
//    mainProcess.initCommandList();
    String address = "https://zhuanlan.zhihu.com/p/59012054";
    HtmlUtil htmlUtil = mainProcess.getHtmlUtilMap().get(ZhihuZhuanlanHtmlUtil.DOMAIN);
    // When
    try {
      mainProcess.downloadWebPage(address, htmlUtil);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDownloadWebPageList(){
    // Given
    MainProcess mainProcess = new MainProcess();
    mainProcess.initSetting();
    mainProcess.initHtmlUtilMap();
//    mainProcess.initCommandList();
    String[] addressList = {
        "https://www.zhihu.com/question/37245305/answer/594992846",
        "https://www.zhihu.com/question/20134551/answer/628918032"
    };
    for(int i = 0; i < addressList.length; i++){
      HtmlUtil htmlUtil = mainProcess.getHtmlUtilMap().get(ZhihuHtmlUtil.DOMAIN);
      // When
      try {
        mainProcess.downloadWebPage(addressList[i], htmlUtil);
        System.out.println("sleep 60s, finish:" +(i+1) + "/" + addressList.length);
        Thread.sleep(60 * 1000);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testDownloadWebPageList1(){
    // Given
    MainProcess mainProcess = new MainProcess();
    mainProcess.initSetting();
    mainProcess.initHtmlUtilMap();
//    mainProcess.initCommandList();
    String[] addressList = {
        "https://www.zhihu.com/question/308945100/answer/619799811",
        "https://www.zhihu.com/question/276648130/answer/619826613"
    };
    for(int i = 0; i < addressList.length; i++){
      HtmlUtil htmlUtil = mainProcess.getHtmlUtilMap().get(ZhihuHtmlUtil.DOMAIN);
      // When
      try {
        mainProcess.downloadWebPage(addressList[i], htmlUtil);
        System.out.println("sleep 60s, finish:" +(i+1) + "/" + addressList.length);
        Thread.sleep(60 * 1000);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
