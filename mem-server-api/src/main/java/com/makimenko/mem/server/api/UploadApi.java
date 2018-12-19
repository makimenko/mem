package com.makimenko.mem.server.api;

import com.makimenko.mem.server.model.*;
import com.makimenko.mem.server.api.UploadApiService;
import com.makimenko.mem.server.api.factories.UploadApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import java.io.File;
import com.makimenko.mem.server.model.UploadLocation;

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

@Path("/upload")


@io.swagger.annotations.Api(description = "the upload API")

public class UploadApi  {
   private final UploadApiService delegate;

   public UploadApi(@Context ServletConfig servletContext) {
      UploadApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UploadApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UploadApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = UploadApiServiceFactory.getUploadApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Upload image file to server", response = UploadLocation.class, tags={ "upload", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Returns file location on server", response = UploadLocation.class) })
    public Response uploadPost(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.uploadPost(fileInputStream, fileDetail,securityContext);
    }
}
