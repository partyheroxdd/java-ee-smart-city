package kz.javaee.project.madiyevmirasitis1908.repository;

import kz.javaee.project.madiyevmirasitis1908.model.User;
import org.hibernate.Session;

import java.util.List;

public interface UserRepository {
    List<User> getAll();
    User findById(Long id, Session session);
    User authenticate(String login, String password);
    void save(User u, Session session);
    void update(User u, Session session);
    void delete(User u, Session session);
}
