package xrm.test.bv.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
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

	public List<ClassVersion> find(List<String> authors, Date from, Date to) {
		Session session = sessionFactory.openSession();
		Criteria c = session.createCriteria(ClassVersion.class);
		if (authors != null && !authors.isEmpty()) {
			Criterion[] authorRestrictions = new Criterion[authors.size()];
			for (int i = 0; i < authors.size(); i++) {
				String author = authors.get(i);
				authorRestrictions[i] = Restrictions.like("author", author);
			}
			c.add(Restrictions.or(authorRestrictions));
		}
		if (from != null)
			c.add(Restrictions.ge("date", from));
		if (to != null)
			c.add(Restrictions.le("date", to));
		return c.list();
	}
}
