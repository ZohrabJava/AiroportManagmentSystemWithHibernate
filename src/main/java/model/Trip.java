package model;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trip", uniqueConstraints = { @UniqueConstraint(name = "trip_trip_number_uindex",
        columnNames = {"trip_number"})})
public class Trip implements Serializable {
    @Id
    @Column(name = "trip_number",nullable = true)
    private int tripNumber;
    @ManyToOne( cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn( name="company_id",nullable = true, foreignKey = @ForeignKey(name = "company_psg_fk"))
    private Company company;
    @Column(name = "plane",length = 30,nullable = false)
    private String plane;
    @Column(name = "town_from",length = 30,nullable = false)
    private String townFrom;
    @Column(name = "town_to",length = 30,nullable = false)
    private  String townTo;
    @Column(name = "time_out",nullable = false)
    private LocalDateTime timeOut;
    @Column(name="time_in",nullable = false)
    private LocalDateTime timeIn;

    @OneToMany(mappedBy = "trip", cascade = { CascadeType.ALL})
    Set<PassInTrip> passInTrips = new HashSet<>();

    public Trip(int tripNumber, Company company, String plane, String townFrom, String townTo, LocalDateTime timeOut, LocalDateTime timeIn) {
        this.tripNumber = tripNumber;
        this.company = company;
        this.plane = plane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    public Trip() {

    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company= company;
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }


    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripNumber=" + tripNumber +
                ", company=" + company +
                ", plane='" + plane + '\'' +
                ", townFrom='" + townFrom + '\'' +
                ", townTo='" + townTo + '\'' +
                ", timeOut=" + timeOut +
                ", timeIn=" + timeIn +
                '}'+'\n';
    }
}
