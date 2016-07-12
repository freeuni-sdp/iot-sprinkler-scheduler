package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler;

import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.data.Schedule;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by GM on 6/9/2016.
 */

@Path("/")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class SchedulerService {

    @Path("/status")
    @GET
    public Status get() {
        return new Status("true");
    }


    @Path("/")
    @GET
    public Response root() {
        return Response.ok().build();
    }


    /**
     * @return current Schedule
     */
    @Path("/houses/{house_id}/schedule")
    @GET
    public Schedule schedule(@PathParam("house_id") String houseID) {
        return Utility.getInstance().getHouseScheduleByID(houseID);
    }


    /**
     * @param schedule new schedule for scheduler
     * @return response code 200 : schedule changed
     */
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/houses/{house_id}/schedule/")
    @POST
    public Response setSchedule(@PathParam("house_id") String houseId, Schedule schedule) {
        Utility utility = Utility.getInstance();

        utility.setNewScheduleForHouse(schedule, houseId);


        return Response.ok().build();
    }


    /**
     * @param day new excluded day
     * @return response code 200: excluded day added
     */
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/houses/{house_id}/schedule/excluded")
    @POST
    public Response addExcluded(@PathParam("house_id") String houseId, String day){
        Utility utility = Utility.getInstance();
        utility.getHouseScheduleByID(houseId).addExcluded(day);
        return Response.ok().build();
    }

}
