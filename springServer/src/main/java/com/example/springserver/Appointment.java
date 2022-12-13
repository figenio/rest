package com.example.springserver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Appointment {
    String name;
    Timestamp dateTime; // Date and time of appointment
    List<String> guests; // List of guests and their preferred alert time

    public void setGuests(List<String> guests) {
        this.guests = guests;
    }

    public Appointment() {
        this.guests = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getGuests() {
        return guests;
    }
}
