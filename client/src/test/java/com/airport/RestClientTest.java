package com.airport.http.client;

import org.junit.jupiter.api.*;
import org.mockito.*;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import org.mockito.junit.jupiter.MockitoExtension;
import com.airport.server.model.*;


@ExtendWith(MockitoExtension.class)
class RestClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RestClient restClient = new RestClient("http://localhost:8080");

    @Test
    void testGetAirportsInCity() {
        Long cityId = 1L;
        String url = "http://localhost:8080/cities/" + cityId + "/airports";

        City mockCity = new City(1L, "Los Angeles", "California", 4000000);
        List<Airport> mockAirports = List.of(
            new Airport(1L, "LAX", "LAX", mockCity, null),
            new Airport(2L, "SFO", "SFO", mockCity, null)
        );

        ResponseEntity<List> responseEntity = new ResponseEntity<>(mockAirports, HttpStatus.OK);
        when(restTemplate.getForEntity(url, List.class)).thenReturn(responseEntity);

        List<?> result = restClient.getAirportsInCity(cityId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.get(0) instanceof Airport);
    }

    @Test
    void testGetAircraftForPassenger() {
        Long passengerId = 5L;
        String url = "http://localhost:8080/passengers/" + passengerId + "/aircraft";

        City mockCity = new City(1L, "Los Angeles", "California", 4000000);
        Passenger mockPassenger = new Passenger(5L, "John", "Doe", "123-456-7890", mockCity, null);
        List<Aircraft> mockAircraft = List.of(
            new Aircraft(1L, "Boeing 737", "Delta Airlines", 150, null, List.of(mockPassenger)),
            new Aircraft(2L, "Airbus A320", "United Airlines", 180, null, List.of(mockPassenger))
        );

        ResponseEntity<List> responseEntity = new ResponseEntity<>(mockAircraft, HttpStatus.OK);
        when(restTemplate.getForEntity(url, List.class)).thenReturn(responseEntity);

        List<?> result = restClient.getAircraftForPassenger(passengerId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.get(0) instanceof Aircraft);
    }

    @Test
    void testGetAirportsForAircraft() {
        Long aircraftId = 9L;
        String url = "http://localhost:8080/aircraft/" + aircraftId + "/airports";

        List<Airport> mockAirports = List.of(
            new Airport(1L, "LAX", "LAX", null, List.of(new Aircraft(aircraftId, "Boeing 737", "Delta Airlines", 150, null, null))),
            new Airport(2L, "SFO", "SFO", null, List.of(new Aircraft(aircraftId, "Airbus A320", "United Airlines", 180, null, null)))
        );

        ResponseEntity<List> responseEntity = new ResponseEntity<>(mockAirports, HttpStatus.OK);
        when(restTemplate.getForEntity(url, List.class)).thenReturn(responseEntity);

        List<?> result = restClient.getAirportsForAircraft(aircraftId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.get(0) instanceof Airport);
    }

    @Test
    void testGetAirportsUsedByPassengers() {
        String url = "http://localhost:8080/passengers/airports";

        List<Airport> mockAirports = List.of(
            new Airport(1L, "LAX", "LAX", null, null),
            new Airport(2L, "SFO", "SFO", null, null)
        );

        ResponseEntity<List> responseEntity = new ResponseEntity<>(mockAirports, HttpStatus.OK);
        when(restTemplate.getForEntity(url, List.class)).thenReturn(responseEntity);

        List<?> result = restClient.getAirportsUsedByPassengers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.get(0) instanceof Airport);
    }
}


