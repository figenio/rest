package com.example.restserver;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

@Path("/appointment")
public class ServerAppointmentResource {
    Map<String, Client> clients = new HashMap<>();
    Map<String, Appointment> appointments = new HashMap<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerAppointment(Appointment appointment) {
        System.out.println("Registering new appointment!");
        System.out.println("New appointment: " + appointment.name);
        // TODO invite clients mentioned
        appointments.put(appointment.name, appointment); // Register appointment
    }

//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void cancelAppointmentAlert(String clientName, String apName) {
//        System.out.println("Cancelling alert of " + apName + " for " + clientName);
//        appointments.get(apName).guests.put(clientName, 0);
//    }
//
//    @DELETE
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void cancelAppointment(String clientName, String apName) {
//        System.out.println("Cancelling " + apName + " for " + clientName);
//        clients.get(clientName).appointments.remove(apName);
//        appointments.get(apName).removeGuest(clientName);
//
//        // If no more guest are confirmed, the appointment is removed
//        if (appointments.get(apName).guests.size() < 1) {
//            System.out.println("No more guest for " + apName + ", deleting it!!");
//            appointments.remove(apName);
//        }
//    }

}
