package com.davidcastella.bgrec.scraper;

import com.davidcastella.bgrec.scraper.exceptions.NonExistentPropertyException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.joox.Match;
import static org.joox.JOOX.*;
import org.w3c.dom.Document;

import javax.ws.rs.core.MediaType;
import javax.xml.ws.http.HTTPException;
import java.net.HttpRetryException;
import java.net.URL;
import java.util.List;

/**
 * Created by davidkaste on 19/11/14.
 */
public class BGGRetriever {
    private URL baseUri;
    private String gameUri;
    private Document document;

    BGGRetriever(URL baseUri, String gameUri) {
        setBaseUri(baseUri);
        setGameUri(gameUri);
        document = retrieve();
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

    public List<Match> query(String value) throws NonExistentPropertyException {
        Match x = $(document).find(value);
        if (x.each().isEmpty()) {
            throw new NonExistentPropertyException("The property " + value + " does not exist.");
        }
        return x.each();
    }

    private Document retrieve () throws HTTPException {
        WebResource resource;
        Client client = Client.create();
        ClientResponse response;

        resource = client.resource(checkBaseUri(getBaseUri().toString()) + getGameUri());
        response = resource.accept(MediaType.APPLICATION_XML_TYPE)
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new HTTPException(response.getStatus());
        }

        return response.getEntity(Document.class);
    }

    private String checkBaseUri (String uri) {
        if (uri.charAt(uri.length() - 1) != '/'){
            uri = uri + "/";
        }
        return uri;
    }
}
