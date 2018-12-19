package com.makimenko.mem.server.api.factories;

import com.makimenko.mem.server.api.EventApiService;
import com.makimenko.mem.server.api.impl.EventApiServiceImpl;


public class EventApiServiceFactory {
    private final static EventApiService service = new EventApiServiceImpl();

    public static EventApiService getEventApi() {
        return service;
    }
}
