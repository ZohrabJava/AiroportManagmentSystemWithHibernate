
import model.Address;
import model.Company;
import model.Passenger;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.DBInput;
import service.HibernetConect;
import service.fileRider.*;
import service.impl.CompanyImpl;
import service.impl.PassengerImpl;
import service.impl.TripImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
//import service.fileRider.PassengerReader;


public class Test {
    public static void main(String[] args) {
        //Creat Input
//        DBInput.addressInput(AddressReader.addressesRead());
//        DBInput.passengerInput(PassengerReader.passengerReader());
//        DBInput.companyInput(CompanyReader.companyReader());
//        DBInput.tripInpit(TripReader.tripReader());
//        DBInput.passInTripInput(PassInTripReader.PassInTripReader());
        //CompanyImpl testing
//        CompanyImpl company=new CompanyImpl();
        //Get by id
//        System.out.println(company.getById(10));
        //Get all
//        System.out.println(company.getAll());
        //Get
//        System.out.println(company.get(4,2,"company_id"));
        //save
//        Company company1=new Company( LocalDate.now(),"blo");
//        company.save(company1);
        //update
//        company.update(company1,"NNN");
        //delete
//        company.delete(1000);
        //Passenger testing
        PassengerImpl passenger = new PassengerImpl();
        TripImpl trip=new TripImpl();
        //Get by id
//        System.out.println(trip.getById(1100));
        //Get all
//        System.out.println(trip.getAll());
        //Get
//        System.out.println(trip.get(4,2,"date"));
        //save
//        SessionFactory sessionFactory = HibernetConect.getSessionFactory();
//        Session session;
//        session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        Company company = null;
//        company = session.get(Company.class, 10);
//        session.getTransaction().commit();
//        session.close();
//        Trip trip1 = new Trip(1234,company,"Kol","Armenia","Russia", LocalDateTime.now(),LocalDateTime.now());
//        trip.save(trip1);
        //update
//        trip.update(trip1,1234);
        //delete
//        trip.delete(1124);
        //Get trip from
//        System.out.println(trip.getTripsFrom("London"));
        //Get trip to
//        System.out.println(trip.getTripsTo("Moscow"));
    }
}
