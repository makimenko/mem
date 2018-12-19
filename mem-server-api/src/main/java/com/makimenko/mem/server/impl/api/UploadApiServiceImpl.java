package com.makimenko.mem.server.api.impl;

import com.makimenko.mem.server.api.*;
import com.makimenko.mem.server.model.*;

import java.io.File;
import com.makimenko.mem.server.model.UploadLocation;

import java.util.List;
import com.makimenko.mem.server.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public class UploadApiServiceImpl extends UploadApiService {
    @Override
    public Response uploadPost(InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
