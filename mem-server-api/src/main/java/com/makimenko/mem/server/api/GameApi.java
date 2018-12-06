/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.makimenko.mem.server.api;

import com.makimenko.mem.server.model.Game;
import com.makimenko.mem.server.model.GameOption;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Api(value = "game", description = "the game API")
public interface GameApi {

    @ApiOperation(value = "Return new game details", nickname = "gameNewPost", notes = "Return new game details", response = Game.class, tags={ "game", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "New game content", response = Game.class) })
    @RequestMapping(value = "/game/new",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Game> gameNewPost(@ApiParam(value = "Game Options" ,required=true )  @Valid @RequestBody GameOption gameOption);

}
