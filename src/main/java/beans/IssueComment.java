package beans;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueComment {

  private String url;
  private String html_url;
  private String issue_url;
  private int id;
  private String node_id;
  private Date created_at;
  private Date updated_at;
  private String author_association;
  private String body;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getHtml_url() {
    return html_url;
  }

  public void setHtml_url(String html_url) {
    this.html_url = html_url;
  }

  public String getIssue_url() {
    return issue_url;
  }

  public void setIssue_url(String issue_url) {
    this.issue_url = issue_url;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNode_id() {
    return node_id;
  }

  public void setNode_id(String node_id) {
    this.node_id = node_id;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  public String getAuthor_association() {
    return author_association;
  }

  public void setAuthor_association(String author_association) {
    this.author_association = author_association;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return "IssueComment{" +
        "url='" + url + '\'' +
        ", html_url='" + html_url + '\'' +
        ", issue_url='" + issue_url + '\'' +
        ", id=" + id +
        ", node_id='" + node_id + '\'' +
        ", created_at=" + created_at +
        ", updated_at=" + updated_at +
        ", author_association='" + author_association + '\'' +
        ", body='" + body + '\'' +
        '}';
  }
}
