package com.example.springserver;

import java.util.ArrayList;
import java.util.List;

public class Client {
    String name;
    List<String> appointments;

    public Client(String name) {
        this.name = name;
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(String appointmentName) {
        appointments.add(appointmentName);
    }
}
