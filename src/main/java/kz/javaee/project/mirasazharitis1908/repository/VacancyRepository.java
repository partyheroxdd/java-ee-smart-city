package kz.javaee.project.mirasazharitis1908.repository;

import kz.javaee.project.mirasazharitis1908.model.Vacancy;
import org.hibernate.Session;

import java.util.List;


public interface VacancyRepository {

  List<Vacancy> getAll();

  Vacancy findById(Long id, Session session);

  void save(Vacancy t, Session session);

  void update(Vacancy t, Session session);

  void delete(Vacancy t, Session session);
}

