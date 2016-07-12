package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator;


import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.Utility;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests.RequestBuilderFactory;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.requests.RequestWrapper;

public class SprinklerClientFactory {

    public SprinklerClient baseClient(){
        return  createClient(Utility.SPRINKLER_PROD_API_ADDRESS);

    }

    public SprinklerClient proxyClient(){
        return  createClient(Utility.SPRINKLER_MOCK_API_ADDRESS);

    }


    private SprinklerClient createClient(String path){
        return new BaseSprinklerClient(path, new RequestBuilderFactory(), new RequestWrapper());
    }




}
