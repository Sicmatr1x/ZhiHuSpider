package util;

import beans.IssueComment;
import java.io.IOException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonParseException;

public class JsonCovertoner {

  private ObjectMapper objectMapper = new ObjectMapper();

  public IssueComment[] getIssueCommentList(String json){
    try {
      IssueComment[] issueCommentList = objectMapper.readValue(json, IssueComment[].class);
      return issueCommentList;
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
