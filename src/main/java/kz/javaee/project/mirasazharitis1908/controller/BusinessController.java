package kz.javaee.project.mirasazharitis1908.controller;


import kz.javaee.project.mirasazharitis1908.model.BusinessNews;
import kz.javaee.project.mirasazharitis1908.service.BusinessService;


import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/business")
public class BusinessController {

  @EJB
  BusinessService businessService;

  static Logger logger = Logger.getLogger(BusinessController.class.getName());

  @PermitAll
  @GET
  @Path("/getAll")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAll() {
    List<BusinessNews> result = businessService.getAll();
    logger.info("Getting all info about news, result - " + result);
    return Response.ok().entity(result).build();
  }

  @RolesAllowed({"ADMIN", "OWNER"})
  @GET
  @Path("getNews/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNews(@PathParam("id") Long id) {
    try {
      BusinessNews businessNews = businessService.getBusinessNewsById(id);
      logger.info("Getting business news by id , result - " + businessNews);
      return Response
          .status(Response.Status.OK)
          .entity((businessNews))
          .build();
    } catch (Exception e) {
      logger.info("Exception - " + e);
      String message = "News not found";
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity(message)
          .build();
    }
  }

  @RolesAllowed({"ADMIN", "OWNER"})
  @POST
  @Path("/createNews")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response saveNews(BusinessNews businessNews) {
    try {
      businessService.createNewBusinessNews(businessNews);
      logger.info("Creating new business news, result - " + businessNews);
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

  @RolesAllowed({"ADMIN", "OWNER"})
  @PUT
  @Path("/updateNews")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateNews(BusinessNews businessNews) {
    try {
      businessService.updateBusinessNews(businessNews);
      logger.info("Business news successfully updated");
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

  @RolesAllowed({"ADMIN", "OWNER"})
  @DELETE
  @Path("/deleteNews/{id}")
  public Response deleteById(@PathParam("id") Long id) {
    try {
      businessService.deleteBusinessNewsById(id);
      logger.info("Business news with id " + id + " deleted");
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

}
