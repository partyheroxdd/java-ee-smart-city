package kz.javaee.project.madiyevmirasitis1908.controller;


import kz.javaee.project.madiyevmirasitis1908.model.Vacancy;
import kz.javaee.project.madiyevmirasitis1908.service.VacancyService;

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

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Vacancy> result = vacancyService.getAll();
        logger.info("Getting all info vacancies, result - " + result);
        return Response.ok().entity(result).build();
    }

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

    @POST
    @Path("/createVacancy")
    public Response saveVacancy(
            @FormParam("title") String title, @FormParam("company") String company,
            @FormParam("description") String description, @FormParam("salary") double salary,
            @FormParam("createdAt") String createdAt)
    {
        try {
            Vacancy vacancy = new Vacancy();
            vacancy.setTitle(title);
            vacancy.setCompany(company);
            vacancy.setDescription(description);
            vacancy.setSalary(salary);
            vacancy.setCreatedAt(createdAt);
            vacancyService.createNewVacancy(vacancy);
            logger.info("Creating new vacancy, result - " + vacancy);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/updateVacancy/{id}")
    public Response updateVacancy(@FormParam("salary") double salary, @PathParam("id") Long id) {
        try {
            vacancyService.updateVacancySalary(id, salary);
            logger.info("Vacancy successfully updated");
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

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