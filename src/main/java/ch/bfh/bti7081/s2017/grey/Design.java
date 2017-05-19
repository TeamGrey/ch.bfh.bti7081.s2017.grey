package ch.bfh.bti7081.s2017.grey;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;

public class Design extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Design";
	private CustomLayout mainlayout = new CustomLayout("mainlayout");
	private Component lHeader = new Label("Header");
	private Component l2Header = new Label("Header2");
	private Component lFooter = new Label("Footer");
	private Button logout = new Button("Logout");
	private Button patientTabs = new Button("PatientTabs");//Temporärer Zugang
	private Button appointmentView = new Button("AppointmentView");//Temporärer Zugang

	public Design(){
		mainlayout.setSizeFull();
		lHeader.setSizeFull();
		lFooter.setSizeFull();

		logout.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				VaadinSession.getCurrent().close();	
				Notification.show("Logging out...", Notification.Type.WARNING_MESSAGE);
				Page.getCurrent().setUriFragment("!"+LoginScreen.NAME);
				Page.getCurrent().reload();
			}
		});
		
		patientTabs.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				Page.getCurrent().setUriFragment("!"+PatientTabsPresenter.NAME);
			}
		});
		
		appointmentView.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				Page.getCurrent().setUriFragment("!"+AppointmentView.NAME);
			}
		});
	}

	public Design insertContent(Component content){
		HorizontalLayout header = new HorizontalLayout();
		if(VaadinSession.getCurrent().getAttribute("user") != null){
			header.addComponents(lHeader, l2Header, logout, patientTabs, appointmentView);
		}
		else{
			header.addComponents(lHeader, l2Header);
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