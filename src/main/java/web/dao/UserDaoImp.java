package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<User> showAllUsers() {
        return entityManager.createQuery("select users from User users", User.class).getResultList();
    }

    @Override
    public User showUserInfo(int id) {return entityManager.find(User.class,id);}

    @Override
    public void saveUser(User user) {entityManager.merge(user);}

    @Override
    public void deleteUser(int id) {;
        entityManager.createQuery("delete from User user where user.id =: userId")
                .setParameter("userId", id)
                .executeUpdate();
    }

    @Override
    public void updateUserInfo(int id, User user) {
        User updatedUser = entityManager.find(User.class, id);
        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(updatedUser.getAge());

    }
}
