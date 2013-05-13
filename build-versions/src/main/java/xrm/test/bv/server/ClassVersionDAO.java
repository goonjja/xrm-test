package xrm.test.bv.server;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xrm.test.versions.entity.ClassVersion;

@Repository
public class ClassVersionDAO {
	private Logger log = Logger.getLogger(ClassVersionDAO.class.getName());

	@Autowired
	SessionFactory sessionFactory;

	public ClassVersionDAO() {
	}

	public List<ClassVersion> findAll() {
		Session session = sessionFactory.openSession();
		return session.createCriteria(ClassVersion.class).list();
	}

	public List<String> getAuthors() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select cv.author from ClassVersion cv group by cv.author");
		return query.list();
	}
}
