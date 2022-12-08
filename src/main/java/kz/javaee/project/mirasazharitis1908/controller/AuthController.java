package kz.javaee.project.mirasazharitis1908.controller;


import kz.javaee.project.mirasazharitis1908.Security.JWTService;
import kz.javaee.project.mirasazharitis1908.model.User;
import kz.javaee.project.mirasazharitis1908.service.UserService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Path("/auth")

public class AuthController implements ExceptionMapper {

  @EJB
  JWTService jwtService;

  @EJB
  UserService userService;


  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @PermitAll
  @Path("/getJWT")
  public Response JWTAuthorization(User authenticationData) {
    User user = userService.authenticate(authenticationData.getLogin(),
        authenticationData.getPassword());
    if (user == null) {
      return Response.status(500)
          .entity("Wrong Data was entered" + "!")
          .build();
    } else {
      String JWT_User = jwtService.generateJWTToken(user);
      return Response.ok().entity(JWT_User).build();
    }

  }


  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/register")
  @PermitAll
  public Response register(User user) {
    userService.createNewUser(user);
    if (user == null) {
      return Response.status(500)
          .entity("User with this login has been already registered!")
          .build();
    }
    User JWT = userService.authenticate(user.getLogin(), user.getPassword());
    if (JWT == null) {
      return Response.status(500)
          .entity("Wrong Data was entered" + "!")
          .build();
    } else {
      String JWT_User = jwtService.generateJWTToken(user);
      return Response.ok().entity("User has been successfully created!\nJWT: " + JWT_User).build();
    }


  }


  @Override
  public Response toResponse(Throwable throwable) {
    return Response.status(500)
        .entity("ATTENTION! ERROR HANDLER IS FOUND A NEW ERROR")
        .build();
  }


}