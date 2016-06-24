package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator;




public class SprinklerCommunicatorProxy implements SprinklerCommunicator {

    private DefaultSprinklerCommunicator communicator;
    public SprinklerCommunicatorProxy(){
            this.communicator = new DefaultSprinklerCommunicator(SprinklerCommunicator.sprinklerMockApiAddress);
    }

    @Override
    public void setStatus(boolean status, String houseId) {
        this.communicator.setStatus(status,houseId,SprinklerCommunicator.DEFAULT_DURATION);
    }

    @Override
    public void setStatus(boolean status, String houseId, int duration) {
        this.communicator.setStatus(status,houseId,duration);
    }
}
