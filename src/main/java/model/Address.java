package model;

//import jakarta.persistence.*;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "address",
        uniqueConstraints = {@UniqueConstraint(name = "Address_city_uindex",
                columnNames = {"country", "city"})})
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", unique = true)
    private int addressId;
    @Column(name = "country", length = 50, nullable = false)
    private String country;
    @Column(name = "city", length = 50, nullable = false)
    private String city;
    @OneToMany(mappedBy = "address", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    Set<Passenger> passengers = new HashSet<>();
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

//    public Set<Address> getAddressSet() {
//        return addressSet;
//    }
//
//    public void setAddressSet(Address addressSet) {
//        this.addressSet.add(addressSet);
//    }

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public Address() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city);
    }

    public void setId(int addressId) {
        this.addressId = addressId;
    }

    public int getId() {
        return addressId;
    }
}
