package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler;

import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.SprinklerCommunicatorProxy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ping")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class PingService {

	@GET
	public Response get() {
		// Should NOT be here ! ! ! ! hack :D
		new SprinklerCommunicatorProxy().setStatus(true,"1");
		return Response.ok().build();
	}

}