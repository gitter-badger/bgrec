package com.davidcastella.bgrec.scraper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.net.URL;

/**
 * Created by davidkaste on 19/11/14.
 */
public class BoardGameRetriever {
    private URL baseUri;
    private String gameUri;
    private String format;

    BoardGameRetriever (URL baseUri, String gameUri, String format) {
        this.baseUri = baseUri;
        this.gameUri = gameUri;
        this.format = format;
    }

    BoardGameRetriever () {
        setBaseUri(null);
        setFormat(null);
        setGameUri(null);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getGameUri() {
        return gameUri;
    }

    public void setGameUri(String gameUri) {
        this.gameUri = gameUri;
    }

    public URL getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(URL baseUri) {
        this.baseUri = baseUri;
    }

    public String retrieve () {
        WebResource resource;
        Client client = Client.create();
        ClientResponse response = null;

        try {
            resource = client.resource(checkBaseUri(getBaseUri().toString()) + getGameUri());
            response = resource.accept(MediaType.APPLICATION_XML_TYPE)
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("ERROR");
            }

        } catch (Exception e) {
            //TODO
        }

        return response.getEntity(String.class);
    }

    private String checkBaseUri (String uri) {
        if (uri.charAt(uri.length() - 1) != '/'){
            uri = uri + "/";
        }
        return uri;
    }
}
