package ar.edu.utn.frc.tup.lciii.repositories.impl;

import ar.edu.utn.frc.tup.lciii.entity.UserEntity;
import ar.edu.utn.frc.tup.lciii.repositories.UserRepository;
import ar.edu.utn.frc.tup.lciii.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.processing.HQL;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public UserEntity getByName(String name) {
        UserEntity user = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM UserEntity WHERE name = :name";
            Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
            query.setParameter("name", name);

            // Obtener el resultado único
            user = query.uniqueResult();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return user;
    }

    public UserEntity save(UserEntity entity) {
        UserEntity user = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
        return user;
    }
}
