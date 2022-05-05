package service;


import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.Set;

public class DBInput {
    public static void addressInput(Set<Address> addresses) {
        SessionFactory sessionFactory = HibernetConect.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Address address : addresses) {
            session.save(address);
        }
        System.out.println("Data is Added");
        transaction.commit();
        session.close();
    }

    public static void passengerInput(Set<Passenger> passengers) {
        SessionFactory sessionFactory = HibernetConect.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Passenger passenger : passengers) {
            session.save(passenger);
        }
        transaction.commit();
        System.out.println("Data is Added");
        session.close();
    }
    public static void companyInput(Set<Company> companies) {
        SessionFactory sessionFactory = HibernetConect.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Company company : companies) {
            session.save(company);
        }
        transaction.commit();
        System.out.println("Data is Added");
        session.close();
    }
    public static void tripInpit(Set<Trip> trips) {
        SessionFactory sessionFactory = HibernetConect.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Trip trip : trips) {
            session.save(trip);
        }
        transaction.commit();
        System.out.println("Data is Added");
        session.close();
    }
    public static void passInTripInput(Set<PassInTrip> passInTrips) {
        SessionFactory sessionFactory = HibernetConect.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (PassInTrip passInTrip : passInTrips) {
            session.save(passInTrip);
        }
        transaction.commit();
        System.out.println("Data is Added");
        session.close();
    }
}
