package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/**
 * Created by rezo on 6/9/16.
 */
public class PingServiceTest  extends JerseyTest{

    @Override
    protected Application configure()
    {
        return new ResourceConfig(PingService.class);

    }

    @Test
    public void TestPingShouldReturn200() throws Exception {

        Response ping = target("ping")
                .request().get();


        int status = ping.getStatus();
        assertEquals(200, status);
    }


}
