package xrm.test.bv.client.ui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

public class DateWidget extends Composite {

	private static DateWidgetUiBinder uiBinder = GWT.create(DateWidgetUiBinder.class);

	interface DateWidgetUiBinder extends UiBinder<Widget, DateWidget> {
	}

	private PopupPanel popupPanel = new PopupPanel(true);
	private Date date = null;
	@UiField
	TextBox textbox;
	@UiField
	Anchor clear;
	@UiField
	Label label;

	public DateWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		DatePicker datePicker = new DatePicker();
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {

			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				textbox.setText(DateTimeFormat.getFormat("dd.MM.yyyy").format(date));
				DateWidget.this.date = date;
				popupPanel.hide();
			}
		});
		popupPanel.setWidget(datePicker);
	}

	@UiHandler("textbox")
	public void onClick(ClickEvent event) {
		int x = textbox.getAbsoluteLeft();
		int y = textbox.getAbsoluteTop();

		popupPanel.setPopupPosition(x, y + 20);
		popupPanel.show();
	}

	@UiHandler("clear")
	public void onClear(ClickEvent event) {
		textbox.setValue("");
		date = null;
	}

	public Date getDate() {
		return date;
	}

	public String getLabel() {
		return label.getText();
	}

	public void setLabel(String label) {
		this.label.setText(label);
	}

}
