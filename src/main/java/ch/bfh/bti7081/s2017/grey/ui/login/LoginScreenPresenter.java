package ch.bfh.bti7081.s2017.grey.ui.login;

import ch.bfh.bti7081.s2017.grey.ui.appointment.AppointmentViewImpl;
import ch.bfh.bti7081.s2017.grey.util.Authentication;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;

public class LoginScreenPresenter {

  public static void login(String username, String password) {
    if (Authentication.authenticate(username, password)) {
      Notification.show("Logging in...", Notification.Type.WARNING_MESSAGE);
      VaadinSession.getCurrent().setAttribute("user", username);
      Page.getCurrent().setUriFragment("!" + AppointmentViewImpl.NAME);
    } else {
      Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
    }
  }

  public static void loggedIn() {
    if (VaadinSession.getCurrent().getAttribute("user") != null) {
      Page.getCurrent().setUriFragment("!" + AppointmentViewImpl.NAME);
    }
  }
}