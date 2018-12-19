package com.makimenko.mem.server.api;

import com.makimenko.mem.server.api.*;
import com.makimenko.mem.server.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.File;
import com.makimenko.mem.server.model.UploadLocation;

import java.util.List;
import com.makimenko.mem.server.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public abstract class UploadApiService {
    public abstract Response uploadPost(InputStream fileInputStream, FormDataContentDisposition fileDetail,SecurityContext securityContext) throws NotFoundException;
}
