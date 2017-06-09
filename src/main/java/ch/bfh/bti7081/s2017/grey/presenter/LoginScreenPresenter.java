package ch.bfh.bti7081.s2017.grey.presenter;

import ch.bfh.bti7081.s2017.grey.view.AppointmentViewImpl;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2017.grey.util.Authentication;

public class LoginScreenPresenter {

	public static void login(String username, String password){
		if(Authentication.authenticate(username, password)){
			Notification.show("Logging in...", Notification.Type.WARNING_MESSAGE);
			VaadinSession.getCurrent().setAttribute("user", username);
			Page.getCurrent().setUriFragment("!"+ AppointmentViewImpl.NAME);
		}
		else{
			Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
		}
	}

	public static void loggedIn() {
		if(VaadinSession.getCurrent().getAttribute("user") != null){
			Page.getCurrent().setUriFragment("!"+AppointmentViewImpl.NAME);
		}
	}
}