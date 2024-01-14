package org.juheinz.navigation;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;


public class NavigationProvider {

    static HttpResponse<String> response;

    /**
     * Returns an instruction on how to get from coordinateA to coordinateB
     */
    public static String getRoute(double[] coordinateA, double[] coordinateB){
        String query = "get-route/?latA="+coordinateA[0]+"&longA="+coordinateA[1]+"&latB="+coordinateB[0]+"&longB="+coordinateB[1];
        HttpRequest request = createRequest(query);
        try{
            response = sendRequest(request);

            // Extract direction and distance from the response
            JSONObject jsonObject = new JSONObject(response.body());
            double direction = jsonObject.getDouble("direction");
            double distance = jsonObject.getDouble("distance");
            return "Bewege dich " + String.format("%.2f", distance) + " km lang in Kompassrichtung " + String.format("%.4f", direction) + "°.";
        }catch (RuntimeException e){
            return "Keine Verbindung zum Navigations Microservice möglich. Ist der Server an?";
        }


    }


    /**
     * Returns distance between two coordinates
     * @return distance in km.
     */
    public static Double getDistance(double[] coordinateA, double[] coordinateB){
        String query = "get-distance/?latA="+coordinateA[0]+"&longA="+coordinateA[1]+"&latB="+coordinateB[0]+"&longB="+coordinateB[1];
        HttpRequest request = createRequest(query);
        try{
        response = sendRequest(request);
        return Double.valueOf(response.body());}
        catch (RuntimeException e){
            return 0.0;
        }
    }

    /**
     * create request for API for navigation microservice
     * @param query requested endpoint and query parameters
     */
    private static HttpRequest createRequest(String query){
        return HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8000/" + query))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    /**
     * Send request to API for navigation microservice
     */
    private static HttpResponse<String> sendRequest(HttpRequest request){
        try (HttpClient client = HttpClient.newHttpClient()){
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}

