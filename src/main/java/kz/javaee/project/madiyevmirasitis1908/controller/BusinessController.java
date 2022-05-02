package kz.javaee.project.madiyevmirasitis1908.controller;


import kz.javaee.project.madiyevmirasitis1908.model.BusinessNews;
import kz.javaee.project.madiyevmirasitis1908.service.BusinessService;


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

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<BusinessNews> result = businessService.getAll();
        logger.info("Getting all info about news, result - " + result);
        return Response.ok().entity(result).build();
    }

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

    @POST
    @Path("/createNews")
    public Response saveNews(
            @FormParam("title") String title, @FormParam("company") String company,
            @FormParam("description") String description, @FormParam("createdAt") String createdAt) {
        try {
            BusinessNews businessNews = new BusinessNews();
            businessNews.setTitle(title);
            businessNews.setCompany(company);
            businessNews.setDescription(description);
            businessNews.setCreatedAt(createdAt);
            businessService.createNewBusinessNews(businessNews);
            logger.info("Creating new business news, result - " + businessNews);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/updateNews/{id}")
    public Response updateNews(@FormParam("title") String title, @PathParam("id") Long id) {
        try {
            businessService.updateBusinessNews(id, title);
            logger.info("Business news successfully updated");
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

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