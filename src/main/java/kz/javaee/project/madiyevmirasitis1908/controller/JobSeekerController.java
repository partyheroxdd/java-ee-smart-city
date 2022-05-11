package kz.javaee.project.madiyevmirasitis1908.controller;


import Security.JWTToken;
import kz.javaee.project.madiyevmirasitis1908.model.Vacancy;
import kz.javaee.project.madiyevmirasitis1908.service.VacancyService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/jobseeker")
public class JobSeekerController {

    @EJB
    VacancyService vacancyService;

    static Logger logger = Logger.getLogger(JobSeekerController.class.getName());

    @PermitAll
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Vacancy> result = vacancyService.getAll();
        logger.info("Getting all info vacancies, result - " + result);
        return Response.ok().entity(result).build();
    }

    @PermitAll
    @GET
    @Path("getVacancy/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancy(@PathParam("id") Long id) {
        try {
            Vacancy vacancy = vacancyService.getVacancyById(id);
            logger.info("Getting vacancy by id , result - " + vacancy);
            return Response
                    .status(Response.Status.OK)
                    .entity((vacancy))
                    .build();
        } catch (Exception e) {
            logger.info("Exception - " + e);
            String message = "Vacancy not found";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .build();
        }
    }

    @RolesAllowed({"ADMIN", "OWNER"})
    @JWTToken
    @POST
    @Path("/createVacancy")
    public Response saveVacancy(
            @FormParam("title") String title, @FormParam("company") String company,
            @FormParam("description") String description, @FormParam("salary") double salary) {
        try {
            Vacancy vacancy = new Vacancy();
            vacancy.setTitle(title);
            vacancy.setCompany(company);
            vacancy.setDescription(description);
            vacancy.setSalary(salary);
            vacancyService.createNewVacancy(vacancy);
            logger.info("Creating new vacancy, result - " + vacancy);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @RolesAllowed({"ADMIN", "OWNER"})
    @JWTToken
    @PUT
    @Path("/updateVacancy")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateVacancy(Vacancy vacancy) {
        try {
            vacancyService.updateVacancy(vacancy);
            logger.info("Vacancy successfully updated");
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @RolesAllowed({"ADMIN", "OWNER"})
    @JWTToken
    @DELETE
    @Path("/deleteVacancy/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            vacancyService.deleteVacancyById(id);
            logger.info("Vacancy with id " + id + " deleted");
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
