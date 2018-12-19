package com.makimenko.mem.server.api.factories;

import com.makimenko.mem.server.api.UploadApiService;
import com.makimenko.mem.server.api.impl.UploadApiServiceImpl;


public class UploadApiServiceFactory {
    private final static UploadApiService service = new UploadApiServiceImpl();

    public static UploadApiService getUploadApi() {
        return service;
    }
}
