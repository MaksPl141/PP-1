package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void clearTables() {
      Session session = sessionFactory.getCurrentSession();

      session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();

      session.createQuery("DELETE FROM User").executeUpdate();
      session.createQuery("DELETE FROM Car").executeUpdate();

      session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
   }

   @Override
   public User findOwnerByCar(String model, int series) {
      String hql = "from User u where u.car.model = :model and u.car.series = :series";

      Query query = sessionFactory.getCurrentSession().createQuery(hql);

      query.setParameter("model", model);
      query.setParameter("series", series);

      return (User) query.uniqueResult();
   }
}
