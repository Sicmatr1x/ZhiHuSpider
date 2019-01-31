package util;

import beans.Setting;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

public class Reader {

  public static String readFile(String path) {
    File file = new File(path);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String tempString = null;
      StringBuilder stringBuilder = new StringBuilder();
      while ((tempString = reader.readLine()) != null) {
        stringBuilder.append(tempString);
      }
      reader.close();
      return stringBuilder.toString();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
        }
      }
    }
    return null;
  }

}
