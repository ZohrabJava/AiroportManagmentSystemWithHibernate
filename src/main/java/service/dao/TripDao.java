package service.dao;

import model.Trip;

import java.util.List;
import java.util.Set;

public interface TripDao {
    Trip getById(long id);

    Set<Trip> getAll();

    Set<Trip> get(int offset, int perPage, String sort);

    void save(Trip trip);

    void update(Trip trip,int id);

    void delete(long tripId);

    List<Trip> getTripsFrom(String city);

    List<Trip> getTripsTo(String city);

}
