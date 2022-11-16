package com.example.restserver;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationPath("/")
public class RestServer extends Application {
    Map<String, Client> clients = new HashMap<>(); // List of clients
    Map<String, Appointment> appointments = new HashMap<>(); // Server reference of appointments to alert clients
    Map<String, List<String>> clientsToInvite = new HashMap<>(); // List of clients to invite to appointments (client, appointments[])

    static boolean minuteComparison(long t1, long t2) {
        Timestamp time1 = new Timestamp(t1);
        Timestamp time2 = new Timestamp(t2);
        if (time1.getYear() == time2.getYear()
                && time1.getMonth() == time2.getMonth()
                && time1.getDate() == time2.getDate()
                && time1.getHours() == time2.getHours()
                && time1.getMinutes() == time2.getMinutes()) {
            return true;
        } else {
            return false;
        }
    }
}