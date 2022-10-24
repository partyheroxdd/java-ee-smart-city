package kz.javaee.project.madiyevmirasitis1908.repository;

import kz.javaee.project.madiyevmirasitis1908.model.Building;
import org.hibernate.Session;

import java.util.List;

public interface BuildingRepository {

  List<Building> getAll();

  Building findById(Long id, Session session);

  void save(Building t, Session session);

  void update(Building t, Session session);

  void delete(Building t, Session session);
}
