package xrm.test.bv.client.service;

import java.util.Date;
import java.util.List;

import xrm.test.versions.entity.ClassVersion;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DaoServiceAsync {


	void getAuthors(AsyncCallback<List<String>> callback);

	void getVersions(List<String> authors, Date from, Date to, AsyncCallback<List<ClassVersion>> callback);

}
