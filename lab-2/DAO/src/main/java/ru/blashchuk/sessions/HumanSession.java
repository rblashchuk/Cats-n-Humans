package ru.blashchuk.sessions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.blashchuk.CatDao;
import ru.blashchuk.HumanDao;

import java.util.List;
import java.util.Set;


public class HumanSession {

    public void createHuman(HumanDao humanDao) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(humanDao);
        tx1.commit();
        session.close();
    }

    public HumanDao readHuman(long id) {
        return SessionFactory.getSessionFactory().openSession().get(HumanDao.class, id);
    }

    public void updateHuman(HumanDao humanDao) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(humanDao);
        tx1.commit();
        session.close();
    }

    public void deleteHuman(long id) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        HumanDao humanDao = readHuman(id);
        session.delete(humanDao);
        tx1.commit();
        session.close();
    }
}