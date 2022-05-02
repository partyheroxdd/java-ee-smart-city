package kz.javaee.project.madiyevmirasitis1908.repositoryimpl;

import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.TouristicPlace;
import kz.javaee.project.madiyevmirasitis1908.repository.TouristicPlaceRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;
@Stateful
public class TouristicPlaceRepositoryImpl implements TouristicPlaceRepository {

    @Override
    public List<TouristicPlace> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT t FROM TouristicPlace t", TouristicPlace.class).getResultList();
    }

    @Override
    public TouristicPlace findById(Long id, Session session) {
        return session.get(TouristicPlace.class, id);
    }

    @Override
    public void save(TouristicPlace t, Session session) {
        session.save(t);
    }

    @Override
    public void update(TouristicPlace t, Session session) {
        session.update(t);
    }

    @Override
    public void delete(TouristicPlace t, Session session) {
        session.delete(t);
    }
}
