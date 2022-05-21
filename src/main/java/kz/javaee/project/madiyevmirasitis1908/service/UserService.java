package kz.javaee.project.madiyevmirasitis1908.service;

import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.User;
import kz.javaee.project.madiyevmirasitis1908.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class UserService {
    @EJB
    UserRepository userRepository;

    public List<User> getAll(){ return userRepository.getAll();}

    public User getUserById(Long id) {
        return userRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public User authenticate(String login, String password) {return userRepository.authenticate(login, password);}

    public void createNewUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        userRepository.save(user, session);
        tx1.commit();
        session.close();
    }

    public void updateUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        userRepository.update(user,session);
        tx1.commit();
        session.close();
    }

    public void deleteUserById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = userRepository.findById(id,session);
        userRepository.delete(user, session);
        tx1.commit();
        session.close();
    }


}
