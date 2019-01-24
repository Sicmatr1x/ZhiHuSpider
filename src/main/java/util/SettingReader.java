package util;

import beans.Setting;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

public class SettingReader {

  private static String SETTING_FILE_NAME = "setting.json";

  private static String readFile(){
    File file = new File(SETTING_FILE_NAME);
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

  public static Setting getSetting(){
    ObjectMapper objectMapper = new ObjectMapper();
    String json = readFile();
    System.out.println(json);
    try {
      Setting setting = objectMapper.readValue(json, Setting.class);
      return setting;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
