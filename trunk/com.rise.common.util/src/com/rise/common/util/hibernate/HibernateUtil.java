package com.rise.common.util.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.rise.common.util.constants.HibernateConstants;

public class HibernateUtil {

	private static HibernateUtil instance = new HibernateUtil();
	private SessionFactory sessionFactory;
	private boolean initialized = false;

	private HibernateUtil() {
		// Prevent Instantiation
	}

	public static HibernateUtil getInstance() {
		return instance;
	}

	public synchronized void initializeSessionFactory() {
		if (!initialized) {
			Configuration configuration = initializeConfiguration();
			ServiceRegistry serviceRegistry = initializeServiceRegistry(configuration);
			if (serviceRegistry != null) {
				this.sessionFactory = configuration
						.buildSessionFactory(serviceRegistry);
				this.initialized = true;
			}
		}
	}

	private Configuration initializeConfiguration() {
		Configuration configuration = new Configuration()
				.addResource(HibernateConstants.HIBERNATE_CFG_XML);
		return configuration.configure();
	}

	private ServiceRegistry initializeServiceRegistry(
			Configuration argConfiguration) {
		if (argConfiguration != null) {
			ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder()
					.applySettings(argConfiguration.getProperties());
			if (serviceRegistryBuilder != null) {
				return serviceRegistryBuilder.buildServiceRegistry();
			}
		}
		return null;
	}

	public Session getSession() {
		return this.sessionFactory.openSession();
	}

	public void beginTransaction(Session argSession) {
		if (argSession != null) {
			argSession.beginTransaction();
		}
	}

	public void commit(Session argSession) {
		if (argSession != null) {
			try {
				argSession.getTransaction().commit();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}

	public void rollback(Session argSession) {
		if (argSession != null) {
			try {
				argSession.getTransaction().rollback();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}

	public void closeSession(Session argSession) {
		if (argSession != null) {
			try {
				argSession.close();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}

	public void closeSessionFactory() {
		if (this.sessionFactory != null) {
			try {
				this.sessionFactory.close();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}
}
