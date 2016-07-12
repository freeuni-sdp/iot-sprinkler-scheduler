package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.updater;


import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.Utility;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.SprinklerClient;

import java.util.Set;

public class SprinklerStatusUpdater extends Thread {

    private Utility utility;
    private SprinklerClient communicator;

    public SprinklerStatusUpdater(Utility utility ,SprinklerClient communicator){
        this.utility = utility;

        this.communicator = communicator;
    }


    @Override
    public void run() {
        while (true){
            Set<String> houseIDS = utility.getHouseIDS();
            for (String  houseId: houseIDS){
                if (utility.timeForSprinkler(houseId)){
                    communicator.setStatus(true,houseId+"");
                }

            }
            try {
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
