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
        Utility utility = Utility.getInstance();
        utility.init();
        Double afterSunRise = 1.5;
        Double beforeSunSet = 0.4;

        Date sunRise = (Date) utility.houseIDAndSun.get(1).first;
        Date sunSet = (Date) utility.houseIDAndSun.get(1).second;

        Date currentDate = new Date();

        int rightNow = currentDate.getHours()*3600 + currentDate.getMinutes()*60 + currentDate.getSeconds();
        int afterSunRizeTime = sunRise.getHours()*3600 + sunRise.getMinutes()*60 + sunRise.getSeconds();
        int beforeSunSetTime = sunSet.getHours()*3600 + sunSet.getMinutes()*60 + sunSet.getSeconds();
        System.out.println("now "+currentDate.getHours() + " after " + sunRise.getHours() + " before  " + sunSet.getHours());

        if ( rightNow - afterSunRise*3600 > afterSunRizeTime
                && rightNow + beforeSunSet*3600 < beforeSunSetTime){
            System.out.println("kiii");
        }
        System.out.println(rightNow + " asdasd   " + afterSunRizeTime + "     daaaa   " + beforeSunSetTime);
    }


}
