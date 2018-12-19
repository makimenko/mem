package com.makimenko.mem.server.api;

import com.makimenko.mem.server.model.*;
import com.makimenko.mem.server.api.GameApiService;
import com.makimenko.mem.server.api.factories.GameApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.makimenko.mem.server.model.Game;
import com.makimenko.mem.server.model.GameOption;

import java.util.Map;
import java.util.List;
import com.makimenko.mem.server.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/game")


@io.swagger.annotations.Api(description = "the game API")

public class GameApi  {
   private final GameApiService delegate;

   public GameApi(@Context ServletConfig servletContext) {
      GameApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("GameApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (GameApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = GameApiServiceFactory.getGameApi();
      }

      this.delegate = delegate;
   }

    @POST
    @Path("/new")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Return new game details", notes = "Return new game details", response = Game.class, tags={ "game", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "New game content", response = Game.class) })
    public Response gameNewPost(@ApiParam(value = "Game Options" ,required=true) GameOption gameOption
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.gameNewPost(gameOption,securityContext);
    }
}
