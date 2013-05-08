package com.rise.common.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class PersonNameTest {

	public static void main(String[] args) throws IOException {
		InputStream resourceInputStream = Environment.class.getClassLoader()
				.getResourceAsStream("hibernate.cfg.xml");
		System.out.println(resourceInputStream.toString());
		Configuration configuration = new Configuration()
				.addResource("hibernate.cfg.xml");
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();

		Transaction transaction = session.getTransaction();
		transaction.begin();
		System.out.println("Inserting Record...");
		PersonName personName = new PersonName();
		personName.setId(new Integer(1001));
		personName.setTitle("Mr.");
		personName.setFirstName("Amar");
		personName.setMiddleName("ShivaPrasad");
		personName.setLastName("Thondapu");
		personName.setSuffix("Jr.");
		personName.setDateCreated(new Date());
		personName.setCreatedBy(new Integer(1));
		personName.setModifiedBy(new Integer(1));
		personName.setDateModified(new Date());
//		personName.setRecordStatus("A");
		session.save(personName);
		transaction.commit();
		System.out.println("Done...");
	}
}
