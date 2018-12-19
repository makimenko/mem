package com.makimenko.mem.server.api.factories;

import com.makimenko.mem.server.api.EventsApiService;
import com.makimenko.mem.server.api.impl.EventsApiServiceImpl;


public class EventsApiServiceFactory {
    private final static EventsApiService service = new EventsApiServiceImpl();

    public static EventsApiService getEventsApi() {
        return service;
    }
}
