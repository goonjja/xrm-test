package xrm.test.bv.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;

public interface FilterChangedEventHandler extends EventHandler {
	void onChanged(List<String> authors, Date from, Date to);
}
