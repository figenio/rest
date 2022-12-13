package com.example.springserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class ServerController {
    @Autowired
    private Scheduler scheduler;

    @CrossOrigin
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        System.out.println("Giving hello");
        return String.format("Hello %s!", name);
    }

    @CrossOrigin
    @PostMapping("/client")
    public void registerClient(@RequestParam(defaultValue = "client") String clientName) {
        System.out.println("SPRING: Registering client - " + clientName);
        scheduler.registerClient(clientName);
        System.out.println(scheduler.clients.keySet());
    }

    @CrossOrigin
    @PostMapping("/appointment")
    public void registerAppointment(@RequestBody Appointment appointment, @RequestParam(value = "clientName") String clientName) {
        System.out.println("SPRING: registering appointment - " + appointment.name);
        scheduler.registerAppointment(clientName, appointment.name, appointment.dateTime, appointment.guests);
        System.out.println(scheduler.appointments.keySet());
    }

    @CrossOrigin
    @DeleteMapping("/appointment")
    public void cancellingAppointment(@RequestParam(value = "clientName") String clientName, @RequestParam(value = "appName") String appName) {
        System.out.println("SPRING: canceling appointment - " + appName);
        scheduler.cancelAppointment(clientName, appName);
        System.out.println(scheduler.appointments.keySet());
    }

    @CrossOrigin
    @GetMapping("/appointment")
    public List<String> registerAppointment(@RequestParam(value = "clientName") String clientName, @RequestParam(value = "dateTime") long dateNumber) {
        System.out.println("SPRING: consulting appointment for " + clientName);
        Timestamp dateTime = new Timestamp(dateNumber);
        return scheduler.queryAppointments(clientName, dateTime);
    }

    @CrossOrigin
    @PutMapping("/appointment")
    public void registerAppointment(@RequestParam(value = "clientName") String clientName, @RequestParam(value = "appName") String appName) {
        System.out.println("SPRING: joining appointment - " + appName);
        scheduler.participateInAppointment(clientName, appName);
    }
}
