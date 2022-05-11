package kz.javaee.project.madiyevmirasitis1908.service;

import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.Building;
import kz.javaee.project.madiyevmirasitis1908.repository.BuildingRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class BuildingService {
    @EJB
    BuildingRepository buildingRepository;

    public List<Building> getAll() {
        return buildingRepository.getAll();
    }

    public Building getBuildingById(Long id) {
        return buildingRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public void createNewBuilding(Building building) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        buildingRepository.save(building, session);
        tx1.commit();
        session.close();
    }

    public void updateBuilding(Building building) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        buildingRepository.update(building, session);
        tx1.commit();
        session.close();
    }


    public void deleteBuildingById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Building building = buildingRepository.findById(id, session);
        buildingRepository.delete(building, session);
        tx1.commit();
        session.close();
    }
}
