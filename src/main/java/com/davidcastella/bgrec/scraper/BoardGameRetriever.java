package com.davidcastella.bgrec.scraper;

import java.net.URL;

/**
 * Created by davidkaste on 19/11/14.
 */
public abstract class BoardGameRetriever {
    private URL baseUri;
    private String gameUri;

    BoardGameRetriever(URL baseUri, String gameUri) {
        this.baseUri = baseUri;
        this.gameUri = gameUri;
    }
}
