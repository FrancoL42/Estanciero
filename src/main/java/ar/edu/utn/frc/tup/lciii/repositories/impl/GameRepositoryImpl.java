package ar.edu.utn.frc.tup.lciii.repositories.impl;

import ar.edu.utn.frc.tup.lciii.entity.*;
import ar.edu.utn.frc.tup.lciii.repositories.*;
import ar.edu.utn.frc.tup.lciii.utils.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {
    CardRepository cardRepository = new CardRepositoryImpl();
    PlayerRepository playerRepository = new PlayerRepositoryImpl();
    PropertyRepository propertyRepository = new PropertyRepositoryImpl();
    SquareRepository squareRepository = new SquareRepositoryImpl();


    @Override
    public GameEntity getGame(Long gameId) {
        GameEntity gameEntity = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM GameEntity g WHERE g.id = :idGame";
            Query<GameEntity> query = session.createQuery(hql, GameEntity.class);
            query.setParameter("idGame", gameId);

            gameEntity = query.uniqueResult();

            // Cargar las colecciones por separado si es necesario
            if (gameEntity != null) {
                Hibernate.initialize(gameEntity.getCards());
                Hibernate.initialize(gameEntity.getPlayers());
            }
            transaction.commit();

        } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
            e.printStackTrace();
        }}


        return gameEntity;
    }

    @Override
    public List<GameEntity> getGamesByUserId(Long userId) {
        List<GameEntity> gameEntity = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM GameEntity ga where ga.user.id = :id";
            Query<GameEntity> query = session.createQuery(hql, GameEntity.class);
            query.setParameter("id", userId);
            gameEntity = query.list();
            transaction.commit();

        } catch (Exception e) {

            e.printStackTrace();
         if (transaction != null) {
                    transaction.rollback();

                return null;
            }

        }

        return gameEntity;
    }


    @Override
    public <T> void save(List<T> entities) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (T entity : entities)
                session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }

    }
    //Usar en caso de que la mierda de arriba no funcione
//    public GameEntity postGame(GameEntity game){
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.saveOrUpdate(game);
//            transaction.commit();
//            return game;
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            throw e;
//        }
//    }

    @Override
    public GameEntity save(GameEntity gameEntity, List<CardGameEntity> cards, List<GamePropertyEntity> properties,
                     List<PlayerEntity> players) {
        Long gameId;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
             gameId =(Long) session.save(gameEntity);
            GameEntity game = session.get(GameEntity.class, gameId);

            cards.forEach(c->c.setGame(game));
            save(cards, session);
            properties.forEach(p->p.setGame(game));
            save(properties, session);
            players.forEach(p->p.setGame(game));
            save(players, session);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
        return getGame(gameId);

    }

    private <T> void save(List<T> entities, Session session) {
        for (T entity : entities) {
            session.saveOrUpdate(entity);
        }
    }


}
