import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class HelloApplication extends Application {

  @GET
  @Path("/")
  public String hello() {
    HibernateSessionFactoryUtil.getSessionFactory().openSession();
    return "Hello";
  }
}