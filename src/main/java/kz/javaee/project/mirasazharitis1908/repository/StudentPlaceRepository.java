package kz.javaee.project.mirasazharitis1908.repository;

import kz.javaee.project.mirasazharitis1908.model.StudentPlace;
import org.hibernate.Session;

import java.util.List;

public interface StudentPlaceRepository {

  List<StudentPlace> getAll();

  StudentPlace findById(Long id, Session session);

  void save(StudentPlace t, Session session);

  void update(StudentPlace t, Session session);

  void delete(StudentPlace t, Session session);
}
