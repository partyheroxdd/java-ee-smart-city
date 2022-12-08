package kz.javaee.project.mirasazharitis1908.controller;


import kz.javaee.project.mirasazharitis1908.model.StudentPlace;
import kz.javaee.project.mirasazharitis1908.service.StudentService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/student")
public class StudentController {

  @EJB
  StudentService studentService;

  static Logger logger = Logger.getLogger(StudentController.class.getName());

  @PermitAll
  @GET
  @Path("/getAll")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAll() {
    List<StudentPlace> result = studentService.getAll();
    logger.info("Getting all info student places, result - " + result);
    return Response.ok().entity(result).build();
  }

  @RolesAllowed({"ADMIN", "OWNER"})
  @GET
  @Path("getStudentPlace/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStudentPlace(@PathParam("id") Long id) {
    try {
      StudentPlace studentPlace = studentService.getStudentPlaceById(id);
      logger.info("Getting student place by id , result - " + studentPlace);

      return Response
          .status(Response.Status.OK)
          .entity((studentPlace))
          .build();
    } catch (Exception e) {
      logger.info("Exception - " + e);
      String message = "Student place not found";
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity(message)
          .build();
    }
  }

  @RolesAllowed({"ADMIN", "OWNER"})
  @POST
  @Path("/createStudentPlace")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response saveStudentPlace(StudentPlace studentPlace) {
    try {
      studentService.createNewStudentPlace(studentPlace);
      logger.info("Creating new student place, result - " + studentPlace);
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

  @RolesAllowed({"ADMIN", "OWNER"})
  @PUT
  @Path("/updateStudentPlace")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateStudentPlace(StudentPlace studentPlace) {
    try {
      studentService.updateStudentPlace(studentPlace);
      logger.info("Student place successfully updated");
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

  @RolesAllowed({"ADMIN", "OWNER"})
  @DELETE
  @Path("/deleteStudentPlace/{id}")
  public Response deleteById(@PathParam("id") Long id) {
    try {
      studentService.deleteStudentPlaceById(id);
      logger.info("Student place with id " + id + " deleted");
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

}
