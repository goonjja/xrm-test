package xrm.test.bv.client.ui;

import java.util.Comparator;
import java.util.List;

import xrm.test.bv.client.VersionsPresenter;
import xrm.test.versions.entity.ClassVersion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class VersionsViewer extends Composite implements VersionsPresenter.View {

	private static VersionsViewerUiBinder uiBinder = GWT.create(VersionsViewerUiBinder.class);

	interface VersionsViewerUiBinder extends UiBinder<Widget, VersionsViewer> {
	}

	public VersionsViewer() {
		initWidget(uiBinder.createAndBindUi(this));
		initTable();
	}

	@UiField
	SimplePanel versions;
	@UiField
	ListBox authorFilter;

	ListDataProvider<ClassVersion> dataProvider = new ListDataProvider<ClassVersion>();

	private void initTable() {
		// Create a CellTable.
		CellTable<ClassVersion> table = new CellTable<ClassVersion>();

		TextColumn<ClassVersion> authorColumn = new TextColumn<ClassVersion>() {
			@Override
			public String getValue(ClassVersion version) {
				return version.getAuthor();
			}
		};
		authorColumn.setSortable(true);
		TextColumn<ClassVersion> classNameColumn = new TextColumn<ClassVersion>() {
			@Override
			public String getValue(ClassVersion version) {
				return version.getClassName();
			}
		};
		classNameColumn.setSortable(true);
		TextColumn<ClassVersion> dateColumn = new TextColumn<ClassVersion>() {
			@Override
			public String getValue(ClassVersion version) {
				return version.getDate().toString().substring(0, 10);
			}
		};
		dateColumn.setSortable(true);
		TextColumn<ClassVersion> commentColumn = new TextColumn<ClassVersion>() {
			@Override
			public String getValue(ClassVersion version) {
				return version.getComment();
			}
		};

		// Add the columns.
		table.addColumn(authorColumn, "Автор");
		table.addColumn(classNameColumn, "Класс");
		table.addColumn(dateColumn, "Дата");
		table.addColumn(commentColumn, "Комментарий");

		ListHandler<ClassVersion> authorSortHandler = new ListHandler<ClassVersion>(dataProvider.getList());
		authorSortHandler.setComparator(authorColumn, new Comparator<ClassVersion>() {
			public int compare(ClassVersion o1, ClassVersion o2) {
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null) {
					return (o2 != null) ? o1.getAuthor().compareTo(o2.getAuthor()) : 1;
				}
				return -1;
			}
		});
		table.addColumnSortHandler(authorSortHandler);
		
		ListHandler<ClassVersion> classSortHandler = new ListHandler<ClassVersion>(dataProvider.getList());
		classSortHandler.setComparator(classNameColumn, new Comparator<ClassVersion>() {
			public int compare(ClassVersion o1, ClassVersion o2) {
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null) {
					return (o2 != null) ? o1.getClassName().compareTo(o2.getClassName()) : 1;
				}
				return -1;
			}
		});
		table.addColumnSortHandler(classSortHandler);
		
		ListHandler<ClassVersion> dateSortHandler = new ListHandler<ClassVersion>(dataProvider.getList());
		dateSortHandler.setComparator(dateColumn, new Comparator<ClassVersion>() {
			public int compare(ClassVersion o1, ClassVersion o2) {
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null) {
					return (o2 != null) ? o1.getDate().compareTo(o2.getDate()) : 1;
				}
				return -1;
			}
		});
		table.addColumnSortHandler(dateSortHandler);

		table.getColumnSortList().push(dateColumn);

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(table);
		versions.setWidget(table);
	}

	@Override
	public void setVersions(List<ClassVersion> versions) {
		dataProvider.getList().clear();
		dataProvider.getList().addAll(versions);
	}

	@Override
	public void setAuthors(List<String> result) {
		authorFilter.clear();
		for(String s: result){
			authorFilter.addItem(s);
		}
	}

}
