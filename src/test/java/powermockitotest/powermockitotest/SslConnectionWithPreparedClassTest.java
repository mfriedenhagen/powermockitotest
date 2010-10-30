package powermockitotest.powermockitotest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Unit test for simple App.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ClassWithFinalMethods.class })
public class SslConnectionWithPreparedClassTest {

    @Test
    public void testMock() {
        final ClassWithFinalMethods mock = PowerMockito.mock(ClassWithFinalMethods.class);
        PowerMockito.doReturn("Hullo").when(mock).getString();
        assertEquals("Hullo", mock.getString());
    }

    /**
     * Rigourous Test :-)
     * 
     * @throws IOException
     * @throws ClientProtocolException
     */
    @Test
    public void testSslConnection() throws ClientProtocolException, IOException {
        final HttpClient client = new DefaultHttpClient();
        final HttpGet get = new HttpGet("https://www.gmx.net/");
        final String html = client.execute(get, new BasicResponseHandler());
        System.out.println(html);
    }
}
