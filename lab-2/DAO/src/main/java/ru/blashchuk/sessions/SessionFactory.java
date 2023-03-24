package ru.blashchuk.sessions;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.blashchuk.CatDao;
import ru.blashchuk.HumanDao;

public class SessionFactory {
    private static org.hibernate.SessionFactory sessionFactory;

    private SessionFactory() {}

    public static org.hibernate.SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(CatDao.class);
                configuration.addAnnotatedClass(HumanDao.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return sessionFactory;
    }
}