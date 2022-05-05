package model;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "passenger")
public class Passenger implements Serializable{
    @Id
    @Column(name = "passenger_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passengerId;
    @Column(name="passenger_name",length = 30,nullable = false)
    private String passengerName;
    @Column(name="passenger_phone",length = 30,nullable = false)
    private String passengerPhone;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="address_id",nullable = true, foreignKey = @ForeignKey(name = "address_psg_fk"))
//    private Set<Address> address=new HashSet<>();
    private Address address;
    @OneToMany(mappedBy = "passenger", cascade = {CascadeType.PERSIST})
    Set<PassInTrip> passInTrips = new HashSet<>();

    public Passenger(String passengerName, String passengerPhone) {
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
    }

    public Passenger(String passengerName, String passengerPhone, Address address) {
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
        this.address = address;
    }

    public Passenger() {

    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

//    public Set<Address> getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address.add( address);
//    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }


    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", passengerName='" + passengerName + '\'' +
                ", passengerPhone='" + passengerPhone + '\'' +
                ", addressId=" +address.getAddressId()+
                '}'+'\n';
    }

    public void setId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getId() {
        return passengerId;
    }
}
