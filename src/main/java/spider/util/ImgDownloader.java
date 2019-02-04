package spider.util;

import beans.Setting;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.nodes.Element;

public class ImgDownloader implements Translator{

  private static void downloadPicture(String urlList, String path) {
    URL url = null;
    try {
      File imgFile= new File(path);
      File mediaFolder = imgFile.getParentFile();
      if (!mediaFolder.exists()) {
        mediaFolder.mkdirs();
      }

      url = new URL(urlList);
      DataInputStream dataInputStream = new DataInputStream(url.openStream());

      FileOutputStream fileOutputStream = new FileOutputStream(imgFile);
      ByteArrayOutputStream output = new ByteArrayOutputStream();

      byte[] buffer = new byte[1024];
      int length;

      while ((length = dataInputStream.read(buffer)) > 0) {
        output.write(buffer, 0, length);
      }
      fileOutputStream.write(output.toByteArray());
      dataInputStream.close();
      fileOutputStream.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   *
   * @param url img url like: https://pic3.zhimg.com/v2-9e02be59739818c9029d028b758d5c9a_r.jpg
   * @return
   */
  private String getImgName(String url){
    String[] work = url.split("/");
    return work[work.length - 1];
  }

  @Override
  public Element translate(Element element) {
    String srcAddress = element.attr("src");
    String imgName = this.getImgName(srcAddress);
    String imgRelativePath = "media/" + imgName;
    downloadPicture(srcAddress, Setting.getSetting().getDownloadPath() + "/" + imgRelativePath);
    element.attr("src", imgRelativePath);
    element.attr("alt", srcAddress);
    return element;
  }
}
