package io.midway.core;

import io.restassured.http.ContentType;

public interface Constantes {

    String APP_BASE_URL = "https://api.thecatapi.com/";
    String X_API_KEY= "live_WzeiAvPQ6IgGdLK0rxDzb8AfQU2Lqs7Tc2FEzdBUOHVdm9sEDxXaMeE7ce4wnVs2";
    ContentType APP_CONTENT_TYPE = ContentType.JSON;

    Long MAX_TIMEOUT = 20000L;

}
