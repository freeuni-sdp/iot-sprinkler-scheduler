package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class RequestWrapperTest {
    @Test
    public void invokePost() throws Exception {
        Invocation.Builder builder = mock(Invocation.Builder.class);
        Entity<String> payload = Entity.json("{a:1}");

        new RequestWrapper().invokePost(builder,payload);

        ArgumentCaptor<Entity> entity = ArgumentCaptor.forClass(Entity.class);

        verify(builder).post(entity.capture());

        assertEquals(payload,entity.getValue());
    }

    @Test
    public void invokeGet() throws Exception {
        Invocation.Builder builder = mock(Invocation.Builder.class);

        new RequestWrapper().invokeGet(builder);

        verify(builder).get();

    }

}