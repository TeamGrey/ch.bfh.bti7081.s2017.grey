package ch.bfh.bti7081.s2017.grey;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class MyUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		new Navigator(this, this);
		
		getNavigator().addView(LoginScreen.NAME, LoginScreen.class);
		getNavigator().addView(AppointmentView.NAME, AppointmentView.class);
		getNavigator().setErrorView(LoginScreen.class);
		
		router("");
	}
	
	private void router(String route){
		Notification.show(route);
		if(getSession().getAttribute("user") != null){
			getNavigator().navigateTo(AppointmentView.NAME);
		}else{
			getNavigator().navigateTo(LoginScreen.NAME);
		}
	}

}
