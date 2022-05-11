package kz.javaee.project.madiyevmirasitis1908.controller;



import Security.JWTToken;
import Security.ListFilterInt;
import kz.javaee.project.madiyevmirasitis1908.Aop.LoggerProduce;
import kz.javaee.project.madiyevmirasitis1908.model.Building;
import kz.javaee.project.madiyevmirasitis1908.service.BuildingService;


import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/admin")
public class AdministrationController {

    @EJB
    BuildingService buildingService;

    static Logger logger = Logger.getLogger(AdministrationController.class.getName());

    @RolesAllowed({"ADMIN"})
    @JWTToken
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Building> result = buildingService.getAll();
        logger.info("Getting all info about buildings, result - " + result);
        return Response.ok().entity(result).build();
    }

    @RolesAllowed({"ADMIN"})
    @JWTToken
    @GET
    @Path("getBuilding/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuilding(@PathParam("id") Long id) {
        try {
            Building building = buildingService.getBuildingById(id);
            logger.info("Getting building by id, result - " + building);
            return Response
                    .status(Response.Status.OK)
                    .entity((building))
                    .build();
        } catch (Exception e) {
            logger.info("Exception - " + e);
            String message = "Building not found";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .build();
        }

    }

    @RolesAllowed({"ADMIN"})
    @JWTToken
    @POST
    @Path("/createBuilding")
    public Response saveBuilding(
            @FormParam("name") String name, @FormParam("category") String category,
            @FormParam("address") String address, @FormParam("contactInfo") String contactInfo)
    {
        try {
            Building building = new Building();
            building.setName(name);
            building.setCategory(category);
            building.setAddress(address);
            building.setContactInfo(contactInfo);
            buildingService.createNewBuilding(building);
            logger.info("Creating new building, result - " + building);
            return Response.ok().build();
        } catch (Exception e) {
            logger.info("Exception - " + e);
            return Response.serverError().build();
        }
    }

    @RolesAllowed({"ADMIN"})
    @JWTToken
    @PUT
    @Path("/updateBuilding/{id}")
    public Response updateBuilding(@FormParam("name") String name, @FormParam("category") String category,
                                   @FormParam("address") String address, @FormParam("contactInfo") String contactInfo,
                                   @PathParam("id") Long id) {
        try {
            buildingService.updateBuilding(id, name, category, address, contactInfo);
            logger.info("Building successfully updated");
            return Response.ok().build();
        } catch (Exception e) {
            logger.info("Exception - " + e);
            return Response.serverError().build();
        }
    }

    @RolesAllowed({"ADMIN"})
    @JWTToken
    @DELETE
    @Path("/deleteBuilding/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            buildingService.deleteBuilidngById(id);
            logger.info("Building with id " + id + " deleted");
            return Response.ok().build();
        } catch (Exception e) {
            logger.info("Exception - " + e);
            return Response.serverError().build();
        }
    }

}
