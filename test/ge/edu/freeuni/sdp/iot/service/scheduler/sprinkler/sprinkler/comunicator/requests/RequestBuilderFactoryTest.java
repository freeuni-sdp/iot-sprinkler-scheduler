package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientBuilder.class)


public class RequestBuilderFactoryTest {
    @Test
    public void getRequestBuilder() throws Exception {
        PowerMockito.mockStatic(ClientBuilder.class);
        final Client mockClient = mock(Client.class);
        PowerMockito.when(ClientBuilder.newClient()).thenReturn(mockClient);



        ArgumentCaptor<String> path = ArgumentCaptor.forClass(String.class);



        final WebTarget mockWebTarget = mock(WebTarget.class);

        when(mockClient.target(anyString())).thenReturn(mockWebTarget);

        new RequestBuilderFactory().getRequestBuilder("a");

        verify(mockClient).target(path.capture());

        verify(mockWebTarget).request();

        assertEquals("a",path.getValue());
    }

}