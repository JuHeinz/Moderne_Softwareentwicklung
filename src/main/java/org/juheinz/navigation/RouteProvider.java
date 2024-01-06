package org.juheinz.navigation;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;


public class RouteProvider {
    static HttpResponse<String> response;
    public static void getRoute(double[] coordinateA, double[] coordinateB){

        String query = "?latA="+coordinateA[0]+"&longA="+coordinateA[1]+"&latB="+coordinateB[0]+"&longB="+coordinateB[1];

        System.out.println(query);
        //build request to API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8000/get-route/" + query))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();


        // send request to api

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.body());
    }


}

