package com.davidcastella.bgrec.scraper;

import com.davidcastella.bgrec.scraper.exceptions.NonExistentPropertyException;
import org.joox.Match;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class BGGRetrieverTest {
    BGGRetriever bgr;

    @Before
    public void setUp() throws Exception {
        bgr = new BGGRetriever(new URL("http://boardgamegeek.com/xmlapi/boardgame"), "3076");
    }

    @Test
    public void testQuery() throws Exception {
        List<Match> x = bgr.query("name");
        assertEquals("Puerto Rico", x.get(0).xpath("//name[@primary = 'true']").content());
        assertEquals("2002", bgr.query("yearpublished").get(0).content());
    }

    @Test
    public void testQueryNonExistentPropertyException() {
        boolean thrown = false;
        try {
            bgr.query("nonexistent");
        } catch (NonExistentPropertyException nep) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}