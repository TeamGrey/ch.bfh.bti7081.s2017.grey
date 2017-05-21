package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.service.StaffService;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.v7.ui.Calendar;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.impl.AppointmentServiceImpl;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class AppointmentView extends HorizontalLayout implements View{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "AppointmentView";

	public AppointmentView(){
		final Button editButton = new Button();
        final Button addButton = new Button();
        final DateTimeField startDate = new DateTimeField();
        final DateTimeField endDate = new DateTimeField();
        final TextArea description = new TextArea();
        final TextField title = new TextField();
        final Label label = new Label();
        final VerticalLayout layout = new VerticalLayout();
        final VerticalLayout rightLayout = new VerticalLayout();

        editButton.setCaption("Bearbeiten");
        addButton.setCaption("Termin hinzufügen");
        startDate.setCaption("Beginn");
        endDate.setCaption("Ende");
        description.setCaption("Beschreibung");
        title.setCaption("Titel");

        startDate.setResolution(DateTimeResolution.MINUTE);
        endDate.setResolution(DateTimeResolution.MINUTE);

		Calendar cal = new Calendar("My Calendar");
		cal.setWidth("600px");
		cal.setHeight("300px");

		addButton.addClickListener(e->{
		    AppointmentService appointmentService = new AppointmentServiceImpl();
            StaffService staffService = new StaffServiceImpl();
            String currentUser = VaadinSession.getCurrent().getAttribute("user").toString();
            appointmentService.createAppointmentDummyPatient(staffService.findStaffByLogin(currentUser),
                    description.getValue(),
                    title.getValue(),
                    startDate.getValue(),
                    endDate.getValue());

			label.setCaption("Neuer Termin hinzugefügt am " + startDate.getValue() +" bis "+ endDate.getValue());
			rightLayout.addComponent(label);
		});
		startDate.addFocusListener(e->{
			this.setEnabled(true);
		});

		layout.addComponents(cal, title, startDate, endDate,description,addButton);
		this.addComponents(layout,rightLayout);
		Design design = new Design();
		addComponent(design.insertContent(layout));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Appointment.loggedIn();		
	}
}

