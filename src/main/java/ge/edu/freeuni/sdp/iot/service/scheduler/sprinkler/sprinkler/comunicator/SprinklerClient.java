package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator;


public interface SprinklerClient {
    void setStatus(boolean status, String houseId);
    void setStatus(boolean status, String houseId, int duration);
}
