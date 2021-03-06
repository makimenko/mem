package com.makimenko.mem.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.makimenko.mem.server.model.BaseEntity;
import com.makimenko.mem.server.model.Question;
import com.makimenko.mem.server.model.VisualContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Group
 */
@Validated

public class Group   {
  @JsonProperty("uuid")
  private String uuid = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("visualContents")
  @Valid
  private List<VisualContent> visualContents = null;

  @JsonProperty("questions")
  @Valid
  private List<Question> questions = null;

  public Group uuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  /**
   * Get uuid
   * @return uuid
  **/
  @ApiModelProperty(value = "")


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Group name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Group visualContents(List<VisualContent> visualContents) {
    this.visualContents = visualContents;
    return this;
  }

  public Group addVisualContentsItem(VisualContent visualContentsItem) {
    if (this.visualContents == null) {
      this.visualContents = new ArrayList<VisualContent>();
    }
    this.visualContents.add(visualContentsItem);
    return this;
  }

  /**
   * Get visualContents
   * @return visualContents
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<VisualContent> getVisualContents() {
    return visualContents;
  }

  public void setVisualContents(List<VisualContent> visualContents) {
    this.visualContents = visualContents;
  }

  public Group questions(List<Question> questions) {
    this.questions = questions;
    return this;
  }

  public Group addQuestionsItem(Question questionsItem) {
    if (this.questions == null) {
      this.questions = new ArrayList<Question>();
    }
    this.questions.add(questionsItem);
    return this;
  }

  /**
   * Get questions
   * @return questions
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Group group = (Group) o;
    return Objects.equals(this.uuid, group.uuid) &&
        Objects.equals(this.name, group.name) &&
        Objects.equals(this.visualContents, group.visualContents) &&
        Objects.equals(this.questions, group.questions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, visualContents, questions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Group {\n");
    
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    visualContents: ").append(toIndentedString(visualContents)).append("\n");
    sb.append("    questions: ").append(toIndentedString(questions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

