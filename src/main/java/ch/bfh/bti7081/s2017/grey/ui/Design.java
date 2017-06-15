package ch.bfh.bti7081.s2017.grey.ui;

import ch.bfh.bti7081.s2017.grey.ui.appointment.AppointmentViewImpl;
import ch.bfh.bti7081.s2017.grey.ui.finish_appointment.FinishAppointmentViewImpl;
import ch.bfh.bti7081.s2017.grey.ui.login.LoginScreen;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author Ken
 */
public class Design extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Design";
	private CustomLayout mainlayout = new CustomLayout("mainlayout");
	private Component lFooter = new Label(VaadinIcons.COPYRIGHT.getHtml()+" TeamGrey BFH 2017", ContentMode.HTML);
	private HorizontalLayout header;
	private Button logout = new Button("Logout");
	private Button back = new Button("Back");
	private Button finish = new Button("Finish");
	ThemeResource resource = new ThemeResource("img/logo.png");
	Image image = new Image("",resource);

	/**
	 * Main design of the website with all its core functions
	 */
	public Design(){
	    image.setId("headerlogo");
		mainlayout.setSizeFull();
		lFooter.setSizeFull();

		logout.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				VaadinSession.getCurrent().close();	
				Notification.show("Logging out...", Notification.Type.WARNING_MESSAGE);
				Page.getCurrent().setUriFragment("!"+ LoginScreen.NAME);
				Page.getCurrent().reload();
			}
		});
		
		back.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				Page.getCurrent().setUriFragment("!"+ AppointmentViewImpl.NAME);
			}
		});

		finish.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {
				Page.getCurrent().setUriFragment("!"+ FinishAppointmentViewImpl.NAME);
			}
		});
	}

	/**
	 * 
	 * @param content Content to insert
	 * @param canGoBack If the back-button will be visible
	 * @return Inserted component
	 */
	public Design insertContent(Component content, boolean canGoBack, boolean canFinish){
		header = new HorizontalLayout();
		if(VaadinSession.getCurrent().getAttribute("user") != null){
			header.addComponents(image, logout);
			if(canGoBack) header.addComponent(back);
			if(canFinish) header.addComponent(finish);
		}
		else{
			header.addComponents(image);
		}
		header.setSizeFull();
		content.setSizeFull();

		mainlayout.addComponent(header, "top");
		mainlayout.addComponent(content, "center");
		mainlayout.addComponent(lFooter, "bottom");

		this.addComponent(mainlayout);
		return this;
	}
}
