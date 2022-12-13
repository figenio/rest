package com.example.springserver;

import java.util.Map;

public class Scheduler {
    Map<String, Client> clients;
    Map<String, Appointment> appointments;

    public void registerClient(String clientName) {
        clients.put(clientName, new Client());
        System.out.println("New client: " + clientName);
    }
}
