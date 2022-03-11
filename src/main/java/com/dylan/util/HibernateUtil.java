package com.dylan.util;
//package hibernate;


import com.dylan.model.Game;
import com.dylan.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static Session getSession() throws IOException {
        if (sessionFactory == null) {

            Configuration config = new Configuration();
            Properties props = new Properties();

            FileInputStream propertiesInput = new FileInputStream("C:\\Users\\djmcf\\hibernate.properties");
            props.load(propertiesInput);

            config.setProperties(props);

            //we need to include our mapped objects****

            config.addAnnotatedClass(User.class);
            config.addAnnotatedClass(Game.class);



            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

            sessionFactory = config.buildSessionFactory(serviceRegistry);

        }
        return sessionFactory.openSession();


        }
    }
