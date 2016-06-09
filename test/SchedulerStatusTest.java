import ge.edu.freeuni.sdp.sprinklerscheduler.core.SchedulerStatus;
import ge.edu.freeuni.sdp.sprinklerscheduler.core.Status;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;


import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;


/**
 * Created by rezo on 6/9/16.
 */
public class SchedulerStatusTest extends JerseyTest {

    @Override
    protected Application configure()
    {
        return new ResourceConfig(SchedulerStatus.class);
    }

    @Test
    public void TestGet() throws Exception {
        Status result =
                target("status")
                        .request()
                        .get(Status.class);

        System.out.println(result);
        assertEquals("true", result.getLastCommand());
    }

    @Test
    public void TestRootShouldReturn200() throws Exception {

        Response ping = target("/")
                .request().get();


        int status = ping.getStatus();
        assertEquals(200, status);
    }


}