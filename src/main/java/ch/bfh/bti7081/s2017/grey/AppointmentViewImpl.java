package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import com.vaadin.annotations.PropertyId;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Calendar;
import com.vaadin.v7.ui.components.calendar.event.BasicEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class AppointmentViewImpl extends HorizontalLayout implements AppointmentView, View{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "AppointmentViewImpl";

	private List<AppontmentViewListener> listeners = new ArrayList<AppontmentViewListener>();

	private Binder<Appointment> binder = new Binder<>(Appointment.class);
	private BeanItemContainer<BasicEvent> container;

	private Button editButton = new Button();
	private Button addButton = new Button();
	@PropertyId("date")
	private DateField startDate = new DateField();
	@PropertyId("finished")
	private DateField endDate = new DateField();
	@PropertyId("description")
	private TextField terminBeschrieb = new TextField();
	@PropertyId("patient")
	private ComboBox<Patient> patients = new ComboBox<>("Patients");
	private Label label = new Label();
	private Calendar cal = new Calendar("My Calendar");
	private VerticalLayout layout = new VerticalLayout();
	private VerticalLayout rightLayout = new VerticalLayout();


	public AppointmentViewImpl() {
		binder.forField(startDate).withConverter(new LocalDateToTimestampConverter()).bind(Appointment::getDate, Appointment::setDate);
		binder.forField(endDate).withConverter(new LocalDateToTimestampConverter()).bind(Appointment::getFinished, Appointment::setFinished);
		binder.forField(terminBeschrieb).bind(Appointment::getDescription, Appointment::setDescription);
		binder.forField(patients).bind(Appointment::getPatient, Appointment::setPatient);

		editButton.setCaption("Bearbeiten");
		startDate.setCaption("Termin Beginn");
		endDate.setCaption("Termin Ende");
		terminBeschrieb.setCaption("Termin Bezeichnung");
		addButton.setCaption("Termin hinzufügen");

		cal.setWidth("600px");
		cal.setHeight("300px");

		patients.setItemCaptionGenerator(Patient::getFirstname);

		addButton.addClickListener(e->{
			for(AppontmentViewListener listener : listeners) {
				listener.saveClick();
			}
		});
		startDate.addFocusListener(e->{
			this.setEnabled(true);
		});
		startDate.setCaption("Startdatum");
		layout.addComponents(endDate,startDate,terminBeschrieb, patients, addButton, cal);
		this.addComponents(layout,rightLayout);
		Design design = new Design();
		addComponent(design.insertContent(layout));
	}

	@Override
	public void addListener(AppontmentViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void setPatients(List<Patient> patients) {
		this.patients.setItems(patients);
	}

	@Override
	public void setAppointment(Appointment appointment) {
		binder.setBean(appointment);
	}

	@Override
	public void setAppointmentList(List<Appointment> appointmentList) {
		container = new BeanItemContainer<BasicEvent>(BasicEvent.class);
		for(Appointment appointment : appointmentList) {
			container.addBean(new BasicEvent("Event", appointment.getDescription(), appointment.getDate(), appointment.getFinished()));
		}
		container.sort(new Object[]{"date"}, new boolean[]{true});

		cal.setContainerDataSource(container, "caption",
				"description", "start", "end", "styleName");
	}

	@Override
	public void enter(ViewChangeEvent viewChangeEvent) {

	}
}

