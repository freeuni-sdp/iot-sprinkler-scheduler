package ge.edu.freeuni.sdp.datafetching;

import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.Utility;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.data.Schedule;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GM on 6/24/2016.
 */
public class DataFetchingTest {

    @Test
    public void testKikola() throws IOException {
        Utility utility = Utility.getInstance();
        utility.init();

        Schedule schedule = utility.houseIDAndSchedules.get("3c5afb74-2e82-4f10-9931-89187fe47adf");

        Double afterSunRise = schedule.getAfterSunRise();
        Double beforeSunSet = schedule.getBeforeSunSet();

        Date sunRise = (Date) utility.houseIDAndSun.get("3c5afb74-2e82-4f10-9931-89187fe47adf").first;
        Date sunSet = (Date) utility.houseIDAndSun.get("3c5afb74-2e82-4f10-9931-89187fe47adf").second;

        Date currentDate = new Date();

        int rightNow = currentDate.getHours()*3600 + currentDate.getMinutes()*60 + currentDate.getSeconds();
        int afterSunRizeTime = sunRise.getHours()*3600 + sunRise.getMinutes()*60 + sunRise.getSeconds();
        int beforeSunSetTime = sunSet.getHours()*3600 + sunSet.getMinutes()*60 + sunSet.getSeconds();
        System.out.println("now "+currentDate.getHours() + " after " + sunRise.getHours() + " before  " + sunSet.getHours());

        Schedule houseSchedule = utility.houseIDAndSchedules.get("3c5afb74-2e82-4f10-9931-89187fe47adf");
        houseSchedule.addExcluded("28/08/1995");
        String newstring = new SimpleDateFormat("dd/MM/yyy").format(currentDate);
        System.out.println(newstring + "  da  " + houseSchedule.getExcluded().get(0));


        if ( rightNow - afterSunRise*3600 > afterSunRizeTime
                && rightNow + beforeSunSet*3600 < beforeSunSetTime){
            System.out.println("kiii");
        }
        System.out.println(rightNow + " asdasd   " + afterSunRizeTime + "     daaaa   " + beforeSunSetTime);
    }


}
