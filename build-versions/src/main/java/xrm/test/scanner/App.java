package xrm.test.scanner;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App {

	private static SessionFactory sessionFactory = null;
	private static ServiceRegistry serviceRegistry = null;

	private static SessionFactory configureSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.configure();

		Properties properties = configuration.getProperties();

		serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		return sessionFactory;
	}

	public static void main(String[] args) {
		// Configure the session factory
		configureSessionFactory();

		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// Creating Contact entity that will be save to the sqlite database
			CodeVersion codeVersion = new CodeVersion();
			codeVersion.setName("name");
			codeVersion.setComment("No comments");
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			codeVersion.setDate(sdf.parse("8.05.2013"));

			// Saving to the database
			session.save(codeVersion);

			// Committing the change in the database.
			session.flush();
			tx.commit();

			// Fetching saved data
			List<CodeVersion> list = session.createQuery("from CodeVersion").list();

			for (CodeVersion version : list) {
				System.out.println("Id: " + version.getId() + " | Name:" + version.getName() + " | Comment:"
						+ version.getComment() + " | Date:" + version.getDate());
			}

		} catch (Exception ex) {
			ex.printStackTrace();

			// Rolling back the changes to make the data consistent in case of
			// any failure
			// in between multiple database write operations.
			tx.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
