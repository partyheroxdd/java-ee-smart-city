package kz.javaee.project.mirasazharitis1908.repositoryimpl;

import kz.javaee.project.mirasazharitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.mirasazharitis1908.model.StudentPlace;
import kz.javaee.project.mirasazharitis1908.repository.StudentPlaceRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class StudentPlaceRepositoryImpl implements StudentPlaceRepository {

  @Override
  public List<StudentPlace> getAll() {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    return session.createQuery("SELECT s FROM StudentPlace s", StudentPlace.class).getResultList();
  }

  @Override
  public StudentPlace findById(Long id, Session session) {
    return session.get(StudentPlace.class, id);
  }

  @Override
  public void save(StudentPlace t, Session session) {
    session.save(t);
  }

  @Override
  public void update(StudentPlace t, Session session) {
    session.update(t);
  }

  @Override
  public void delete(StudentPlace t, Session session) {
    session.delete(t);
  }

}
