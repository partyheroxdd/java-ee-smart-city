package kz.javaee.project.madiyevmirasitis1908.controller;

import kz.javaee.project.madiyevmirasitis1908.model.TouristicPlace;
import kz.javaee.project.madiyevmirasitis1908.service.TouristicService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/tourism")
public class TourismController {

    @EJB
    TouristicService touristicService;

    static Logger logger = Logger.getLogger(TourismController.class.getName());

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<TouristicPlace> result = touristicService.getAll();
        logger.info("Getting all info tourist places, result - " + result);
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("getTouristicPlace/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTouristicPlace(@PathParam("id") Long id) {
        try {
            TouristicPlace touristicPlace = touristicService.getTouristicPlaceById(id);
            logger.info("Getting student place by id , result - " + touristicPlace);
            return Response
                    .status(Response.Status.OK)
                    .entity((touristicPlace))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            String message = "Touristic place not found";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .build();
        }
    }

    @POST
    @Path("/createTouristicPlace")
    public Response saveTouristicPlace(
            @FormParam("name") String name, @FormParam("type") String type,
            @FormParam("address") String address, @FormParam("contactInfo") String contactInfo)
    {
        try {
            TouristicPlace touristicPlace = new TouristicPlace();
            touristicPlace.setName(name);
            touristicPlace.setType(type);
            touristicPlace.setAddress(address);
            touristicPlace.setContactInfo(contactInfo);
            touristicService.createNewTouristicPlace(touristicPlace);
            logger.info("Creating new touristic place, result - " + touristicPlace);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/updateTouristicPlace/{id}")
    public Response updateTouristicPlace(@FormParam("address") String address, @PathParam("id") Long id) {
        try {
            touristicService.updateTouristicPlaceAddress(id, address);
            logger.info("Touristic place successfully updated");
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/deleteTouristicPlace/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            touristicService.deleteTouristicPlaceById(id);
            logger.info("Touristic place with id " + id + " deleted");
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}