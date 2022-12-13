package com.example.springserver;

import java.util.ArrayList;
import java.util.List;

public class Client {
    List<String> appointments;

    public Client() {
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(String appointmentName) {
        appointments.add(appointmentName);
    }
}
