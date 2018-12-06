package com.makimenko.mem.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.makimenko.mem.server.model.GameType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GameOption
 */
@Validated

public class GameOption   {
  @JsonProperty("gameType")
  private GameType gameType = null;

  @JsonProperty("maxQuestions")
  private Integer maxQuestions = null;

  @JsonProperty("maxOptions")
  private Integer maxOptions = null;

  public GameOption gameType(GameType gameType) {
    this.gameType = gameType;
    return this;
  }

  /**
   * Get gameType
   * @return gameType
  **/
  @ApiModelProperty(value = "")

  @Valid

  public GameType getGameType() {
    return gameType;
  }

  public void setGameType(GameType gameType) {
    this.gameType = gameType;
  }

  public GameOption maxQuestions(Integer maxQuestions) {
    this.maxQuestions = maxQuestions;
    return this;
  }

  /**
   * Get maxQuestions
   * @return maxQuestions
  **/
  @ApiModelProperty(value = "")


  public Integer getMaxQuestions() {
    return maxQuestions;
  }

  public void setMaxQuestions(Integer maxQuestions) {
    this.maxQuestions = maxQuestions;
  }

  public GameOption maxOptions(Integer maxOptions) {
    this.maxOptions = maxOptions;
    return this;
  }

  /**
   * Get maxOptions
   * @return maxOptions
  **/
  @ApiModelProperty(value = "")


  public Integer getMaxOptions() {
    return maxOptions;
  }

  public void setMaxOptions(Integer maxOptions) {
    this.maxOptions = maxOptions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GameOption gameOption = (GameOption) o;
    return Objects.equals(this.gameType, gameOption.gameType) &&
        Objects.equals(this.maxQuestions, gameOption.maxQuestions) &&
        Objects.equals(this.maxOptions, gameOption.maxOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gameType, maxQuestions, maxOptions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GameOption {\n");
    
    sb.append("    gameType: ").append(toIndentedString(gameType)).append("\n");
    sb.append("    maxQuestions: ").append(toIndentedString(maxQuestions)).append("\n");
    sb.append("    maxOptions: ").append(toIndentedString(maxOptions)).append("\n");
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

