package com.example.restserver;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Path("/client")
public class ServerClientResource {
    Map<String, Client> clients = new HashMap<>();
    Map<String, Timestamp> appointments = new HashMap<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerClient(String clientName) {
        clients.put(clientName, new Client());
        System.out.println("HELLO");
        System.out.println("New client: " + clientName);
    }

    @POST
    @Path("/appointment")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerAppointment(Appointment app) {
        appointments.put(app.name, app.dateTime);
        System.out.println("New appointment: " + app.name);
    }

    // Client confirms interest in appointment and sets appointment alert
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void confirmClientToAppointment(String clientName, String apName, int alertTime) {
//        System.out.println("Confirmation from " + clientName + " for " + apName);
//        if (alertTime >= 0) {
//            appointments.get(apName).addGuest(clientName, alertTime);
//            clients.get(clientName).addAppointment(apName);
//        }
//    }

}
