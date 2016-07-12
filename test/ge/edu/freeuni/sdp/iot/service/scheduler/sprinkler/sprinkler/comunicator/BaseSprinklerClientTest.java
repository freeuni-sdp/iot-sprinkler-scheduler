package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator;

import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.Utility;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests.RequestBuilderFactory;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests.RequestWrapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.client.ClientBuilder;
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
    public void setStatusCreatesCorrectBody() throws Exception {

        RequestBuilderFactory builderFactory = mock(RequestBuilderFactory.class);
        RequestWrapper requestWrapper = mock(RequestWrapper.class);

        SprinklerClient client = new BaseSprinklerClient(Utility.SPRINKLER_PROD_API_ADDRESS,builderFactory,requestWrapper);

        ArgumentCaptor<Entity> entity = ArgumentCaptor.forClass(Entity.class);
        ArgumentCaptor<Invocation.Builder> request = ArgumentCaptor.forClass(Invocation.Builder.class);
        client.setStatus(true,"1",90);

        verify(requestWrapper).invokePost(request.capture(),entity.capture());


        String template = Utility.SPRINKLER_BODY_TEMPLATE;
        String payloadJson = String.format(template , 1, "on" , 90);

        Entity<String> json = Entity.json(payloadJson);

        assertEquals(json, entity.getValue());
    }


    @Test
    public void setStatusCreatesCorrectRequest() throws Exception {
        String s =  Utility.SPRINKLER_PROD_API_ADDRESS +"/houses/1";
        RequestBuilderFactory builderFactory  = mock(RequestBuilderFactory.class);
        RequestWrapper requestWrapper = mock(RequestWrapper.class);

        SprinklerClient client = new BaseSprinklerClient(Utility.SPRINKLER_PROD_API_ADDRESS,builderFactory,requestWrapper);

        when(builderFactory.getRequestBuilder(anyString())).thenReturn(ClientBuilder.newClient().target(s).request());
        client.setStatus(true,"1",90);

        verify(builderFactory).getRequestBuilder(anyString());

    }




}