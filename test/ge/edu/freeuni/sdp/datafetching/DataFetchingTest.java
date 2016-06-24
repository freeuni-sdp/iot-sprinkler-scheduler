package ge.edu.freeuni.sdp.datafetching;

import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.Utility;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by GM on 6/24/2016.
 */
public class DataFetchingTest {

    @Test
    public void testKikola() throws IOException {
        Utility utility = new Utility();
        Double afterSunRise = 1.5;
        Double beforeSunSet = 0.4;

        Date sunRise = (Date) utility.houseIDAndSun.get(1).first;
        Date sunSet = (Date) utility.houseIDAndSun.get(1).second;

        Date currentDate = new Date();
        Long currentTime = currentDate.getTime();
        Double currentTimeD = currentTime.doubleValue();

        if (currentTimeD - afterSunRise*60*60*1000 > sunRise.getTime()
                && currentTimeD + beforeSunSet*60*60*1000 < sunSet.getTime()){
            System.out.println("truuuuuuuuuuueee");
        }
        System.out.println(currentTimeD - afterSunRise*60*60*1000 +"daaaaaa" + sunRise.getTime());
    }


}
