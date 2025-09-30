package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
       Car currentCar = user.getCar();
      sessionFactory.getCurrentSession().save(user);
       sessionFactory.getCurrentSession().save(currentCar);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User findUserByCarParameters(String model, int series) {
       //TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where model = :model and series = :series", Car.class);
       TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select u from User as u where u.car.model = :model and u.car.series = :series", User.class);
       query.setParameter("model", model);
       query.setParameter("series", series);
       query.setMaxResults(1);
       //Car car;
       User result;
       try {
           //car = query.getSingleResult();
           result = Objects.requireNonNull(query.getSingleResult());
       }  catch (Exception e) {
           result = null;
       }
       return result;
   }
}
