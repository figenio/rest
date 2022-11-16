package com.example.restserver;

import jakarta.ws.rs.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Path("/hello-world")
public class ServerHelloResource {
    // Simple standard method to test server connection
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}


