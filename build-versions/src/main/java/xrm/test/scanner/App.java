package xrm.test.scanner;

import java.io.IOException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import xrm.test.bv.server.ClassVersionDAO;
import xrm.test.versions.entity.ClassVersion;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ClassVersionDAO dao = ctx.getBean(ClassVersionDAO.class);
		List<String> versions = dao.getAuthors();
		for (String cv : versions) {
			System.out.println(cv);
		}
	}
}
