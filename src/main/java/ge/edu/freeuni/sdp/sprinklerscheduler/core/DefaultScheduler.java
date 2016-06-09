package ge.edu.freeuni.sdp.sprinklerscheduler.core;

/**
 * Created by GM on 6/9/2016.
 */

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class DefaultScheduler {

    @GET
    public Response get() {
        return Response.ok().build();
    }

}