package com.rise.model;

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

import com.rise.common.model.ContactInformation;

public class TrainingProviderTest {

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

		Set<ContactInformation> ciSet = new HashSet<ContactInformation>();
		Set<TrainingProvider> tpSet = new HashSet<TrainingProvider>();
		Set<TrainingProviderEmpanelment> tpeSet = new HashSet<TrainingProviderEmpanelment>();
		for (int i = 0; i < 2; i++) {
			ContactInformation ci = new ContactInformation();
			ci.setId(i + 1);
//			ci.setStreetAddress("Kukatpally" + i);
//			ci.setCity("Hyderabad" + i);
//			ci.setState("AP" + i);
//			ci.setPostalCode("500082");
//			ci.setCountry("INDIA");
//			ci.setEmail("athondapu@ciphercloud.com");
//			ci.setAlternativeEmail("athondapu@ciphercloud.com");
//			ci.setPhone("8886077897");
//			ci.setAlternativePhone("8886077897");
			ci.setWebsite("www.google.com");
			ci.setCreatedBy(1);
			ci.setDateCreated(new Date());
			ci.setModifiedBy(1);
			ci.setDateModified(new Date());
			ci.setRecordStatus("A");
			ciSet.add(ci);
			session.save(ci);
		}
		for (int i = 0; i < 2; i++) {
			TrainingProviderEmpanelment tpe = new TrainingProviderEmpanelment();
			tpe.setId(i + 1);
			tpe.setDateRequested(new Date());
			tpe.setDateApproved(new Date());
			tpe.setFromDate(new Date());
			tpe.setToDate(new Date());
			tpe.setCreatedBy(1);
			tpe.setDateCreated(new Date());
			tpe.setModifiedBy(1);
			tpe.setDateModified(new Date());
			tpe.setRecordStatus("A");
			tpeSet.add(tpe);
		}
		for (int i = 0; i < 2; i++) {
			TrainingProvider taProvider = new TrainingProvider();
			taProvider.setId(i + 1);
			taProvider.setContactPersonName("Amar" + i);
			taProvider.setName("Amar" + i);
			taProvider.setCreatedBy(1);
			taProvider.setDateCreated(new Date());
			taProvider.setModifiedBy(1);
			taProvider.setDateModified(new Date());
			taProvider.setRecordStatus("A");
			taProvider.setContactInformation(ciSet.iterator().next());
			taProvider.setTrainingProviderEmpanelments(tpeSet);
			tpSet.add(taProvider);
		}
		Iterator<TrainingProvider> iterator = tpSet.iterator();
		while (iterator.hasNext()) {
			session.save(iterator.next());
		}
			transaction.commit();
			System.out.println("Done...");
			session.close();
			sessionFactory.close();
	}
}
