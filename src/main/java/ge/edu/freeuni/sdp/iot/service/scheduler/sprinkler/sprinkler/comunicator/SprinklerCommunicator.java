package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator;


public interface SprinklerCommunicator {
    String SPRINKLER_MOCK_API_ADDRESS = "https://private-a39ec-iotsprinkler.apiary-mock.com/webapi";
    String SPRINKLER_PROD_API_ADDRESS = "https://iot-sprinkler.herokuapp.com/webapi";
    int DEFAULT_DURATION = 60;

    void setStatus(boolean status, String houseId);
    void setStatus(boolean status, String houseId, int duration);
}
