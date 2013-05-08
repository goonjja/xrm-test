package xrm.test.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "createTaskBean")
@RequestScoped
public class CreateTaskBean {
	@ManagedProperty("#{dataBean}")
	private DataBean dataBean;

	private String name = "";

	public void createTask() {
		dataBean.getTasks().add(new Task(name));
		name = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataBean getDataBean() {
		return dataBean;
	}

	public void setDataBean(DataBean dataBean) {
		this.dataBean = dataBean;
	}

}
