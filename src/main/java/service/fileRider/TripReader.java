package service.fileRider;

import model.Address;
import model.Company;
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

public class TripReader {
    public static String dateCompil(String date){
        return date.replace(" ","T");
    }
    public static Set<Trip> tripReader() {
        SessionFactory sessionFactory = HibernetConect.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Set<Trip> trip = null;
        try {
            trip = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/trips.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                element=element.replace("'","");
                arr = element.split(",");
                Query query =session.createQuery("select c from Company c where companyId=:COMPANYID ").
                        setParameter("COMPANYID", Integer.parseInt( arr[1]));
                Company company= (Company) query.list().get(0);
                Trip tr = new Trip( Integer.parseInt( arr[0]),company,
                        arr[2],arr[3],arr[4], LocalDateTime.parse( dateCompil( arr[5])),
                        LocalDateTime.parse( dateCompil(arr[6])));
                trip.add(tr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.close();
        return trip;
    }
}
