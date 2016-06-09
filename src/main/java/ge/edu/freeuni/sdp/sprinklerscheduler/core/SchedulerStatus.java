package ge.edu.freeuni.sdp.sprinklerscheduler.core;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by GM on 6/9/2016.
 */

@Path("/")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class SchedulerStatus {

    @Path("/status")
    @GET
    public Status get() {
        return new Status("true");
    }

}
