package kz.javaee.project.madiyevmirasitis1908.service;

import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.TouristicPlace;
import kz.javaee.project.madiyevmirasitis1908.repository.TouristicPlaceRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class TouristicService {
    @EJB
    TouristicPlaceRepository touristicPlaceRepository;

    public List<TouristicPlace> getAll(){ return touristicPlaceRepository.getAll();}

    public TouristicPlace getTouristicPlaceById(Long id) {
        return touristicPlaceRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public void createNewTouristicPlace(TouristicPlace touristicPlace) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        touristicPlaceRepository.save(touristicPlace, session);
        tx1.commit();
        session.close();
    }

    public void updateTouristicPlaceAddress(Long id, String address) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        TouristicPlace touristicPlace = touristicPlaceRepository.findById(id, session);
        touristicPlace.setAddress(address);
        touristicPlaceRepository.update(touristicPlace,session);
        tx1.commit();
        session.close();
    }

    public void deleteTouristicPlaceById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        TouristicPlace touristicPlace = touristicPlaceRepository.findById(id,session);
        touristicPlaceRepository.delete(touristicPlace, session);
        tx1.commit();
        session.close();
    }

}
