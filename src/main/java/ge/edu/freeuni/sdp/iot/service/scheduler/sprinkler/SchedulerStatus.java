package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler;

import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.Schedule;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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


    @Path("/")
    @GET
    public Response root() {
        return Response.ok().build();
    }


    /**
     * @return current Schedule
     */
    @Path("/schedule")
    @GET
    public Schedule schedule() {
        return Schedule.getInstance();
    }


    /**
     * @param schedule new schedule for scheduler
     * @return response code 200 : schedule changed
     */
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/schedule")
    @POST
    public Response setSchedule(Schedule schedule) {
        Schedule schedule1 = Schedule.getInstance();
        schedule1.setEndMonth(schedule.getEndMonth());
        schedule1.setStartMonth(schedule.getStartMonth());
        schedule1.setExcluded(schedule.getExcluded());

        return Response.ok().build();
    }


    /**
     * @param day new excluded day
     * @return response code 200: excluded day added
     */
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/schedule/excluded")
    @POST
    public Response addExcluded(String day){
        Schedule.getInstance().addExcluded(day);
        return Response.ok().build();
    }

}
