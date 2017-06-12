package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.presenter.LoginScreenPresenter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author Ken
 */
public class LoginScreen extends HorizontalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "LoginScreen";
	ThemeResource logo = new ThemeResource("img/logo.png");
    Image loginLogo = new Image("",logo);


	/**
	 * Defines the login-screen with all its visible elements
	 */
	public LoginScreen(){
		loginLogo.setId("loginlogo");
		FormLayout content = new FormLayout();
		content.addComponent(loginLogo);
		TextField username = new TextField("Username");
		content.addComponent(username);
		PasswordField password = new PasswordField("Password");
		content.addComponent(password);

		Button send = new Button("Enter");
		send.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				LoginScreenPresenter.login(username.getValue(), password.getValue());
			}
		});
		send.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		content.addComponent(send);

		Design design = new Design();
		addComponent(design.insertContent(content, false));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		LoginScreenPresenter.loggedIn();
	}
}