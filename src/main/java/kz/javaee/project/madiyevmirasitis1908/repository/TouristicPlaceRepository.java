package kz.javaee.project.madiyevmirasitis1908.repository;

import kz.javaee.project.madiyevmirasitis1908.model.TouristicPlace;
import org.hibernate.Session;

import java.util.List;

public interface TouristicPlaceRepository {
    List<TouristicPlace> getAll();
    TouristicPlace findById(Long id, Session session);
    void save(TouristicPlace t, Session session);
    void update(TouristicPlace t, Session session);
    void delete(TouristicPlace t, Session session);
}
