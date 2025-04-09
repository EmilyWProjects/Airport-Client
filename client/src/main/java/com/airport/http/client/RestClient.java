package com.airport.http.client;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

public class RestClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public RestClient(String serverUrl) {
        this.restTemplate = new RestTemplate();
        this.serverUrl = serverUrl;
    }

    // Get a list of airports in a city
    public List<Map<String, Object>> getAirportsInCity(Long cityId) {
        String url = serverUrl + "/cities/" + cityId + "/airports";
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<Map<String, Object>>>() {});
        return response.getBody();
    }

    // Get the list of aircraft a passenger has traveled on
    public List<Map<String, Object>> getAircraftForPassenger(Long passengerId) {
        String url = serverUrl + "/passengers/" + passengerId + "/aircraft";
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<Map<String, Object>>>() {});
        return response.getBody();
    }

    // Get a list of airports an aircraft can take off from and land at
    public List<Map<String, Object>> getAirportsForAircraft(Long aircraftId) {
        String url = serverUrl + "/aircraft/" + aircraftId + "/airports";
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<Map<String, Object>>>() {});
        return response.getBody();
    }

    // Get a list of airports used by passengers
    public List<Map<String, Object>> getAirportsUsedByPassengers() {
        String url = serverUrl + "/passengers/airports";
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<Map<String, Object>>>() {});
        return response.getBody();
    }
}


