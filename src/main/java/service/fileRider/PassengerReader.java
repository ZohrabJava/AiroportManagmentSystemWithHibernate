package service.fileRider;

import model.Address;
import model.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import service.HibernetConect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PassengerReader {
//    public static Address addressSearch(String[] arr) {
//       Set<Address> addresses=AddressReader.addressesRead();
//       return Addr;
//    }

    public static Set<Passenger> passengerReader() {
        SessionFactory sessionFactory = HibernetConect.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Set<Passenger> passengers = null;
        try {
            passengers = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/passengers.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                element = element.replace("'", "");
                arr = element.split(",");
                Query query =session.createQuery("select a from Address a where country=:COUNTRY and city=:CITY ").
                        setParameter("COUNTRY", arr[2]).setParameter("CITY", arr[3]);
                Address address= (Address)query.list().get(0);
                Passenger pas = new Passenger(arr[0], arr[1]);
                pas.setAddress(address);
                passengers.add(pas);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.close();
        return passengers;
    }
}
