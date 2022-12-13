package com.example.springserver;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public void registerAppointment(String clientName, String apName, long apTime, List<String> guests) {
        System.out.println("Registering new appointment " + apName + " at " + apTime);
        appointments.put(apName, new Appointment()); // Register appointment
        appointments.get(apName).setDateTime(apTime);
        appointments.get(apName).setName(apName);

        // Register the client that created the appointment in it
        appointments.get(apName).guests.add(clientName);
        clients.get(clientName).addAppointment(apName);

        // Then registers its guests
        for (int i = 0 ; i< guests.size(); i++) {
            appointments.get(apName).guests.add(guests.get(i));
            clients.get(guests.get(i)).addAppointment(apName);
        }

        System.out.println("Appointment guests: " + appointments.get(apName).guests.toString());
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
    public List<Appointment> queryAppointments(String clientName, long longToSearch) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        System.out.println("Querying for appointments of " + clientName + " at " + dateFormat.format(new Date(longToSearch)));

        List<Appointment> appointmentsOfClient = new ArrayList<>();
        for (Map.Entry<String, Appointment> entry : appointments.entrySet()) {
            if (dateFormat.format(new Date(longToSearch)).equals(dateFormat.format(new Date(entry.getValue().dateTime)))) {
                appointmentsOfClient.add(entry.getValue());
            }
        }
        return appointmentsOfClient;
    }

    // Client participates in appointment
    public void participateInAppointment(String clientName, String appointmentName) {
        appointments.get(appointmentName).guests.add(clientName);
        clients.get(clientName).addAppointment(appointmentName);
    }
}
