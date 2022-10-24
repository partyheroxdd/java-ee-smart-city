package kz.javaee.project.madiyevmirasitis1908.service;

import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.StudentPlace;
import kz.javaee.project.madiyevmirasitis1908.repository.StudentPlaceRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class StudentService {

  @EJB
  StudentPlaceRepository studentPlaceRepository;

  public List<StudentPlace> getAll() {
    return studentPlaceRepository.getAll();
  }

  public StudentPlace getStudentPlaceById(Long id) {
    return studentPlaceRepository.findById(id,
        HibernateSessionFactoryUtil.getSessionFactory().openSession());
  }

  public void createNewStudentPlace(StudentPlace studentPlace) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();
    studentPlaceRepository.save(studentPlace, session);
    tx1.commit();
    session.close();
  }

  public void updateStudentPlace(StudentPlace studentPlace) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();
    studentPlaceRepository.update(studentPlace, session);
    tx1.commit();
    session.close();
  }


  public void deleteStudentPlaceById(Long id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();
    StudentPlace studentPlace = studentPlaceRepository.findById(id, session);
    studentPlaceRepository.delete(studentPlace, session);
    tx1.commit();
    session.close();
  }
}
