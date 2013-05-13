package xrm.test.bv.server;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {
	ApplicationContext context;

	private static ApplicationContextProvider instance = null;

	private ApplicationContextProvider() {
	}

	public static ApplicationContextProvider getInstance() {
		if (instance == null) {
			instance = new ApplicationContextProvider();
		}
		return instance;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context = arg0;
	}

	public ApplicationContext getApplicationContext() {
		return context;
	}

	public static Object createAutowiredBean(Class<?> cls) {
		return getInstance().getApplicationContext()
				.getAutowireCapableBeanFactory().createBean(cls);
	}

}
