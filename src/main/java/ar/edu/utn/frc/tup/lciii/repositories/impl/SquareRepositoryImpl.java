package ar.edu.utn.frc.tup.lciii.repositories.impl;

import ar.edu.utn.frc.tup.lciii.entity.*;
import ar.edu.utn.frc.tup.lciii.repositories.SquareRepository;
import ar.edu.utn.frc.tup.lciii.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SquareRepositoryImpl implements SquareRepository {

    @Override
    public List<SquareEntity> getAllSquares() {
        List<SquareEntity> squareEntities;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
        transaction = session.beginTransaction();
        String hql = "FROM SquareEntity ";
        squareEntities = session.createQuery(hql,SquareEntity.class).list();
        transaction.commit();
    }catch (Exception e){

        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
        throw  e;

    }
    return squareEntities;
    }

//    @Override
//    public List<PlayerSquareEntity> getAllSquaresByGameId(Long gameId) {
//        List<PlayerSquareEntity> playerSquareEntities;
//        Transaction transaction = null;
//
//        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            transaction = session.beginTransaction();
//            String hql = "FROM PlayerSquareEntity pse WHERE pse.player.game.id = :gameId ";
//            Query<PlayerSquareEntity> query = session.createQuery(hql,PlayerSquareEntity.class);
//            query.setParameter("gameId",gameId);
//            playerSquareEntities = query.list();
//            transaction.commit();
//        }catch (Exception e){
//
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            throw  e;
//
//        }
//        return playerSquareEntities;
//    }

    public SquareEntity getByNumber(Integer number){
        SquareEntity squareEntity = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM SquareEntity p WHERE p.number = :number";
            Query<SquareEntity> query = session.createQuery(hql, SquareEntity.class);
            query.setParameter("number", number);

            // Obtener el resultado único
            squareEntity = query.uniqueResult();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return squareEntity;
    }

//    @Override
//    public List<SquareEntity> saveSquares(List<SquareEntity> squares) {
//        List<SquareEntity> squareEntities;
//        Transaction transaction = null;
//        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            squareEntities = (List<SquareEntity>) session.save(squares);
//            transaction.commit();
//        }catch (Exception e){
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            throw  e;
//        }
//        return squareEntities;
//    }
}
