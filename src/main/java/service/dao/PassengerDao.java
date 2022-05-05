package service.dao;

import model.Passenger;
import model.Trip;

import java.util.List;
import java.util.Set;

public interface PassengerDao {
    Passenger getById(long id);

    Set<Passenger> getAll();

    Set<Passenger> get(int offset, int perPage, String sort);

    void save(Passenger passenger);

    void update(Passenger passenger, int id);



    void delete(long passengerId);

    List<Passenger> getPassengersOfTrip(long tripNumber);

    void registerTrip(Trip trip, Passenger passenger);

    void cancelTrip(long passengerId, long tripNumber);

}
