package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests;



import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;

public class RequestBuilderFactory {


    public Invocation.Builder getRequestBuilder(String path){
        return ClientBuilder.newClient().target(path).request();
    }


}
