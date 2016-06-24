package ge.edu.freeuni.sdp.sprinklerscheduler.core;


import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.data.Schedule;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.updater.SprinklerStatusUpdater;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.sprinkler.comunicator.SprinklerCommunicatorProxy;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;


@WebListener()
public class Listener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private Map<Integer, Pair> houseIDAndLocations;
    private Map<Integer, Schedule> houseIDAndSchedules;

    // Public constructor is required by servlet spec
    public Listener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("listener started");
        new SprinklerStatusUpdater(Schedule.getInstance(),new SprinklerCommunicatorProxy()).start();

    }

    public void setHouseIDAndLocations(Map<Integer, Pair> houseIDAndLocations) {
        this.houseIDAndLocations = houseIDAndLocations;
    }

    public void setHouseIDAndSchedules(Map<Integer, Schedule> houseIDAndSchedules) {
        this.houseIDAndSchedules = houseIDAndSchedules;
    }

    /**
     * Fetches sunset times and sunrise times
     */
    public Pair<String> getSunData(int house_id) {
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
    public Map<Integer, Pair> getHousesData() {
        Map<Integer, Pair> houseIDAndLocations = new HashMap<>();
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://private-0ab61f-iothouseregistry.apiary-mock.com/houses")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get();
        if (response.getStatus() == 200) {
            String jsonString = response.readEntity(String.class);
            JsonArray houses = Json.parse(jsonString).asArray();
            System.out.println(houses);
            for (JsonValue house : houses) {
                JsonValue house_id = house.asObject().get("name");
                String id_string = house_id.asObject().getString("_", "-1");

                String[] houseNameParts = id_string.split("#");
                int houseID = Integer.parseInt(houseNameParts[1]);

                JsonValue house_location = house.asObject().get("geo_location");
                String location_string = house_location.asObject().getString("_", "-1");

                String[] parts = location_string.split(",");
                double latitude = Double.parseDouble(parts[0]);
                double longtitude = Double.parseDouble(parts[1]);
                Pair<Double> geoLoc = new Pair<>(latitude, longtitude);
                System.out.println("house id is:   " + houseID + "  coords are:   " + latitude + "  aaand  " + longtitude);
                houseIDAndLocations.put(houseID, geoLoc);
            }
        } else {
            System.out.println("Could not load houses' IDs");
        }
        return houseIDAndLocations;
    }

    public static class Pair<T> {
        T first, second;

        public Pair(T a, T b) {
            this.first = a;
            this.second = b;
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
