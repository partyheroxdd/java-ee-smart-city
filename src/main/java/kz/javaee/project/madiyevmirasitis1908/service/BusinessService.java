package kz.javaee.project.madiyevmirasitis1908.service;

import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.BusinessNews;
import kz.javaee.project.madiyevmirasitis1908.repository.BusinessNewsRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class BusinessService {
    @EJB
    BusinessNewsRepository businessNewsRepository;

    public List<BusinessNews> getAll(){ return businessNewsRepository.getAll();}

    public BusinessNews getBusinessNewsById(Long id) {
        return businessNewsRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public void createNewBusinessNews(BusinessNews businessNews) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        businessNewsRepository.save(businessNews, session);
        tx1.commit();
        session.close();
    }

    public void updateBusinessNews(BusinessNews businessNews) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        businessNewsRepository.update(businessNews,session);
        tx1.commit();
        session.close();
    }


    public void deleteBusinessNewsById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        BusinessNews businessNews = businessNewsRepository.findById(id,session);
        businessNewsRepository.delete(businessNews, session);
        tx1.commit();
        session.close();
    }
}
