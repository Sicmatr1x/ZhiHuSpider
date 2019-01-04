import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlUtil {

  public HtmlUtil(String address) {
    this.address = address;
  }

  private String address;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  private Document doc;

  public Document getDoc() {
    return doc;
  }

  public void getHtml() throws IOException {
    this.doc = Jsoup.connect(this.address).get();
  }

}
