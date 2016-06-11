import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.SchedulerStatus;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.Day;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.Schedule;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by GM on 6/10/2016.
 */
public class ScheduleCorrectnessTest extends JerseyTest {

    @Override
    protected Application configure()
    {
        return new ResourceConfig(SchedulerStatus.class);
    }

    @Test
    public void TestSchedule() throws Exception {
        Schedule result =
                target("schedule")
                        .request()
                        .get(Schedule.class);
        assertEquals(4, (int)result.getStartMonth());
        assertEquals(10, (int)result.getEndMonth());
    }

    @Test
    public void TestSchedule2() throws Exception {
        Schedule mySchedule = Schedule.getInstance();
        mySchedule.setAfterSunRise(3.0);
        mySchedule.setBeforeSunSet(2.0);
        mySchedule.setEndMonth(7);
        mySchedule.setStartMonth(1);
        Schedule result =
                target("schedule")
                        .request()
                        .get(Schedule.class);
        assertEquals(1, (int)result.getStartMonth());
        assertEquals(7, (int)result.getEndMonth());
        assertEquals(3.0, result.getAfterSunRise(), 0.000001);
        assertEquals(2.0, result.getBeforeSunSet(), 0.000001);
    }


    @Test
    public void TestSchedule3() throws Exception {
        Schedule mySchedule = Schedule.getInstance();
        mySchedule.addExcluded(new Day(28,8,1995));
        Schedule result =
                target("schedule")
                        .request()
                        .get(Schedule.class);
        assertEquals((new Day(28,8,1995)).getDay(), result.getExcluded().get(0).getDay());
        assertEquals((new Day(28,8,1995)).getMonth(), result.getExcluded().get(0).getMonth());
        assertEquals((new Day(28,8,1995)).getYear(), result.getExcluded().get(0).getYear());
    }


    @Test
    public void TestSchedule4() throws Exception {
        Schedule mySchedule = Schedule.getInstance();
        mySchedule.setExcluded(new ArrayList<Day>());
        for (int i=0; i<5; i++){
            mySchedule.addExcluded(new Day(i,i,2000+i));
        }
        Schedule result =
                target("schedule")
                        .request()
                        .get(Schedule.class);
        for (int i=0; i<5; i++){
            assertEquals((new Day(i,i,2000+i)).getDay(), result.getExcluded().get(i).getDay());
            assertEquals((new Day(i,i,2000+i)).getMonth(), result.getExcluded().get(i).getMonth());
            assertEquals((new Day(i,i,2000+i)).getYear(), result.getExcluded().get(i).getYear());
        }
    }

    @Test
    public void TestSchedule5() throws Exception {
        Schedule mySchedule = Schedule.getInstance();
        List<Day> list = new ArrayList<>();
        for (int i=0; i<5; i++){
            list.add(new Day(i,i,2000+i));
        }
        mySchedule.setExcluded(list);
        Schedule result =
                target("schedule")
                        .request()
                        .get(Schedule.class);
        for (int i=0; i<5; i++){
            assertEquals((new Day(i,i,2000+i)).getDay(), result.getExcluded().get(i).getDay());
            assertEquals((new Day(i,i,2000+i)).getMonth(), result.getExcluded().get(i).getMonth());
            assertEquals((new Day(i,i,2000+i)).getYear(), result.getExcluded().get(i).getYear());
        }
    }

}
