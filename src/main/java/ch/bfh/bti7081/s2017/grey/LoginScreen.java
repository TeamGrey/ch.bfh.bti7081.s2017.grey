package ch.bfh.bti7081.s2017.grey;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2017.grey.util.Authentication;

public class LoginScreen extends HorizontalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "LoginScreen";

	public LoginScreen(){
		FormLayout content = new FormLayout();
		TextField username = new TextField("Username");
		content.addComponent(username);
		PasswordField password = new PasswordField("Password");
		content.addComponent(password);

		Button send = new Button("Enter");
		send.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				if(Authentication.authenticate(username.getValue(), password.getValue())){
					Notification.show("Logging in...", Notification.Type.WARNING_MESSAGE);
					VaadinSession.getCurrent().setAttribute("user", username.getValue());
					Page.getCurrent().setUriFragment("!"+AppointmentView.NAME);
				}
				else{
					Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
				}
			}
			
					
			
		});
		content.addComponent(send);
		
		Design test = new Design();
		addComponent(test.insertContent(content));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}