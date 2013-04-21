package com.rise.common.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ContactInformationTest {

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
		Set<ContactInformation> ciSet = new HashSet<ContactInformation>();
		for (int i = 0; i < 2; i++) {
			ContactInformation ci = new ContactInformation();
			ci.setId(i + 1);
			ci.setStreetAddress("Kukatpally" + i);
			ci.setCity("Hyderabad" + i);
			ci.setState("AP" + i);
			ci.setPostalCode("500082");
			ci.setCountry("INDIA");
			ci.setEmail("athondapu@ciphercloud.com");
			ci.setAlternativeEmail("athondapu@ciphercloud.com");
			ci.setPhone("8886077897");
			ci.setAlternativePhone("8886077897");
			ci.setWebsite("www.google.com");
			ci.setCreatedBy(1);
			ci.setDateCreated(new Date());
			ci.setModifiedBy(1);
			ci.setDateModified(new Date());
			ci.setRecordStatus("A");
			ciSet.add(ci);
		}
		Iterator<ContactInformation> iterator = ciSet.iterator();
		while (iterator.hasNext()) {
			session.save(iterator.next());
		}
		transaction.commit();
		System.out.println("Done...");
	}
}
