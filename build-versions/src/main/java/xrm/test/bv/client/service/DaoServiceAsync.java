package xrm.test.bv.client.service;

import java.util.List;

import xrm.test.versions.entity.ClassVersion;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DaoServiceAsync {

	void getVersions(AsyncCallback<List<ClassVersion>> callback);

	void getAuthors(AsyncCallback<List<String>> callback);

}
