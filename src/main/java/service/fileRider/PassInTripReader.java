package service.fileRider;

import model.Address;
import model.PassInTrip;
import model.Passenger;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import service.HibernetConect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PassInTripReader {
    public static String dateCompil(String date){
        return date.replace(" ","T");
    }
    public static Set<PassInTrip> PassInTripReader() {
        SessionFactory sessionFactory = HibernetConect.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Set<PassInTrip> pit = null;
        try {
            pit = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/pass_in_trip.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                element=element.replace("'","");
                arr = element.split(",");
                Query query =session.createQuery("select t from Trip t where tripNumber=:TRIPNUMBER  ").
                        setParameter("TRIPNUMBER", Integer.parseInt( arr[0]));
                Trip trip= (Trip) query.list().get(0);
                Query query1 =session.createQuery("select p from Passenger p where passengerId=:PASSENGERID  ").
                        setParameter("PASSENGERID", Integer.parseInt( arr[1]));
                Passenger passenger= (Passenger) query1.list().get(0);
                PassInTrip pi = new PassInTrip(trip,passenger,
                        LocalDateTime.parse(dateCompil( arr[2])),arr[3]);
                pit.add(pi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.close();
        return pit;
    }
}
