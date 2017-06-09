package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.presenter.PatientTabsPresenter;
import ch.bfh.bti7081.s2017.grey.view.AppointmentViewImpl;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;

public class Design extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Design";
	private CustomLayout mainlayout = new CustomLayout("mainlayout");
	private Component lFooter = new Label(VaadinIcons.COPYRIGHT.getHtml()+" TeamGrey BFH 2017", ContentMode.HTML);
	private Button logout = new Button("Logout");
	private Button patientTabs = new Button("PatientTabs");// TODO Temporärer Zugang
	private Button appointmentView = new Button("AppointmentView");// TODO Temporärer Zugang
	ThemeResource resource = new ThemeResource("img/logo.png");
	Image image = new Image("",resource);
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
				Page.getCurrent().setUriFragment("!"+LoginScreen.NAME);
				Page.getCurrent().reload();
			}
		});
		
		patientTabs.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				Page.getCurrent().setUriFragment("!"+ PatientTabsPresenter.NAME);
			}
		});
		
		appointmentView.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				Page.getCurrent().setUriFragment("!"+ AppointmentViewImpl.NAME);
			}
		});
	}

	public Design insertContent(Component content){
		HorizontalLayout header = new HorizontalLayout();
		if(VaadinSession.getCurrent().getAttribute("user") != null){
			header.addComponents( image, logout, patientTabs, appointmentView);
		}
		else{
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
