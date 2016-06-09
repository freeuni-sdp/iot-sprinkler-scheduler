package ge.edu.freeuni.sdp.sprinklerscheduler.core;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ping")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class PingService {

	@GET
	public Response get() {
		return Response.ok().build();
	}

}