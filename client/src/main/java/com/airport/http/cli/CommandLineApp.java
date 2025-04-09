package com.airport.http.cli;

import com.airport.http.client.RestClient;

import java.util.*;

public class Main {
    private static final String SERVER_URL = "http://localhost:8080";

    public static void main(String[] args) {
        RestClient restClient = new RestClient(SERVER_URL);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Find airports in a city");
            System.out.println("2. List aircraft a passenger has traveled on");
            System.out.println("3. List airports an aircraft can take off from and land at");
            System.out.println("4. List airports passengers have used");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter city ID: ");
                    Long cityId = scanner.nextLong();
                    List<Map<String, Object>> airports = restClient.getAirportsInCity(cityId);
                    if (airports != null && !airports.isEmpty()) {
                        System.out.println("Airports in city " + cityId + ":");
                        for (Map<String, Object> airport : airports) {
                            String name = (String) airport.get("name");
                            String code = (String) airport.get("code");
                            System.out.println("Airport: " + name + " (" + code + ")");
                        }
                    } else {
                        System.out.println("No airports found for city ID " + cityId);
                    }
                    break;

                case 2:
                    System.out.print("Enter passenger ID: ");
                    Long passengerId = scanner.nextLong();
                    List<Map<String, Object>> aircraft = restClient.getAircraftForPassenger(passengerId);
                    if (aircraft != null && !aircraft.isEmpty()) {
                        System.out.println("Aircraft traveled on by passenger " + passengerId + ":");
                        for (Map<String, Object> air : aircraft) {
                            String type = (String) air.get("type");
                            System.out.println("Aircraft: " + type);
                        }
                    } else {
                        System.out.println("No aircraft found for passenger ID " + passengerId);
                    }
                    break;

                case 3:
                    System.out.print("Enter aircraft ID: ");
                    Long aircraftId = scanner.nextLong();
                    List<Map<String, Object>> airportsForAircraft = restClient.getAirportsForAircraft(aircraftId);
                    if (airportsForAircraft != null && !airportsForAircraft.isEmpty()) {
                        System.out.println("Airports for aircraft " + aircraftId + ":");
                        for (Map<String, Object> airport : airportsForAircraft) {
                            String name = (String) airport.get("name");
                            String code = (String) airport.get("code");
                            System.out.println("Airport: " + name + " (" + code + ")");
                        }
                    } else {
                        System.out.println("No airports found for aircraft ID " + aircraftId);
                    }
                    break;

                case 4:
                    List<Map<String, Object>> usedAirports = restClient.getAirportsUsedByPassengers();
                    if (usedAirports != null && !usedAirports.isEmpty()) {
                        System.out.println("Airports used by passengers:");
                        for (Map<String, Object> airport : usedAirports) {
                            String name = (String) airport.get("name");
                            String code = (String) airport.get("code");
                            System.out.println("Airport: " + name + " (" + code + ")");
                        }
                    } else {
                        System.out.println("No airports used by passengers.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }
}


