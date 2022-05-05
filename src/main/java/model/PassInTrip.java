package model;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "pass_in_trip", uniqueConstraints = {@UniqueConstraint(name = "pass_in_trip_psg_id_uindex",
        columnNames = {"trip_id", "psg_id"})})
public class PassInTrip implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id", insertable = false, updatable = false)
    private Trip trip;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "psg_id", insertable = false, updatable = false)
    private Passenger passenger;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "place", length = 5, nullable = false)
    private String place;

    public PassInTrip(Trip trip, Passenger passenger, LocalDateTime date, String place) {
        this.trip = trip;
        this.passenger = passenger;
        this.date = date;
        this.place = place;
    }

    public PassInTrip(int tripId, int psgId, LocalDateTime date, String place) {

        this.date = date;
        this.place = place;
    }

    public PassInTrip() {

    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "PassInTrip{" +
                "tripId=" + String.valueOf( trip.getTripNumber()) +
                ", passengerId=" + String.valueOf( passenger.getPassengerId()) +
                ", date=" + date +
                ", place='" + place + '\'' +
                '}';
    }
}
