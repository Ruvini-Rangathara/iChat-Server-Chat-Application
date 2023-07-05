package com.example.ichatserver.model;

import com.example.ichatserver.to.ClientTo;
import com.example.ichatserver.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientModel {

    //save client
    public boolean save(ClientTo client) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(client);
            transaction.commit();
            return true;
        }catch(Exception e){
            System.out.println(e);
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }


    //update client
    public boolean update(ClientTo client) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(client);
            transaction.commit();
            return true;
        }catch(Exception e){
            System.out.println(e);
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }


    //delete client
    public boolean delete(ClientTo client) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(client);
            transaction.commit();
            return true;
        }catch(Exception e){
            System.out.println(e);
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    //search client
    public ClientTo search(String email) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            ClientTo client = session.get(ClientTo.class, email);
            transaction.commit();
            return client;
        }catch(Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    public List<ClientTo> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{

            String hql = "FROM client";
            Query query = session.createQuery(hql);
            List<ClientTo> list= query.list();
            return list;

        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    public String getLastEmail() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            String hql = "SELECT email FROM client ORDER BY email DESC";
            Query query = session.createQuery(hql);
            List<String> list = query.list();
            return list.get(0);

        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }

    }

}
