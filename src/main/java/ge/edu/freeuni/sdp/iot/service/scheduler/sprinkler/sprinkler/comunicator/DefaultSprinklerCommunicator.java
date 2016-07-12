package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class DefaultSprinklerCommunicator implements SprinklerCommunicator {
    private String sprinklerApiAddress;

    private static final String templateUrl = "/houses/%s";

    public DefaultSprinklerCommunicator(String sprinklerApiAddress){
        this.sprinklerApiAddress = sprinklerApiAddress;
    }


    @Override
    public void setStatus(boolean status, String houseId) {
        setStatus(status, houseId,SprinklerCommunicator.DEFAULT_DURATION);
    }

    @Override
    public void setStatus(boolean status, String houseId, int duration) {
        Client client = ClientBuilder.newClient();
        String template = "{  'house_id': '%s',  'set_status': '%s',  'duration': %s}";
        String payloadJson = String.format(template , houseId, status ? "on" : "off", duration);
        Entity payload = Entity.json(payloadJson);
        String path = String.format(sprinklerApiAddress+templateUrl,houseId);
        Response response = client.target(path)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(payload);

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        System.out.println("body: " + response.readEntity(String.class));
    }
}
