package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator;


public class SimpleSprinklerClient implements SprinklerCommunicator {

    public DefaultSprinklerCommunicator communicator;

    public SimpleSprinklerClient(){
        this.communicator = new DefaultSprinklerCommunicator(SprinklerCommunicator.SPRINKLER_PROD_API_ADDRESS);
    }

    @Override
    public void setStatus(boolean status, String houseId) {
        this.communicator.setStatus(status,houseId);
    }

    @Override
    public void setStatus(boolean status, String houseId, int duration) {
        this.communicator.setStatus(status,houseId,duration);
    }
}
