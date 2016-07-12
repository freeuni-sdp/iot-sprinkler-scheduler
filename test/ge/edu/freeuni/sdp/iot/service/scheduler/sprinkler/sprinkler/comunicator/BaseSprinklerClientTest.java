package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator;

import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.Utility;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests.RequestBuilderFactory;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests.RequestWrapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class BaseSprinklerClientTest {

    private SprinklerClientFactory clientFactory;

    @Before
    public void start(){
        clientFactory = new SprinklerClientFactory();
    }

    @Test
    public void setStatusCreatesCorrectPath() throws Exception {


        String s =  Utility.SPRINKLER_PROD_API_ADDRESS +"/houses/1";

        RequestBuilderFactory builderFactory = mock(RequestBuilderFactory.class);
        RequestWrapper requestWrapper = mock(RequestWrapper.class);

        SprinklerClient client = new BaseSprinklerClient(Utility.SPRINKLER_PROD_API_ADDRESS,builderFactory,requestWrapper);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        client.setStatus(true,"1");

        verify(builderFactory).getRequestBuilder(argument.capture());

        assertEquals(s, argument.getValue());

    }

    @Test
    public void setStatus1() throws Exception {

    }

}