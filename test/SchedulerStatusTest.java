import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.SchedulerStatus;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.Status;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;


import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;


public class SchedulerStatusTest extends JerseyTest {

    @Override
    protected Application configure()
    {
        return new ResourceConfig(SchedulerStatus.class);
    }

    @Test
    public void TestRootShouldReturn200() throws Exception {

        Response ping = target("/")
                .request().get();


        int status = ping.getStatus();
        assertEquals(200, status);
    }


}