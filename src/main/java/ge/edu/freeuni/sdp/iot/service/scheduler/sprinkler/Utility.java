package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.data.Schedule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by GM on 6/24/2016.
 */
public class Utility {

    public static String SPRINKLER_MOCK_API_ADDRESS = "https://private-a39ec-iotsprinkler.apiary-mock.com/webapi";
    public static String SPRINKLER_PROD_API_ADDRESS = "https://iot-sprinkler.herokuapp.com/webapi";
    public static int  DEFAULT_DURATION = 60;



    public Map<String, Pair> houseIDAndLocations;
    public Map<String, Schedule> houseIDAndSchedules;
    public Map<String, Pair> houseIDAndSun;

    private static Utility instance = new Utility();

    public static Utility getInstance(){
        return instance;
    }

    private Utility(){

    }

    public void init() {
        this.houseIDAndLocations = new HashMap<>();
        this.houseIDAndSchedules = new HashMap<>();
        this.houseIDAndSun = new HashMap<>();
        fetchNewDataFromLinks();
        Runnable myThread = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1800000);
                        refreshData();
                    }
                    catch (InterruptedException e){
                        System.out.println("pfffff");
                    }
                }
            }
        };
        Thread thread = new Thread(myThread);
        thread.start();
    }

    public void refreshData(){
        Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        int seconds = c.get(Calendar.SECOND);

        if(hours*3600 + minutes*60 + seconds < 1800){
            fetchNewDataFromLinks();
        }
    }

    public Date getCurrentDate(){
        return new Date();
    }

    public boolean timeForSprinkler(String houseID){
        Schedule schedule = this.houseIDAndSchedules.get(houseID);
        Double afterSunRise = schedule.getAfterSunRise();
        Double beforeSunSet = schedule.getBeforeSunSet();

        Date sunRise = (Date) this.houseIDAndSun.get(houseID).first;
        Date sunSet = (Date) this.houseIDAndSun.get(houseID).second;

        Date currentDate = getCurrentDate();

        int rightNow = currentDate.getHours()*3600 + currentDate.getMinutes()*60 + currentDate.getSeconds();
        int afterSunRizeTime = sunRise.getHours()*3600 + sunRise.getMinutes()*60 + sunRise.getSeconds();
        int beforeSunSetTime = sunSet.getHours()*3600 + sunSet.getMinutes()*60 + sunSet.getSeconds();

        Schedule houseSchedule = this.houseIDAndSchedules.get(houseID);
        houseSchedule.addExcluded("28/08/1995");
        String currDate = new SimpleDateFormat("dd/MM/yyy").format(currentDate);
        System.out.println(currDate + "  da  " + houseSchedule.getExcluded().get(0));

        if ( rightNow - afterSunRise*3600 > afterSunRizeTime
                && rightNow + beforeSunSet*3600 < beforeSunSetTime){
            for (int i=0; i<houseSchedule.getExcluded().size(); i++){
                if (currDate.equals(houseSchedule.getExcluded().get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    private void fetchNewDataFromLinks(){
        this.houseIDAndLocations = getHousesData();
        this.houseIDAndSun.clear();
        for (String houseID : getHouseIDS()){
            Pair sun = getSunData(houseID);
            String sunRise = ((String)sun.first);
            String sunSet = ((String)sun.second);

            DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
            try
            {
                Date dateSunRise = formatter.parse(sunRise);
                Date dateSunSet = formatter.parse(sunSet);
                this.houseIDAndSun.put(houseID, new Pair(dateSunRise,dateSunSet));
            }
            catch (java.text.ParseException ex)
            {
                System.out.println("Exception "+ex);
            }
        }
    }

    public Set<String> getHouseIDS(){
        return this.houseIDAndLocations.keySet();
    }

    public Schedule getHouseScheduleByID(String id){
        return this.houseIDAndSchedules.get(id);
    }

    /**
     * Fetches sunset times and sunrise times
     */
    public Pair<String> getSunData(String house_id) {
        Pair<Double> coordinates = this.houseIDAndLocations.get(house_id);
        double langtitude = coordinates.first;
        double longtitude = coordinates.second;

        Client client = ClientBuilder.newClient();
        Response response = client.target("http://api.sunrise-sunset.org/json")
                .queryParam("lat", langtitude)
                .queryParam("lng", longtitude)
                .queryParam("date", "today")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get();

        if (response.getStatus() == 200) {
            String jsonString = response.readEntity(String.class);
            JsonObject items = Json.parse(jsonString).asObject().get("results").asObject();
            String sunrise = items.asObject().getString("sunrise", "6:00:00 AM");
            String sunset = items.asObject().getString("sunset", "7:00:00 PM");
            System.out.println(sunrise + "    " + sunset);
            return new Pair<>(sunrise, sunset);
        } else {
            System.out.println("Could not load information about the sunset and sunrise");
        }
        return null;
    }

    /**
     * Fetches houses IDs
     */
    public Map<String, Pair> getHousesData() {
        Map<String, Pair> houseIDAndLocations = new HashMap<>();
        Client client = ClientBuilder.newClient();
//        Response response = client.target("http://private-0ab61f-iothouseregistry.apiary-mock.com/houses")
//                .request(MediaType.TEXT_PLAIN_TYPE)
//                .get();
        Response response = client.target("http://iot-house-registry.herokuapp.com/houses")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get();
        if (response.getStatus() == 200) {
            String jsonString = response.readEntity(String.class);
            JsonArray houses = Json.parse(jsonString).asArray();
            System.out.println(houses);
            for (JsonValue house : houses) {
                JsonValue house_id = house.asObject().get("RowKey");
                String houseID = house_id.asObject().getString("_", "-1");

                JsonValue house_location = house.asObject().get("geo_location");
                String location_string = house_location.asObject().getString("_", "-1");

                String[] parts = location_string.split(",");
                double latitude = Double.parseDouble(parts[0]);
                double longtitude = Double.parseDouble(parts[1]);
                Pair<Double> geoLoc = new Pair<>(latitude, longtitude);
                System.out.println("house id is:   " + houseID + "  coords are:   " + latitude + "  aaand  " + longtitude);
                houseIDAndLocations.put(houseID, geoLoc);
                putDefaultScheduleToNewHouse(houseID);
            }
        } else {
            System.out.println("Could not load houses' IDs");
        }
        return houseIDAndLocations;
    }

    private void putDefaultScheduleToNewHouse(String houseID) {
        if (!this.houseIDAndSchedules.containsKey(houseID) || this.houseIDAndSchedules.get(houseID)==null){
            this.setNewScheduleForHouse(new Schedule(),houseID);
        }
    }


    public static class Pair<T> {
        public T first, second;

        public Pair(T a, T b) {
            this.first = a;
            this.second = b;
        }

    }

    public void setNewScheduleForHouse(Schedule schedule, String houseId){
        this.houseIDAndSchedules.put(houseId, schedule);
    }



}
