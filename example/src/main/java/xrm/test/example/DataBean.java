package xrm.test.example;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import xrm.test.versions.annotation.Version;

@Version(author = "pupkin", date = "13.05.2013", comment = "New version!")
@ManagedBean(name = "dataBean")
@SessionScoped
public class DataBean implements Serializable {
	private static final long serialVersionUID = -5087503228238906555L;
	private List<Task> tasks = new LinkedList<Task>();

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTaskCompleted(ValueChangeEvent event) {
		String id = event.getComponent().getId();
		id = id.substring(5);
		int intId = Integer.parseInt(id);
		if (intId < tasks.size()) {
			tasks.get(intId).setCompleted(true);
		}
	}

	public void deleteTask(int index) {
		if (index >= 0 && index < tasks.size()) {
			tasks.remove(index);
		}
	}

}
