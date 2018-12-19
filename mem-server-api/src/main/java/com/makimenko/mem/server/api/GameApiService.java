package com.makimenko.mem.server.api;

import com.makimenko.mem.server.api.*;
import com.makimenko.mem.server.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.makimenko.mem.server.model.Game;
import com.makimenko.mem.server.model.GameOption;

import java.util.List;
import com.makimenko.mem.server.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public abstract class GameApiService {
    public abstract Response gameNewPost(GameOption gameOption,SecurityContext securityContext) throws NotFoundException;
}
