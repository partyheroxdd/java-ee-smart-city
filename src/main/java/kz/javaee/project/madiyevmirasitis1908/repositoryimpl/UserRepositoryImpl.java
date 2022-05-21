package kz.javaee.project.madiyevmirasitis1908.repositoryimpl;

import kz.javaee.project.madiyevmirasitis1908.EntityManager;
import kz.javaee.project.madiyevmirasitis1908.database.HibernateSessionFactoryUtil;
import kz.javaee.project.madiyevmirasitis1908.model.User;
import kz.javaee.project.madiyevmirasitis1908.repository.UserRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Stateful
public class UserRepositoryImpl implements UserRepository {
    @Inject
    EntityManager em;
    @Override
    public List<User> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User findById(Long id, Session session) {
        return session.get(User.class, id);
    }

    @Override
    public void save(User u, Session session) {
        session.save(u);
    }

    @Override
    public void update(User u, Session session) {
        session.update(u);
    }

    @Override
    public void delete(User u, Session session) {
        session.delete(u);
    }

    @Override
    public User authenticate(String login, String password) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT u FROM User u WHERE u.login = '%s' AND u.password = '%s'", login, password);
            Query query = entityManager.createQuery(sql);
            User result = (User) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }
}
