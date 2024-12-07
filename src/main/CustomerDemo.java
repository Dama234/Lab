package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure(\"hibernate.cfg.xml\").addAnnotatedClass(Customer.class).buildSessionFactory();
    }

    public static void main(String[] args) {
        insertCustomers();
        applyCriteriaQueries();
    }

    private static void insertCustomers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Customer c1 = new Customer();
        c1.setName(\"John Doe\");
        c1.setEmail(\"john@example.com\");
        c1.setAge(25);
        c1.setLocation(\"New York\");

        Customer c2 = new Customer();
        c2.setName(\"Jane Smith\");
        c2.setEmail(\"jane@example.com\");
        c2.setAge(30);
        c2.setLocation(\"Los Angeles\");

        Customer c3 = new Customer();
        c3.setName(\"Mark Wilson\");
        c3.setEmail(\"mark@example.com\");
        c3.setAge(35);
        c3.setLocation(\"Chicago\");

        session.save(c1);
        session.save(c2);
        session.save(c3);

        transaction.commit();
        session.close();
    }

    private static void applyCriteriaQueries() {
        Session session = sessionFactory.openSession();

        
        Criteria criteria1 = session.createCriteria(Customer.class);
        criteria1.add(Restrictions.eq(\"location\", \"New York\"));
        List<Customer> result1 = criteria1.list();
        System.out.println(\"Customers in New York: \" + result1);

    
        Criteria criteria2 = session.createCriteria(Customer.class);
        criteria2.add(Restrictions.gt(\"age\", 30));
        List<Customer> result2 = criteria2.list();
        System.out.println(\"Customers older than 30: \" + result2);

        
        Criteria criteria3 = session.createCriteria(Customer.class);
        criteria3.add(Restrictions.between(\"age\", 25, 35));
        List<Customer> result3 = criteria3.list();
        System.out.println(\"Customers between ages 25 and 35: \" + result3);

        
        Criteria criteria4 = session.createCriteria(Customer.class);
        criteria4.add(Restrictions.like(\"name\", \"%Jane%\"));\n       
          
        List<Customer> result4 = criteria4.list();\n           System.out.println(\"Customers with name containing 'Jane': \" + result4);\n\n           session.close();\n       }\n   }\n   ```
