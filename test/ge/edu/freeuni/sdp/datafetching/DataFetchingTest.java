package ge.edu.freeuni.sdp.datafetching;

import ge.edu.freeuni.sdp.sprinklerscheduler.core.Listener;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GM on 6/24/2016.
 */
public class DataFetchingTest {

    @Test
    public void testData() {
        Map<Integer, Listener.Pair> houseIDAndLocations = new HashMap<>();
        Listener.Pair pr = new Listener.Pair<>(36.7201600, -4.4203400);
        houseIDAndLocations.put(1, pr);
        Listener ls = new Listener();
        ls.setHouseIDAndLocations(houseIDAndLocations);
        Listener.Pair info = ls.getSunData(1);
    }

    @Test
    public void testKikola() throws IOException {
        Listener ls = new Listener();
        ls.getHousesData();
    }


}
