package org.juheinz.navigation;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;


public class NavigationProvider {

    static HttpResponse<String> response;

    public static String getRoute(double[] coordinateA, double[] coordinateB){
        String query = "get-route/?latA="+coordinateA[0]+"&longA="+coordinateA[1]+"&latB="+coordinateB[0]+"&longB="+coordinateB[1];
        HttpRequest request = createRequest(query);
        response = sendRequest(request);

        // Extract direction and distance from the response
        JSONObject jsonObject = new JSONObject(response.body());
        double direction = jsonObject.getDouble("direction");
        double distance = jsonObject.getDouble("distance");
        return "Fahre " + String.format("%.2f", distance) + "km lang in Kompassrichtung " + String.format("%.4f", direction) + "°";
    }


    public static Double getDistance(double[] coordinateA, double[] coordinateB){
        String query = "get-distance/?latA="+coordinateA[0]+"&longA="+coordinateA[1]+"&latB="+coordinateB[0]+"&longB="+coordinateB[1];
        HttpRequest request = createRequest(query);
        response = sendRequest(request);
        return Double.valueOf(response.body());
    }

    private static HttpRequest createRequest(String query){
        //build request to API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8000/" + query))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        return request;
    }

    private static HttpResponse sendRequest(HttpRequest request){
        // send request to api
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.body());
        return  response;
    }

}

