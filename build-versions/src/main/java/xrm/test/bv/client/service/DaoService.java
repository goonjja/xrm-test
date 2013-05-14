package xrm.test.bv.client.service;

import java.util.Date;
import java.util.List;

import xrm.test.versions.entity.ClassVersion;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dao")
public interface DaoService extends RemoteService{
	public List<ClassVersion> getVersions(List<String> authors, Date from, Date to);
	public List<String> getAuthors();
}
