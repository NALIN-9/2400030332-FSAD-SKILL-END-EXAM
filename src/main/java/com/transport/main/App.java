package com.transport.main;

import com.transport.entity.Transport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Transport.class)
                .buildSessionFactory();

        try {
            createTransports(factory);
            readAllTransports(factory);
            readTransportsByStatus(factory, "Active");
            updateTransportStatus(factory, "Truck-001", "Inactive");
            deleteTransport(factory, "Van-002");

            System.out.println("\n--- Final list of transports ---");
            readAllTransports(factory);

        } finally {
            factory.close();
        }
    }

    private static void createTransports(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            System.out.println("Creating new transport objects...");
            Transport tempTransport1 = new Transport("Truck-001", new Date(), "Active", "Heavy Truck", 10000);
            Transport tempTransport2 = new Transport("Van-002", new Date(), "Active", "Light Van", 2000);
            Transport tempTransport3 = new Transport("Bus-003", new Date(), "Maintenance", "Passenger Bus", 50);

            session.save(tempTransport1);
            session.save(tempTransport2);
            session.save(tempTransport3);

            session.getTransaction().commit();
            System.out.println("Created and saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    private static void readAllTransports(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            System.out.println("\nReading all transports using HQL...");

            Query<Transport> query = session.createQuery("from Transport", Transport.class);
            List<Transport> transports = query.getResultList();

            for (Transport transport : transports) {
                System.out.println(transport);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    private static void readTransportsByStatus(SessionFactory factory, String status) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            System.out.println("\nReading transports with status '" + status + "' using HQL...");

            Query<Transport> query = session.createQuery("from Transport t where t.status = :transportStatus",
                    Transport.class);
            query.setParameter("transportStatus", status);
            List<Transport> transports = query.getResultList();

            for (Transport transport : transports) {
                System.out.println(transport);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    private static void updateTransportStatus(SessionFactory factory, String name, String newStatus) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            System.out.println("\nUpdating status of '" + name + "' to '" + newStatus + "' using HQL...");

            Query query = session.createQuery("update Transport set status = :newStatus where name = :name");
            query.setParameter("newStatus", newStatus);
            query.setParameter("name", name);

            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    private static void deleteTransport(SessionFactory factory, String name) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            System.out.println("\nDeleting transport '" + name + "' using HQL...");

            Query query = session.createQuery("delete from Transport where name = :name");
            query.setParameter("name", name);

            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }
}
