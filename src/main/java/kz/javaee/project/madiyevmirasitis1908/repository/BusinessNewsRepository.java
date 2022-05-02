package kz.javaee.project.madiyevmirasitis1908.repository;

import kz.javaee.project.madiyevmirasitis1908.model.BusinessNews;
import org.hibernate.Session;

import java.util.List;

public interface BusinessNewsRepository {
    List<BusinessNews> getAll();
    BusinessNews findById(Long id, Session session);
    void save(BusinessNews t, Session session);
    void update(BusinessNews t, Session session);
    void delete(BusinessNews t, Session session);
}
