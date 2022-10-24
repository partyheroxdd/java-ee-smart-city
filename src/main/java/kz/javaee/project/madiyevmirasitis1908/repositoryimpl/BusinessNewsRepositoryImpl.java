package kz.javaee.project.madiyevmirasitis1908.repositoryimpl;

import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.BusinessNews;
import kz.javaee.project.madiyevmirasitis1908.repository.BuildingRepository;
import kz.javaee.project.madiyevmirasitis1908.repository.BusinessNewsRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class BusinessNewsRepositoryImpl implements BusinessNewsRepository {

  @Override
  public List<BusinessNews> getAll() {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    return session.createQuery("SELECT b FROM BusinessNews  b", BusinessNews.class).getResultList();
  }

  @Override
  public BusinessNews findById(Long id, Session session) {
    return session.get(BusinessNews.class, id);
  }

  @Override
  public void save(BusinessNews t, Session session) {
    session.save(t);
  }

  @Override
  public void update(BusinessNews t, Session session) {
    session.update(t);
  }

  @Override
  public void delete(BusinessNews t, Session session) {
    session.delete(t);
  }

}
