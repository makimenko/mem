/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.makimenko.mem.server.api;

import org.springframework.core.io.Resource;
import com.makimenko.mem.server.model.UploadLocation;
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

@Api(value = "upload", description = "the upload API")
public interface UploadApi {

    @ApiOperation(value = "", nickname = "uploadPost", notes = "Upload image file to server", response = UploadLocation.class, tags={ "upload", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns file location on server", response = UploadLocation.class) })
    @RequestMapping(value = "/upload",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    ResponseEntity<UploadLocation> uploadPost(@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file);

}