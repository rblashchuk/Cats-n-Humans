package ru.blashchuk.sessions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.blashchuk.CatDao;
import ru.blashchuk.HumanDao;

import java.util.List;
import java.util.Set;


public class CatSession {

    public void createCat(CatDao catDao) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(catDao);
        tx1.commit();
        session.close();
    }

    public CatDao readCat(long id) {
        return SessionFactory.getSessionFactory().openSession().get(CatDao.class, id);
    }

    public void updateCat(CatDao catDao) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(catDao);
        tx1.commit();
        session.close();
    }

    public void deleteCat(long id) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        CatDao catDao = readCat(id);
        session.delete(catDao);
        tx1.commit();
        session.close();
    }
}