package kz.javaee.project.madiyevmirasitis1908.controller;




import kz.javaee.project.madiyevmirasitis1908.model.Building;
import kz.javaee.project.madiyevmirasitis1908.service.BuildingService;


import javax.annotation.security.PermitAll;
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
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Building> result = buildingService.getAll();
        logger.info("Getting all info about buildings, result - " + result);
        return Response.ok().entity(result).build();
    }

    @RolesAllowed({"ADMIN"})
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
    @POST
    @Path("/createBuilding")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveBuilding(Building building)
    {
        try {
            buildingService.createNewBuilding(building);
            logger.info("Creating new building, result - " + building);
            return Response.ok().build();
        } catch (Exception e) {
            logger.info("Exception - " + e);
            return Response.serverError().build();
        }
    }

    @RolesAllowed({"ADMIN"})
    @PUT
    @Path("/updateBuilding")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBuilding(Building building) {
        try {
            buildingService.updateBuilding(building);
            logger.info("Building successfully updated");
            return Response.ok().build();
        } catch (Exception e) {
            logger.info("Exception - " + e);
            return Response.serverError().build();
        }
    }

    @RolesAllowed({"ADMIN"})
    @DELETE
    @Path("/deleteBuilding/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            buildingService.deleteBuildingById(id);
            logger.info("Building with id " + id + " deleted");
            return Response.ok().build();
        } catch (Exception e) {
            logger.info("Exception - " + e);
            return Response.serverError().build();
        }
    }

}
