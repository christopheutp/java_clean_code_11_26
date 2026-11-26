package com.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/hello-before")
public class ExampleResourceBefore {
    private static final Logger LOGGER = Logger.getLogger(ExampleResourceBefore.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info("Début : hello");
        try {
            return "Hello RESTEasy";
        } finally {
            LOGGER.info("Fin : hello");
        }
    }

    @GET
    @Path("/toto")
    @Produces(MediaType.TEXT_PLAIN)
    public String toto() {
        LOGGER.info("Début : toto");
        try {
            return "Hello RESTEasy from toto";
        } finally {
            LOGGER.info("Fin : toto");
        }
    }
}
