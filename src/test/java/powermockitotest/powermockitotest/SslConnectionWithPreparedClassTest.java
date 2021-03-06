package powermockitotest.powermockitotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Unit test for simple App.
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.*")
@PrepareForTest({ ClassWithFinalMethods.class })
public class SslConnectionWithPreparedClassTest {

    /**
     * 
     */
    private static final String HTTPS_WWW_GMX_NET = "https://www.gmx.net/";

    /**
     * This test will blow if not @PrepareForTest is given, as {@link ClassWithFinalMethods#getString()} is final.
     */
    @Test
    public void testMock() {
        final ClassWithFinalMethods mock = PowerMockito.mock(ClassWithFinalMethods.class);
        PowerMockito.doReturn("Hullo").when(mock).getString();
        assertEquals("Hullo", mock.getString());
    }

    /**
     * This test will blow if @PrepareForTest is given.
     * 
     * @throws IOException
     * @throws ClientProtocolException
     */
    @Test
    public void testSslConnection() throws ClientProtocolException, IOException {
        final HttpClient client = new DefaultHttpClient();
        final HttpGet get = new HttpGet(HTTPS_WWW_GMX_NET);
        final String html = client.execute(get, new BasicResponseHandler());
        containsTitle(html);
    }

    /**
     * @param html
     */
    void containsTitle(final String html) {
        assertThat(
                html,
                containsString("<title>GMX - E-Mail, FreeMail, De-Mail, Themen- &amp; Shopping-Portal - kostenlos</title>"));
    }

    /**
     * This will work, strange.
     * 
     * @throws IOException
     */
    @Test
    public void testURLSslConnection() throws IOException {
        final URL url = new URL(HTTPS_WWW_GMX_NET);
        final BufferedInputStream stream = new BufferedInputStream(url.openStream());
        try {
            containsTitle(IOUtils.toString(stream));
        } finally {
            stream.close();
        }
    }

}
