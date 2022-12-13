package com.example.springserver;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Scheduler {
    Map<String, Client> clients;
    Map<String, Appointment> appointments;

    public Scheduler() {
        this.clients = new HashMap<>();
        this.appointments = new HashMap<>();
    }

    // Registers new client
    public void registerClient(String clientName) {
        clients.put(clientName, new Client(clientName));
        System.out.println("New client: " + clientName);
    }

    // Register a new appointment and sets its guests
    public void registerAppointment(String clientName, String apName, Timestamp apTime, List<String> guests) {
        System.out.println("Registering new appointment!");
        appointments.put(apName, new Appointment()); // Register appointment
        appointments.get(apName).setDateTime(apTime);

        // Register the client that created the appointment in it
        appointments.get(apName).guests.add(clientName);
        clients.get(clientName).addAppointment(apName);

        // Then registers its guests
        for (int i = 0 ; i< guests.size(); i++) {
            appointments.get(apName).guests.add(guests.get(i));
            clients.get(guests.get(i)).addAppointment(apName);
        }
    }

    // Cancels appointment for client
    public void cancelAppointment(String clientName, String apName) {
        System.out.println("Cancelling " + apName + " for " + clientName);
        clients.get(clientName).appointments.remove(apName);
        appointments.get(apName).guests.remove(clientName);

        // If no more guest are confirmed, the appointment is removed
        if (appointments.get(apName).guests.size() < 1) {
            System.out.println("No more guest for " + apName + ", deleting it!!");
            appointments.remove(apName);
        }
    }

    // Query for appointments of client by hour inputted
    public List<String> queryAppointments(String clientName, Timestamp timeToSearch) {
        System.out.println("Querying for appointments of " + clientName);

        List<String> appointmentNames = new ArrayList<>();
        for (Map.Entry<String, Appointment> entry : appointments.entrySet()) {
            if (entry.getValue().dateTime.getDate() == timeToSearch.getDate()
                    && entry.getValue().dateTime.getMonth() == timeToSearch.getMonth()
                    && entry.getValue().dateTime.getYear() == timeToSearch.getYear()
                    && entry.getValue().guests.contains(clientName)) {
                appointmentNames.add(entry.getKey());
            }
        }

        System.out.println("Returning appointments found");
        return appointmentNames;
    }

    // Client participates in appointment
    public void participateInAppointment(String clientName, String appointmentName) {
        appointments.get(appointmentName).guests.add(clientName);
        clients.get(clientName).addAppointment(appointmentName);
    }
}
