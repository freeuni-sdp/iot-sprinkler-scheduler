package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator;

import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.Utility;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests.RequestBuilderFactory;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests.RequestWrapper;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;


public class BaseSprinklerClient implements SprinklerClient {

    private String sprinklerApiAddress;
    private RequestBuilderFactory factory;
    private RequestWrapper wrapper;

    private static final String templateUrl = "/houses/%s";

    public BaseSprinklerClient(String sprinklerApiAddress, RequestBuilderFactory factory, RequestWrapper wrapper){

        this.sprinklerApiAddress = sprinklerApiAddress;
        this.factory = factory;
        this.wrapper = wrapper;

    }

    @Override
    public void setStatus(boolean status, String houseId) {
        setStatus(status, houseId, Utility.DEFAULT_DURATION);
    }

    @Override
    public void setStatus(boolean status, String houseId, int duration) {

        String path = String.format(sprinklerApiAddress+templateUrl,houseId);
        Invocation.Builder request = factory.getRequestBuilder(path);

        Entity payload = getEntity(status, houseId, duration);
        Response response = wrapper.invokePost(request,payload);

    }


    private Entity getEntity(boolean status, String houseId, int duration) {
        String template = Utility.SPRINKLER_BODY_TEMPLATE;
        String payloadJson = String.format(template , houseId, status ? "on" : "off", duration);

        return Entity.json(payloadJson);
    }
}
