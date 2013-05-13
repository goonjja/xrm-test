package xrm.test.bv.client;

import xrm.test.bv.client.ui.VersionsViewer;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.RootPanel;

public class Application implements EntryPoint {
	VersionsPresenter presenter;
	VersionsViewer view;

	@Override
	public void onModuleLoad() {
		view = GWT.create(VersionsViewer.class);
		presenter = new VersionsPresenter(view);
		Document.get().getElementById("loading").removeFromParent();
		RootPanel.get().add(view);

		presenter.load();
	}

}
