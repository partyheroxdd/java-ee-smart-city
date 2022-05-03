package Security;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;



@Provider
@JWTToken
@Priority(Priorities.AUTHENTICATION)
public class JWTFilterNeed implements ContainerRequestFilter {



    @Context
    private ResourceInfo resourceInfo;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        Method method = resourceInfo.getResourceMethod();


        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Access blocked for all users !!").build());
                return;
            }

            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);



            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                throw new NotAuthorizedException("Authorization header must be provided");
            }


            String token = authorizationHeader.substring("Bearer".length()).trim();
            try {


                if (token.length() > 10) {
                } else {
                    throw new SecurityException("Invalid token!");
                }


                if (method.isAnnotationPresent(RolesAllowed.class)) {
                    RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                    Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));


                    if (!isUserAllowed(token, rolesSet)) {
                        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                .entity("You cannot access this resource").build());
                        return;
                    }
                }
            } catch (Exception e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }

        }
    }


    private boolean isUserAllowed(final String token, final Set<String> rolesSet) {
        boolean isAllowed = false;
        String userRole = "";


        if (token.equals("2aU02dqxYLwStknXJFwB")) {
            userRole = "ADMIN";
        } else if (token.equals("e55KNLny4Nv5cYkS8C4k")) {
            userRole = "OWNER";
        } else userRole = "USER";

        if (rolesSet.contains(userRole)) {
            isAllowed = true;
        }
        return isAllowed;
    }
}