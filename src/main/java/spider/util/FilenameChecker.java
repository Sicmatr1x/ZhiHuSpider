package spider.util;

public class FilenameChecker {

  public static String getLegalFileName(String filename){
    return filename.replace("\\", "_")
        .replace("/", "_")
        .replace(":", "_")
        .replace("*", "_")
        .replace("?", "_")
        .replace("<", "_")
        .replace(">", "_")
        .replace("|", "_");
  }
}
