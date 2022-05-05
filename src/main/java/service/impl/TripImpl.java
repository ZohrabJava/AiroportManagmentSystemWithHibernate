package service.impl;

import model.Passenger;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.HibernetConect;
import service.dao.TripDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class TripImpl implements TripDao {

    private final SessionFactory sessionFactory = HibernetConect.getSessionFactory();
    private Session session;

    @Override
    public Trip getById(long id) {
        Trip trip = null;
        try {
            session = sessionFactory.openSession();
            Query query= session.createQuery("select t from Trip t where t.tripNumber=:ID").
                    setParameter("ID",  Integer.parseInt(String.valueOf(id)));
            trip =(Trip) query.getResultList().get(0);
        }finally {
            session.close();
        }
        return trip;
    }

    @Override
    public Set<Trip> getAll() {
        session = sessionFactory.openSession();
        Set<Trip> trips = new HashSet<>();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select t from Trip t ");
        for (int i = 0; i < query.list().size(); i++) {
            trips.add((Trip) query.list().get(i));
        }
        return trips;
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {
        session = sessionFactory.openSession();
        Set<Trip> trips = new HashSet<>();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select t from Trip t order by :SORT").
                setParameter("SORT", sort).
                setFirstResult(offset).setMaxResults(perPage);
        for (int i = 0; i < query.getResultList().size(); i++) {
            trips.add((Trip) query.getResultList().get(i));
        }
        return trips;
    }

    @Override
    public void save(Trip trip) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(trip);
        transaction.commit();
        System.out.println("Data is Added");
        session.close();
    }

    @Override
    public void update(Trip trip, int id) {
        session = sessionFactory.openSession();
        trip.setTripNumber(id);
        Transaction tx = session.beginTransaction();
        session.update(trip);
        tx.commit();
        System.out.println("Data is Update");
        session.close();
    }

    @Override
    public void delete(long tripId) {
        Trip trip = getById(tripId);
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(trip);
        transaction.commit();
        System.out.println("Data is Delete");
        session.close();
    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        session = sessionFactory.openSession();
        List<Trip> trips =null;
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select t from Trip t where townFrom=:TOWNFROM").
                setParameter("TOWNFROM",city);
        trips=query.getResultList();
        return trips;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        session = sessionFactory.openSession();
        List<Trip> trips = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select t from Trip t where townTo=:TOWNTO").
                setParameter("TOWNTO",city);
        trips=query.getResultList();
        return trips;
    }
}
