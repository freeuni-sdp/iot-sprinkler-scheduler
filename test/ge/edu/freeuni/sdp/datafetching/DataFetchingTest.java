package ge.edu.freeuni.sdp.datafetching;

import ge.edu.freeuni.sdp.sprinklerscheduler.core.Listener;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by GM on 6/24/2016.
 */
public class DataFetchingTest {

    @Test
    public void testKikola() throws IOException {
        Listener ls = new Listener();
        ls.getHousesData();
    }


}
