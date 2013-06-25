package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.rise.common.model.Person;
import com.rise.common.model.PersonName;

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
		
//		AbstractEntityPersister aep=((AbstractEntityPersister)session.getSessionFactory().getClassMetadata(Person.class));
//		String[] properties=aep.getPropertyNames();
//		                
//		for(int nameIndex=0;nameIndex!=properties.length;nameIndex++){
//		   System.out.println("Property name: "+properties[nameIndex]);
//		   String[] columns=aep.getPropertyColumnNames(nameIndex);
//		   for(int columnIndex=0;columnIndex!=columns.length;columnIndex++){
//		      System.out.println("Column name: "+columns[columnIndex]);
//		   }
//		}
//		String[] names = sessionFactory.getClassMetadata(PersonName.class.getName()).getPropertyNames();
//		for (String string : names) {
//			System.out.println(string);
//		}
		
		Map<String, ClassMetadata> map = sessionFactory.getAllClassMetadata();
		Iterator<Map.Entry<String, ClassMetadata>> iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String,ClassMetadata> entry = iter.next();
			System.out.println("Key: "+entry.getKey());
			ClassMetadata value = entry.getValue();
			System.out.println(value.getIdentifierPropertyName());
			System.out.println(value.getIdentifierType());
			for (String token : value.getPropertyNames()) {
				System.out.println("Property Name: "+token);
			}
		}

//		Transaction transaction = session.getTransaction();
//		transaction.begin();
//		System.out.println("Inserting Record...");
//		PersonName personName = new PersonName();
////		personName.setId(new Integer(1001));
//		personName.setTitle("Mr.");
//		personName.setFirstName("Amar");
//		personName.setMiddleName("ShivaPrasad");
//		personName.setLastName("Thondapu");
//		personName.setSuffix("Jr.");
////		personName.setDateCreated(new Date());
////		personName.setCreatedBy(new Integer(1));
////		personName.setModifiedBy(new Integer(1));
////		personName.setDateModified(new Date());
////		personName.setRecordStatus("A");
//		session.save(personName);
//		transaction.commit();
//		System.out.println("Done...");
	}
}
