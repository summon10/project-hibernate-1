package com.game.repository;

import com.game.entity.Player;
import jakarta.annotation.PreDestroy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;


import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository(value = "db")
public class PlayerRepositoryDB implements IPlayerRepository {
    private final SessionFactory sessionFactory;

    public PlayerRepositoryDB() {
        sessionFactory = new Configuration().buildSessionFactory();
    }

    @Override
    public List<Player> getAll(int pageNumber, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createNativeQuery("select * from rpg.player", Player.class);
            query.setFirstResult(pageNumber * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        }

    }

    @Override
    public int getAllCount() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createNamedQuery("player_getAllCount", Long.class);
            return (query.getFirstResult());
        }

    }

    @Override
    public Player save(Player player) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.save(player);
            tr.commit();
            return player;
        }
    }

    @Override
    public Player update(Player player) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.update(player);
            tr.commit();
            return player;
        }
    }

    @Override
    public Optional<Player> findById(long id) {
        try (Session session = sessionFactory.openSession())
        {
            Player player = session.find(Player.class, id);
            return Optional.of(player);
        }

    }

    @Override
    public void delete(Player player) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.remove(player);
            tr.commit();

        }
    }

    @PreDestroy
    public void beforeStop() {
        sessionFactory.close();
    }
}