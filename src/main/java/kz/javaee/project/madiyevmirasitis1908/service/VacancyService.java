package kz.javaee.project.madiyevmirasitis1908.service;

import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.Vacancy;
import kz.javaee.project.madiyevmirasitis1908.repository.VacancyRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class VacancyService {
    @EJB
    VacancyRepository vacancyRepository;

    public List<Vacancy> getAll(){ return vacancyRepository.getAll();}

    public Vacancy getVacancyById(Long id) {
        return vacancyRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public void createNewVacancy(Vacancy vacancy) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        vacancyRepository.save(vacancy, session);
        tx1.commit();
        session.close();
    }

    public void updateVacancySalary(Long id, double salary) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Vacancy vacancy = vacancyRepository.findById(id, session);
        vacancy.setSalary(salary);
        vacancyRepository.update(vacancy,session);
        tx1.commit();
        session.close();
    }

    public void deleteVacancyById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Vacancy vacancy = vacancyRepository.findById(id,session);
        vacancyRepository.delete(vacancy, session);
        tx1.commit();
        session.close();
    }
}
