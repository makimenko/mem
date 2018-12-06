package com.makimenko.mem.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.makimenko.mem.server.model.Question;
import com.makimenko.mem.server.model.UploadLocation;
import com.makimenko.mem.server.model.VisualContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GameStep
 */
@Validated

public class GameStep   {
  @JsonProperty("uuid")
  private String uuid = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("media")
  private UploadLocation media = null;

  @JsonProperty("inputQuestion")
  private Question inputQuestion = null;

  @JsonProperty("outputAnswer")
  private Question outputAnswer = null;

  public GameStep uuid(String uuid) {
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

  public GameStep name(String name) {
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

  public GameStep media(UploadLocation media) {
    this.media = media;
    return this;
  }

  /**
   * Get media
   * @return media
  **/
  @ApiModelProperty(value = "")

  @Valid

  public UploadLocation getMedia() {
    return media;
  }

  public void setMedia(UploadLocation media) {
    this.media = media;
  }

  public GameStep inputQuestion(Question inputQuestion) {
    this.inputQuestion = inputQuestion;
    return this;
  }

  /**
   * Get inputQuestion
   * @return inputQuestion
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Question getInputQuestion() {
    return inputQuestion;
  }

  public void setInputQuestion(Question inputQuestion) {
    this.inputQuestion = inputQuestion;
  }

  public GameStep outputAnswer(Question outputAnswer) {
    this.outputAnswer = outputAnswer;
    return this;
  }

  /**
   * Get outputAnswer
   * @return outputAnswer
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Question getOutputAnswer() {
    return outputAnswer;
  }

  public void setOutputAnswer(Question outputAnswer) {
    this.outputAnswer = outputAnswer;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GameStep gameStep = (GameStep) o;
    return Objects.equals(this.uuid, gameStep.uuid) &&
        Objects.equals(this.name, gameStep.name) &&
        Objects.equals(this.media, gameStep.media) &&
        Objects.equals(this.inputQuestion, gameStep.inputQuestion) &&
        Objects.equals(this.outputAnswer, gameStep.outputAnswer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, media, inputQuestion, outputAnswer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GameStep {\n");
    
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    media: ").append(toIndentedString(media)).append("\n");
    sb.append("    inputQuestion: ").append(toIndentedString(inputQuestion)).append("\n");
    sb.append("    outputAnswer: ").append(toIndentedString(outputAnswer)).append("\n");
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

