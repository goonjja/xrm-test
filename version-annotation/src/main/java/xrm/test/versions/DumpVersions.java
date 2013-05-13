package xrm.test.versions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import xrm.test.versions.annotation.Version;
import xrm.test.versions.entity.ClassVersion;


/**
 * Scans classpath for classes annotated with a Version annotation, updates
 * database if changes have been found.
 * 
 * @author vedernikov
 */
public class DumpVersions {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private static SessionFactory sessionFactory = null;
	private static ServiceRegistry serviceRegistry = null;

	public static void main(String[] args) throws ClassNotFoundException {
		String basePackage = "xrm.test";
		if(args.length > 0)
			basePackage = args[0];
		// Configure the session factory
		configureSessionFactory();

		// Look for annotated classes
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Version.class));
		for (BeanDefinition bean : scanner.findCandidateComponents(basePackage)) {
			String className = bean.getBeanClassName();
			Version annotation = Class.forName(className).getAnnotation(Version.class);
			saveVersion(className, annotation);
		}

	}
	

	private static SessionFactory configureSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.configure();

		Properties properties = configuration.getProperties();

		serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		return sessionFactory;
	}

	private static void saveVersion(String className, Version annotation) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			ClassVersion codeVersion = createClassVersion(className, annotation);
			// check previous version
			Criteria c = session.createCriteria(ClassVersion.class).add(Restrictions.ilike("className", className))
					.add(Restrictions.ilike("author", codeVersion.getAuthor()))
					.add(Restrictions.eq("date", codeVersion.getDate()))
					.add(Restrictions.ilike("comment", codeVersion.getComment()));
			ClassVersion existingVersion = (ClassVersion) c.uniqueResult();
			// save new version if it not exists
			if (existingVersion == null) {
				session.save(codeVersion);
			}
			session.flush();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	private static ClassVersion createClassVersion(String className, Version annotation) throws ParseException {
		ClassVersion result = new ClassVersion();
		result.setClassName(className);
		result.setAuthor(annotation.author());
		result.setDate(sdf.parse(annotation.date()));
		result.setComment(annotation.comment());
		return result;
	}
}
