package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.updater;


import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.data.Schedule;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.SprinklerCommunicator;

public class SprinklerStatusUpdater extends Thread {

    private Schedule schedule;
    private SprinklerCommunicator communicator;

    public SprinklerStatusUpdater(Schedule schedule, SprinklerCommunicator communicator){

        this.schedule = schedule;
        this.communicator = communicator;
    }


    @Override
    public void run() {
        while (true){
            if (schedule.shoudSendOn()){
                communicator.setStatus(true,"1");
            }
            try {
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
