package kz.javaee.project.madiyevmirasitis1908.repositoryimpl;

import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.Building;
import kz.javaee.project.madiyevmirasitis1908.repository.BuildingRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class BuildingRepositoryImpl implements BuildingRepository {

  @Override
  public List<Building> getAll() {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    return session.createQuery("SELECT b FROM  Building b", Building.class).getResultList();
  }

  @Override
  public Building findById(Long id, Session session) {
    return session.get(Building.class, id);
  }

  @Override
  public void save(Building t, Session session) {
    session.save(t);
  }

  @Override
  public void update(Building t, Session session) {
    session.update(t);
  }

  @Override
  public void delete(Building t, Session session) {
    session.delete(t);
  }

}
