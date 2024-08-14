package ar.edu.utn.frc.tup.lciii.repositories.impl;

import ar.edu.utn.frc.tup.lciii.entity.GamePropertyEntity;
import ar.edu.utn.frc.tup.lciii.entity.PropertyEntity;
import ar.edu.utn.frc.tup.lciii.entity.UserEntity;
import ar.edu.utn.frc.tup.lciii.repositories.PropertyRepository;
import ar.edu.utn.frc.tup.lciii.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PropertyRepositoryImpl implements PropertyRepository {

    @Override
    public List<GamePropertyEntity> getAllPropertiesByGameId(Long id) {

        List<GamePropertyEntity> gamePropertyEntities;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM GamePropertyEntity Where game.id = :gameId";
            Query<GamePropertyEntity> query = session.createQuery(hql, GamePropertyEntity.class);
            query.setParameter("gameId", id);
            gamePropertyEntities = query.list();
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
        return gamePropertyEntities;
    }
    @Override
    public List<PropertyEntity> getAllProperties() {

        List<PropertyEntity> propertyEntities;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM PropertyEntity";
            propertyEntities= session.createQuery(hql, PropertyEntity.class).list();
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
        return propertyEntities;
    }

    @Override
    public PropertyEntity getById(Long id) {
        PropertyEntity propertyEntity = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM PropertyEntity p WHERE p.id = :id";
            Query<PropertyEntity> query = session.createQuery(hql, PropertyEntity.class);
            query.setParameter("id", id);

            // Obtener el resultado único
            propertyEntity = query.uniqueResult();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return propertyEntity;
    }

//    @Override
//    public List<PropertyEntity> saveProperties(List<PropertyEntity> properties) {
//        List<PropertyEntity> propertyEntities;
//        Transaction transaction = null;
//        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            propertyEntities = (List<PropertyEntity>) session.save(properties);
//            transaction.commit();
//        }catch (Exception e){
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            throw  e;
//        }
//        return propertyEntities;
//    }
}
