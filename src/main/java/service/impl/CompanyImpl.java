package service.impl;

import model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.HibernetConect;
import service.dao.CompanyDao;

import java.util.*;

public class CompanyImpl implements CompanyDao {
    private final SessionFactory sessionFactory = HibernetConect.getSessionFactory();
    private Session session;

    @Override
    public Company getById(long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Company company = null;
        company = session.get(Company.class, Integer.parseInt(String.valueOf(id)));
        session.getTransaction().commit();
        //Variant 2
//        Query query = session.createQuery("select c from Company c where companyId=:COMPANYID ").
//                setParameter("COMPANYID", Integer.parseInt(String.valueOf(id)));
//        Company company = (Company) query.list().get(0);
        session.close();
        return company;
    }

    @Override
    public Set<Company> getAll() {
        session = sessionFactory.openSession();
        Set<Company> companies = new HashSet<>();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select c from Company c  ");

        for (int i = 0; i < query.list().size(); i++) {
            companies.add((Company) query.list().get(i));
        }
        return companies;
    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {
        session = sessionFactory.openSession();
        Set<Company> companies = new HashSet<>();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select c from Company c order by :SORT").
                setParameter("SORT", sort).
                setFirstResult(offset).setMaxResults(perPage);
        for (int i = 0; i < query.getResultList().size(); i++) {
            companies.add((Company) query.getResultList().get(i));
        }
        return companies;
    }

    @Override
    public void save(Company company) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
        System.out.println("Data is Added");
        session.close();
    }

    public void insertId(Company company, String companyName) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT c from Company c where companyName=:COMPANYNAME").
                setParameter("COMPANYNAME", companyName);
        Company res = (Company) query.getResultList().get(0);
        company.setCompanyId(res.getCompanyId());
        session.close();
    }

    @Override
    public void update(Company company, String companyName) {
        session = sessionFactory.openSession();
        insertId(company, companyName);
        Transaction tx = session.beginTransaction();
        session.update(company);
        tx.commit();
        System.out.println("Data is Update");
        session.close();

    }

    @Override
    public void delete(long companyId) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Company company = getById(companyId);
        session.remove(company);
        transaction.commit();
        System.out.println("Data is Delete");
        session.close();
    }
}
