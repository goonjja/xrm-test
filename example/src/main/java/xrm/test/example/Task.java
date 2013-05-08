package xrm.test.example;

import java.io.Serializable;

import xrm.test.annotations.Version;

@Version(author = "vedernikov", date = "8.05.2013", comment = "Added field: 'completed'")
public class Task implements Serializable {
	private static final long serialVersionUID = 6414097801813478012L;
	private String name = "";
	private Boolean completed = false;

	public Task(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

}
