package com.makimenko.mem.server.api;

import com.makimenko.mem.server.model.*;
import com.makimenko.mem.server.api.EventApiService;
import com.makimenko.mem.server.api.factories.EventApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.makimenko.mem.server.model.Event;

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

@Path("/event")


@io.swagger.annotations.Api(description = "the event API")

public class EventApi  {
   private final EventApiService delegate;

   public EventApi(@Context ServletConfig servletContext) {
      EventApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("EventApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (EventApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = EventApiServiceFactory.getEventApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Creates a new event", response = Event.class, tags={ "question", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "event response", response = Event.class) })
    public Response eventPost(@ApiParam(value = "Event to add to the database" ,required=true) Event event
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.eventPost(event,securityContext);
    }
    @DELETE
    @Path("/{uuid}")
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Deletes a new event", response = Void.class, tags={ "question", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "event response", response = Void.class) })
    public Response eventUuidDelete(@ApiParam(value = "Event UUID for deletion from the database",required=true) @PathParam("uuid") String uuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.eventUuidDelete(uuid,securityContext);
    }
    @GET
    @Path("/{uuid}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Return event detals", notes = "Return event detals", response = Event.class, tags={ "question", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Event.class) })
    public Response eventUuidGet(@ApiParam(value = "Unique identifier of Event",required=true) @PathParam("uuid") String uuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.eventUuidGet(uuid,securityContext);
    }
}
