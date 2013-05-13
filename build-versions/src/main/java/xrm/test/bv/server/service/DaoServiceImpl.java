package xrm.test.bv.server.service;

import java.util.List;

import xrm.test.bv.client.service.DaoService;
import xrm.test.bv.server.ApplicationContextProvider;
import xrm.test.bv.server.ClassVersionDAO;
import xrm.test.versions.entity.ClassVersion;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DaoServiceImpl extends RemoteServiceServlet implements DaoService {
	private static final long serialVersionUID = 7694101036379875425L;
	private ClassVersionDAO dao = (ClassVersionDAO) ApplicationContextProvider.getInstance().createAutowiredBean(
			ClassVersionDAO.class);

	@Override
	public List<ClassVersion> getVersions() {
		return dao.findAll();
	}

	@Override
	public List<String> getAuthors() {
		return dao.getAuthors();
	}

}
