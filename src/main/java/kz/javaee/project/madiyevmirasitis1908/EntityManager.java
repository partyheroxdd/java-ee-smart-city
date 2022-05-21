package kz.javaee.project.madiyevmirasitis1908;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
public class EntityManager {

    public javax.persistence.EntityManager manager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        javax.persistence.EntityManager em = emf.createEntityManager();
        return em;
    }
}
