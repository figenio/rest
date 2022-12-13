package com.example.springserver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Appointment {
    Timestamp dateTime; // Date and time of appointment
    Map<String, Integer> guests; // List of guests and their preferred alert time

    public Appointment(Timestamp dateTime) {
        this.dateTime = dateTime;
        this.guests = new HashMap<>();
    }
}
