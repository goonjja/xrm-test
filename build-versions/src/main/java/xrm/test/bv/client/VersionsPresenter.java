package xrm.test.bv.client;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;

import xrm.test.bv.client.service.DaoService;
import xrm.test.bv.client.service.DaoServiceAsync;
import xrm.test.versions.entity.ClassVersion;

public class VersionsPresenter {

	public interface View extends IsWidget {
		void setVersions(List<ClassVersion> versions);

		void setAuthors(List<String> result);
	}

	private DaoServiceAsync dao = GWT.create(DaoService.class);
	private View view;

	public VersionsPresenter(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void loadVersions() {
		dao.getVersions(new AsyncCallback<List<ClassVersion>>() {

			@Override
			public void onSuccess(List<ClassVersion> result) {
				view.setVersions(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("failed", caught);
			}
		});
	}

	public void loadAuthors() {
		dao.getAuthors(new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				view.setAuthors(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("failed", caught);
			}
		});
	}

	public void load() {
		loadAuthors();
		loadVersions();
	}

}
