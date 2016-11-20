//package com.sbt.accounting;


import com.sbt.accounting.Proccessing;
import com.sbt.project.Document;
import com.sbt.project.MessageReceiver;
import com.sbt.project.MessageReceiverOne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by vsshm_000 on 19.11.2016.
 */
public class AccountingEngine {
    public static void main(String[] args) throws Exception {

        try {
            MessageReceiverOne messageReceiver = new MessageReceiverOne();
            SessionFactory sessionFactory = null;
            Session session = null;
            try {
                Proccessing proccessing = new Proccessing();
                sessionFactory = new Configuration().configure().buildSessionFactory();
                session = sessionFactory.openSession();
                System.out.printf("To processing=" );

                session.beginTransaction();
                System.out.printf("Proccesing done=");
                Query queryDocuments = session.createQuery("from Document d where d.id = :id ");
                String doc_id=messageReceiver.recciev();
                System.out.printf("Proccesing done="+doc_id);
               List<Document> documents = queryDocuments.setParameter("id", 2).list();
             //    System.out.printf("Proccesing done=" + doc);
                for (Document doc : documents) {
                    proccessing.procces(doc);
                    System.out.printf("Proccesing done=" + doc);
                    session.save(doc); 
             }
                session.getTransaction().commit();
            } catch (HibernateException e) {
                System.out.println("Problem creating session factory");
                e.printStackTrace();
                if (session != null) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (sessionFactory != null) {
                    System.out.println("sessionFactory != null");
                    sessionFactory.close();
                }
            }
        }  catch (Exception e) {
            System.out.println("Problem Exception e");
            e.printStackTrace();
        }
    }
}
