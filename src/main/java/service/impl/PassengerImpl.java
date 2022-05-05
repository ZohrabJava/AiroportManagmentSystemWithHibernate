package service.impl;

import model.PassInTrip;
import model.Passenger;
import model.Trip;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.HibernetConect;
import service.dao.PassengerDao;
import java.time.LocalDateTime;
import java.util.*;

public class PassengerImpl implements PassengerDao {

    private final SessionFactory sessionFactory = HibernetConect.getSessionFactory();
    private Session session;
    public String autoPlace(int tripNumber) {
        String place = "1a";
        session = sessionFactory.openSession();
        List<String> places = null;
        Query query= session.createQuery("select pit.place from PassInTrip pit where pit.trip.id=:TRIPNUM ").
                setParameter("TRIPNUM",tripNumber);
        places=query.getResultList();
        while (places.contains(place)) {
            if (place.substring(0, 1).charAt(0) < '6') {
                place = Character.toString(place.charAt(0) + 1) + place.charAt(1);
            } else {
                place = "1" + Character.toString(place.charAt(1) + 1);
            }
        }
        return place;
    }

    @Override
    public Passenger getById(long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Passenger passenger = null;
        passenger = session.get(Passenger.class, Integer.parseInt(String.valueOf(id)));
        session.getTransaction().commit();
        session.close();
        return passenger;
    }

    @Override
    public Set<Passenger> getAll() {
        session = sessionFactory.openSession();
        Set<Passenger> passengers = new HashSet<>();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select p from Passenger p  ");
        for (int i = 0; i < query.list().size(); i++) {
            passengers.add((Passenger) query.list().get(i));
        }
        return passengers;
    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {
        session = sessionFactory.openSession();
        Set<Passenger> passengers = new HashSet<>();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select p from Passenger p order by :SORT").
                setParameter("SORT", sort).
                setFirstResult(offset).setMaxResults(perPage);
        for (int i = 0; i < query.getResultList().size(); i++) {
            passengers.add((Passenger) query.getResultList().get(i));
        }
        return passengers;
    }

    @Override
    public void save(Passenger passenger) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(passenger);
        transaction.commit();
        System.out.println("Data is Added");
        session.close();
    }


    @Override
    public void update(Passenger passenger, int id) {
        session = sessionFactory.openSession();
        passenger.setPassengerId(id);
        Transaction tx = session.beginTransaction();
        session.update(passenger);
        tx.commit();
        System.out.println("Data is Update");
        session.close();
    }

    @Override
    public void delete(long passengerId) {
        Passenger passenger = getById(passengerId);
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(passenger);
        transaction.commit();
        System.out.println("Data is Delete");
        session.close();
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        session = sessionFactory.openSession();
        List<Passenger> passengers = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select p from Passenger p join PassInTrip pt on p.id=pt.passenger.id join Trip t" +
                        " on pt.trip.id=t.id where pt.trip.id=:TRIPNUM").
                setParameter("TRIPNUM",Integer.parseInt(String.valueOf( tripNumber)));
        passengers=query.getResultList();
        session.close();
        return passengers;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {
        session = sessionFactory.openSession();
        PassInTrip passInTrip=new PassInTrip(trip,passenger, LocalDateTime.now(),autoPlace(trip.getTripNumber()));
        Transaction transaction = session.beginTransaction();
        session.save(passInTrip);
        transaction.commit();
        System.out.println("Data is Added");
        session.close();
    }

    public PassInTrip getPassInTrip(long passengerId, long tripNumber) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PassInTrip passInTrip = null;
        Query query= session.createQuery("select pit from PassInTrip pit where passenger.id=:PID and trip.id=:TID").
                setParameter("PID",Integer.parseInt(String.valueOf( passengerId)))
                        .setParameter("TID",Integer.parseInt(String.valueOf( tripNumber)));
        passInTrip =(PassInTrip) query.getResultList().get(0);
        session.getTransaction().commit();
        session.close();
        return passInTrip;
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {
        PassInTrip passInTrip = getPassInTrip(passengerId,tripNumber);
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(passInTrip);
        transaction.commit();
        System.out.println("Data is Delete");
        session.close();
    }
}
