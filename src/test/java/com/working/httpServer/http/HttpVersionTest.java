package com.working.httpServer.http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HttpVersionTest {

    @Test
    void testGetBestCompatibleVersion_ExactMatch() throws Exception {
        HttpVersion version = null;
        try {
             version = HttpVersion.getBestCompatibleVersion("HTTP/1.1");
        } catch (Exception e) {
            fail("Exception should not have been thrown: ");
        }

        assertNotNull(version);
        assertEquals(HttpVersion.HTTP_1_1, version);
    }

    @Test
    void testGetBestCompatibleVersion_HigherMinorReturnsBestCompatible() throws Exception {
        // HTTP/1.2 is not defined, so should return HTTP/1.1 as best compatible
        HttpVersion version = HttpVersion.getBestCompatibleVersion("HTTP/1.2");
        assertEquals(HttpVersion.HTTP_1_1, version);
    }

    @Test
    void testGetBestCompatibleVersion_LowercaseHttp() throws Exception {
        try {
            HttpVersion.getBestCompatibleVersion("http/1.1");
            fail("Exception should have been thrown for lowercase 'http'");
        } catch (BadHttpVersionException e) {
            // expected
        } catch (Exception e) {
            fail("Unexpected exception type: " + e);
        }
    }

    @Test
    void testGetBestCompatibleVersion_UnknownMajorReturnsNull() throws Exception {
        // HTTP/2.0 is not defined, so should return null
        HttpVersion version = HttpVersion.getBestCompatibleVersion("HTTP/2.0");
        assertNull(version);
    }

    @Test
    void testGetBestCompatibleVersion_InvalidFormatThrowsBadHttpVersionException() {
        assertThrows(BadHttpVersionException.class, () -> {
            HttpVersion.getBestCompatibleVersion("HTP/1.1");
        });
        assertThrows(BadHttpVersionException.class, () -> {
            HttpVersion.getBestCompatibleVersion("HTTP/1");
        });
        assertThrows(BadHttpVersionException.class, () -> {
            HttpVersion.getBestCompatibleVersion("");
        });
        assertThrows(BadHttpVersionException.class, () -> {
            HttpVersion.getBestCompatibleVersion("HTTP/one.one");
        });
    }

    @Test
    void testGetBestCompatibleVersion_NullInputThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            HttpVersion.getBestCompatibleVersion(null);
        });
    }
}
