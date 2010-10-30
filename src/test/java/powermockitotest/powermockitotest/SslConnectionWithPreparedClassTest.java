package powermockitotest.powermockitotest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Unit test for simple App.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ClassWithFinalMethods.class})
public class SslConnectionWithPreparedClassTest 
{
    /**
     * Rigourous Test :-)
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @Test
    public void testApp() throws ClientProtocolException, IOException    
    {
        final HttpClient client = new DefaultHttpClient();
        final HttpGet get = new HttpGet("https://www.gmx.net/");
        client.execute(get);
    }
}
