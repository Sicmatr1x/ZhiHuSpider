package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {
  public static void writeFile(String content, String path) throws IOException {
    System.out.println("[Writer]:writeFile:" + path);
    File file = new File(path);
    if (!file.exists()) {
      file.createNewFile();
    }
    FileOutputStream out = new FileOutputStream(file, true);
    out.write(content.getBytes("utf-8"));
    out.close();
  }
}
