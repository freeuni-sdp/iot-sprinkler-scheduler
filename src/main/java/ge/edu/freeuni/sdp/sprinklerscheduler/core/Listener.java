package ge.edu.freeuni.sdp.sprinklerscheduler.core;


import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule.Schedule;

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
    private Map<Integer, Schedule> houseIDandSchedules;

    // Public constructor is required by servlet spec
    public Listener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("listener started");
    }

    /** Fetches sunset times and sunrise times*/
    private Pair<Integer> getSunData() {

        return null;
    }

    /** Fetches houses IDs */
    private Map<Integer, Pair> getHousesData(){
        Map<Integer, Pair> houseIDAndLocations = new HashMap<>();
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://iot-house-registry.apiblueprint.org/houses")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get();
        if (response.getStatus() == 200){
            String jsonString = response.readEntity(String.class);
            JsonArray jsonArray = JsonArray.readFrom(jsonString);
            for (int i=0; i<jsonArray.size(); i++){
                JsonObject jsonObject = jsonArray.get(i).asObject();

                JsonObject locObject = jsonObject.get("geo_location").asObject();
                String coordinates = locObject.get("_").asString();
                String[] parts = coordinates.split(",");
                double latitude = Double.parseDouble(parts[0]);
                double longtitude = Double.parseDouble(parts[1]);
                Pair<Double> geoLoc = new Pair<>(latitude, longtitude);

                JsonObject nameObject = jsonObject.get("name").asObject();
                String houseName = nameObject.get("_").asString();
                String[] houseNameParts = houseName.split("#");
                int houseID = Integer.parseInt(houseNameParts[1]);

                houseIDAndLocations.put(houseID, geoLoc);
            }

        } else {
            System.out.println("Could not load houses' IDs");
        }
        return houseIDAndLocations;
    }

    private class Pair<T>{
        T first, second;

        Pair(T a, T b){
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
