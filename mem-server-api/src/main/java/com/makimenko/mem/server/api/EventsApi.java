package com.makimenko.mem.server.api;

import com.makimenko.mem.server.model.*;
import com.makimenko.mem.server.api.EventsApiService;
import com.makimenko.mem.server.api.factories.EventsApiServiceFactory;

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

@Path("/events")


@io.swagger.annotations.Api(description = "the events API")

public class EventsApi  {
   private final EventsApiService delegate;

   public EventsApi(@Context ServletConfig servletContext) {
      EventsApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("EventsApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (EventsApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = EventsApiServiceFactory.getEventsApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/find")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds events and return base information", notes = "Finds events and return base information", response = Event.class, responseContainer = "List", tags={ "question", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Event.class, responseContainer = "List") })
    public Response eventsFindGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.eventsFindGet(securityContext);
    }
}
