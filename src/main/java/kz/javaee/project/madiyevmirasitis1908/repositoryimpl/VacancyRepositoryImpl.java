package kz.javaee.project.madiyevmirasitis1908.repositoryimpl;


import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.Vacancy;
import kz.javaee.project.madiyevmirasitis1908.repository.VacancyRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class VacancyRepositoryImpl implements VacancyRepository {

  @Override
  public List<Vacancy> getAll() {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    return session.createQuery("SELECT v FROM Vacancy v", Vacancy.class).getResultList();
  }

  @Override
  public Vacancy findById(Long id, Session session) {
    return session.get(Vacancy.class, id);
  }

  @Override
  public void save(Vacancy t, Session session) {
    session.save(t);
  }

  @Override
  public void update(Vacancy t, Session session) {
    session.update(t);
  }

  @Override
  public void delete(Vacancy t, Session session) {
    session.delete(t);
  }


}
