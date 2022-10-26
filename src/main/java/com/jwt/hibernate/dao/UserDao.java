package com.jwt.hibernate.dao;

import com.jwt.hibernate.bean.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDao {
    public boolean validate(String userName, String password) {
        Users user = null;
        try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();

            // 3. Get Session object
            Session session = sessionFactory.openSession();

            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            user = (Users) session.createQuery("SELECT U FROM Users U WHERE U.userName = :userName").setParameter("userName", userName).uniqueResult();
            System.out.println(user);

            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
        return false;
    }
}
