package ar.edu.utn.frc.tup.lciii.repositories.impl;

import ar.edu.utn.frc.tup.lciii.entity.GameEntity;
import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;

import ar.edu.utn.frc.tup.lciii.entity.PropertyEntity;
import ar.edu.utn.frc.tup.lciii.repositories.PlayerRepository;
import ar.edu.utn.frc.tup.lciii.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepository {
    @Override
    public List<PlayerEntity> getAllPlayersByGameId(Long gameId) {
        List<PlayerEntity> playerEntities;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM PlayerEntity pe where pe.game.id=:idGame";
            Query<PlayerEntity> query = session.createQuery(hql, PlayerEntity.class);
            query.setParameter("idGame", gameId);
            playerEntities = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
        return playerEntities;
    }

    @Override
    public PlayerEntity getById(Long id) {
        PlayerEntity playerEntity = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM PlayerEntity p WHERE p.id = :id";
            Query<PlayerEntity> query = session.createQuery(hql, PlayerEntity.class);
            query.setParameter("id", id);

            // Obtener el resultado único
            playerEntity = query.uniqueResult();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return playerEntity;
    }

//    @Override
//    public List<PlayerEntity> savePlayers(List<PlayerEntity> players) {
//        List<PlayerEntity> playerEntities;
//        Transaction transaction = null;
//        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            playerEntities = (List<PlayerEntity>) session.save(players);
//            transaction.commit();
//        }catch (Exception e){
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            throw  e;
//        }
//        return playerEntities;
//    }
}
