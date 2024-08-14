package ar.edu.utn.frc.tup.lciii.repositories.impl;

import ar.edu.utn.frc.tup.lciii.entity.CardEntity;

import ar.edu.utn.frc.tup.lciii.entity.CardGameEntity;
import ar.edu.utn.frc.tup.lciii.entity.GameEntity;
import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.repositories.CardRepository;
import ar.edu.utn.frc.tup.lciii.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CardRepositoryImpl implements CardRepository {

    @Override
    public List<CardEntity> getChanceCards() {
      String description = "'Suerte'";
        return getCards(description);
    }

    @Override
    public List<CardEntity> getDestinyCards() {
      String description = "'Destino'";
        return getCards(description);
    }

    @Override
    public List<CardGameEntity> getChanceCardsByGameId(Long id) {
        Transaction transaction = null;
        List<CardGameEntity> cardEntities;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM CardGameEntity ce WHERE ce.card.cardType.id = 1 and ce.game.id=:id";
            Query<CardGameEntity> query = session.createQuery(hql, CardGameEntity.class);
            query.setParameter("id", id);

            cardEntities = query.list();

            //      String hql = "FROM CardEntity";
            transaction.commit();
            return cardEntities;
        } catch (Exception e) {
            if (transaction != null) {
                return null;
                // transaction.rollback();

            }
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CardGameEntity> getDestinyCardsByGameId(Long id) {
        Transaction transaction = null;
        List<CardGameEntity> cardEntities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM CardGameEntity ce WHERE ce.card.cardType.id = 2 and ce.game.id=:id";
            Query<CardGameEntity> query = session.createQuery(hql, CardGameEntity.class);
            query.setParameter("id", id);

            cardEntities = query.list();

            //      String hql = "FROM CardEntity";
            transaction.commit();
            return cardEntities;
        } catch (Exception e) {
            if (transaction != null) {
                return null;
                // transaction.rollback();

            }
            //e.printStackTrace();
            return cardEntities;
        }
    }

    public List<CardEntity> getCards(String description){
        Transaction transaction = null;
        List<CardEntity> cardEntities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
           String hql = "FROM CardEntity ce WHERE ce.cardType.description ="+description;

      //      String hql = "FROM CardEntity";
            cardEntities = session.createQuery(hql, CardEntity.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                return null;
               // transaction.rollback();

            }
            //e.printStackTrace();
        }
        return cardEntities;

    }
    public List<CardGameEntity> getCards(String description,Long id){
        Transaction transaction = null;
        List<CardGameEntity> cardGameEntities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM CardGameEntity cge WHERE cge.game.id =:idGame and cge.card.cardType.description ="+description;
            Query<CardGameEntity> query = session.createQuery(hql, CardGameEntity.class);
            query.setParameter("idGame", id);
            cardGameEntities = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                return null;
                //transaction.rollback();
            }
            //e.printStackTrace();
        }
        return cardGameEntities;

    }

    @Override
    public CardEntity getById(Long id) {
        CardEntity cardEntity = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM CardEntity p WHERE p.id = :id";
            Query<CardEntity> query = session.createQuery(hql, CardEntity.class);
            query.setParameter("id", id);

            // Obtener el resultado único
            cardEntity = query.uniqueResult();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return cardEntity;

    }
}
