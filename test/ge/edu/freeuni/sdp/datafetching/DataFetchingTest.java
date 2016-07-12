package ge.edu.freeuni.sdp.datafetching;
import org.mockito.MockitoAnnotations;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.Utility;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.data.Schedule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * Created by GM on 6/24/2016.
 */
public class DataFetchingTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Utility utility = Utility.getInstance();
        utility.init();
    }

    @Test
    public void testHouseLocationFetching() throws IOException {
        Utility utility = Utility.getInstance();
        for (String house: utility.houseIDAndLocations.keySet()){
            assertEquals(true,utility.houseIDAndLocations.get(house).first != null);
            assertEquals(true,utility.houseIDAndLocations.get(house).second != null);
        }
    }

    @Test
    public void testHouseSunsetFetching() {
        Utility utility = Utility.getInstance();
        for (String house: utility.houseIDAndLocations.keySet()){
            assertEquals(true,utility.houseIDAndSun.containsKey(house));
            assertEquals(true,utility.houseIDAndSun.get(house).first != null);
            assertEquals(true,utility.houseIDAndSun.get(house).second != null);
        }
    }

    @Test
    public void testHouseScheduleFetching(){
        Utility utility = Utility.getInstance();
        for (String house: utility.houseIDAndLocations.keySet()){
            assertEquals(true,utility.houseIDAndSchedules.containsKey(house));
            assertEquals(true,utility.houseIDAndSchedules.get(house) != null);
        }
    }

    @Test
    public void housesIDGetterTest(){
        Utility utility = Utility.getInstance();
        Set<String> ids = utility.getHouseIDS();
        for (String house: utility.houseIDAndLocations.keySet()){
            assertEquals(true,ids.contains(house));
        }
    }

    @Test
    public void checkScheduleAndHouses(){
        Utility utility = Utility.getInstance();
        Set<String> ids = utility.getHouseIDS();
        Schedule compareSchedule = new Schedule();
        for (String house: ids){
            assertEquals(compareSchedule.getAfterSunRise(),utility.getHouseScheduleByID(house).getAfterSunRise());
            assertEquals(compareSchedule.getBeforeSunSet(),utility.getHouseScheduleByID(house).getBeforeSunSet());
            assertEquals(compareSchedule.getEndMonth(),utility.getHouseScheduleByID(house).getEndMonth());
            assertEquals(compareSchedule.getStartMonth(),utility.getHouseScheduleByID(house).getStartMonth());
        }
    }

    @Test
    public void setScheduleTest(){
        Utility utility = Utility.getInstance();
        Schedule compareSchedule = new Schedule();
        String house = "satestoo";
        utility.setNewScheduleForHouse(compareSchedule, house);
        assertEquals(compareSchedule.getAfterSunRise(),utility.getHouseScheduleByID(house).getAfterSunRise());
        assertEquals(compareSchedule.getBeforeSunSet(),utility.getHouseScheduleByID(house).getBeforeSunSet());
        assertEquals(compareSchedule.getEndMonth(),utility.getHouseScheduleByID(house).getEndMonth());
        assertEquals(compareSchedule.getStartMonth(),utility.getHouseScheduleByID(house).getStartMonth());
    }


    @Test
    public void testPairClass(){
        Utility.Pair pr = new Utility.Pair(1,3);
        Utility.Pair pr1 = new Utility.Pair("pirveli","meore");
        assertEquals(1, pr.first);
        assertEquals(3, pr.second);
        assertEquals("pirveli", pr1.first);
        assertEquals("meore", pr1.second);
    }

    @Test
    public void testScheduleChecking(){
        Utility utility = Utility.getInstance();
        utility = mock(Utility.class);
        for (String house : utility.getHouseIDS()){
            Date date = new Date();
            date.setHours(15);
            when(utility.getCurrentDate()).thenReturn(date);
            assertEquals(true,utility.timeForSprinkler(house));
            date.setHours(22);
            when(utility.getCurrentDate()).thenReturn(date);
            assertEquals(false,utility.timeForSprinkler(house));
        }
    }


}
