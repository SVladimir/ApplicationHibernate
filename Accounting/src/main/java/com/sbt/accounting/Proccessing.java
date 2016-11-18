package com.sbt.accounting;

import com.sbt.project.Account;
import com.sbt.project.Document;
import com.sbt.project.IProccesing;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.*;

import java.math.BigDecimal;

/**
 * Created by vsshm_000 on 02.11.2016.
 */
public class Proccessing implements IProccesing {

    @Override
    public void procces(Document document) {
        SessionFactory sessionFactory = null;
        Session session = null;

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            Account accountDt = null;
            Account accountCt = null;
            BigDecimal sum = BigDecimal.valueOf(0);
            sum = sum.subtract(document.getSum());
            accountDt = document.getAccountDt();
            accountCt = document.getAccountCt();
            accountDt.setSaldo(accountDt.getSaldo().subtract(document.getSum()));
            accountCt.setSaldo(accountCt.getSaldo().subtract(document.getSum()));
            session.beginTransaction();
            session.save(accountDt);
            session.save(accountCt);
            session.save(document);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Problem creating session factory");

            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }

    }
}
