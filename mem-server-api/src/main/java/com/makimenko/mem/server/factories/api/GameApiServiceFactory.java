package com.makimenko.mem.server.api.factories;

import com.makimenko.mem.server.api.GameApiService;
import com.makimenko.mem.server.api.impl.GameApiServiceImpl;


public class GameApiServiceFactory {
    private final static GameApiService service = new GameApiServiceImpl();

    public static GameApiService getGameApi() {
        return service;
    }
}
