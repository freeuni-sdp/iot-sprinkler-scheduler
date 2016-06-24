package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator;


public interface SprinklerCommunicator {
    String sprinklerMockApiAddress = "https://private-a39ec-iotsprinkler.apiary-mock.com/webapi/houses/%s/task";
    int DEFAULT_DURATION = 60;

    void setStatus(boolean status, String houseId);
    void setStatus(boolean status, String houseId, int duration);
}
