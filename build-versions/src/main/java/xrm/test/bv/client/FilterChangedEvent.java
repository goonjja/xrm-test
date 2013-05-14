package xrm.test.bv.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent;

public class FilterChangedEvent extends GwtEvent<FilterChangedEventHandler> {

	public static Type<FilterChangedEventHandler> TYPE = new Type<FilterChangedEventHandler>();

	private List<String> authors;
	private Date from;
	private Date to;

	public FilterChangedEvent(List<String> authors, Date from, Date to) {
		super();
		this.authors = authors;
		this.from = from;
		this.to = to;
	}

	@Override
	public GwtEvent.Type<FilterChangedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FilterChangedEventHandler handler) {
		handler.onChanged(authors, from, to);
	}

}
