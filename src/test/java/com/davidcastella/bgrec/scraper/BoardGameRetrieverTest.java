package com.davidcastella.bgrec.scraper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import com.davidcastella.bgrec.scraper.fixtures.BoardGameFixture;

import static org.junit.Assert.*;

public class BoardGameRetrieverTest {

    BoardGameRetriever bgr;

    @Before
    public void setUp() throws Exception {
        bgr = new BoardGameRetriever(new URL("http://boardgamegeek.com/xmlapi/boardgame/"), "13", "XML");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRetrieveSlashEndedUri() throws Exception {
        assertEquals(bgr.retrieve(), BoardGameFixture.getSettlersOfCatan());
    }

    @Test
    public void testRetrieveNonSlashEndedUri() throws Exception {
        bgr.setBaseUri(new URL("http://boardgamegeek.com/xmlapi/boardgame"));
        assertEquals(bgr.retrieve(), BoardGameFixture.getSettlersOfCatan());
    }
}