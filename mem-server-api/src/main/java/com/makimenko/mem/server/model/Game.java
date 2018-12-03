package com.makimenko.mem.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.makimenko.mem.server.model.BaseEntity;
import com.makimenko.mem.server.model.GameStep;
import com.makimenko.mem.server.model.GameType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Game
 */
@Validated

public class Game   {
  @JsonProperty("uuid")
  private String uuid = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("gameType")
  private GameType gameType = null;

  @JsonProperty("gameSteps")
  @Valid
  private List<GameStep> gameSteps = null;

  public Game uuid(String uuid) {
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

  public Game name(String name) {
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

  public Game gameType(GameType gameType) {
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

  public Game gameSteps(List<GameStep> gameSteps) {
    this.gameSteps = gameSteps;
    return this;
  }

  public Game addGameStepsItem(GameStep gameStepsItem) {
    if (this.gameSteps == null) {
      this.gameSteps = new ArrayList<GameStep>();
    }
    this.gameSteps.add(gameStepsItem);
    return this;
  }

  /**
   * Get gameSteps
   * @return gameSteps
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<GameStep> getGameSteps() {
    return gameSteps;
  }

  public void setGameSteps(List<GameStep> gameSteps) {
    this.gameSteps = gameSteps;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Game game = (Game) o;
    return Objects.equals(this.uuid, game.uuid) &&
        Objects.equals(this.name, game.name) &&
        Objects.equals(this.gameType, game.gameType) &&
        Objects.equals(this.gameSteps, game.gameSteps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, gameType, gameSteps);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Game {\n");
    
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    gameType: ").append(toIndentedString(gameType)).append("\n");
    sb.append("    gameSteps: ").append(toIndentedString(gameSteps)).append("\n");
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

