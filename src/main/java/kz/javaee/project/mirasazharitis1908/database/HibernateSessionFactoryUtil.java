package kz.javaee.project.mirasazharitis1908.database;


import kz.javaee.project.mirasazharitis1908.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

  private static SessionFactory sessionFactory;

  private HibernateSessionFactoryUtil() {
  }

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Vacancy.class);
        configuration.addAnnotatedClass(TouristicPlace.class);
        configuration.addAnnotatedClass(StudentPlace.class);
        configuration.addAnnotatedClass(BusinessNews.class);
        configuration.addAnnotatedClass(Building.class);
        configuration.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());

      } catch (Exception e) {
        System.out.println("Exception!" + e);
      }
    }
    return sessionFactory;
  }
}
