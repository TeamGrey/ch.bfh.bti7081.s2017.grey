package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import com.vaadin.annotations.PropertyId;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Calendar;
import com.vaadin.v7.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.v7.ui.components.calendar.event.BasicEvent;

import java.sql.Timestamp;
import java.util.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using . This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class AppointmentViewImpl extends HorizontalLayout implements AppointmentView, View{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "AppointmentViewImpl";

	private List<AppontmentViewListener> listeners = new ArrayList<AppontmentViewListener>();

	private Binder<Appointment> binder = new Binder<>(Appointment.class);
	private BeanItemContainer<AppointmentEvent> container;

	private Button editButton = new Button();
	private Button addButton = new Button();

	private Button monthView = new Button();
	private Button weekView = new Button();
	private Button dayView = new Button();

	private DateTimeField startDate = new DateTimeField();
	private DateTimeField endDate = new DateTimeField();
	private TextField terminTitel = new TextField();
	private TextField terminBeschrieb = new TextField();
	private ComboBox<Patient> patients = new ComboBox<>("Patients");

	private Calendar cal = new Calendar("My Calendar");

	private VerticalLayout layout = new VerticalLayout();
	private VerticalLayout rightLayout = new VerticalLayout();
	private HorizontalLayout calendarView = new HorizontalLayout();


	public AppointmentViewImpl() {
		binder.forField(startDate).bind(Appointment::getDate, Appointment::setDate);
		binder.forField(endDate).bind(Appointment::getEndDate, Appointment::setEndDate);
		binder.forField(terminTitel).bind(Appointment::getTitle, Appointment::setTitle);
		binder.forField(terminBeschrieb).bind(Appointment::getDescription, Appointment::setDescription);
		binder.forField(patients).bind(Appointment::getPatient, Appointment::setPatient);

		addButton.setCaption("Termin hinzufügen");
		editButton.setCaption("Bearbeiten");

		monthView.setCaption("Monat");
		weekView.setCaption("Woche");
		dayView.setCaption("Tag");

		startDate.setCaption("Termin Beginn");
		endDate.setCaption("Termin Ende");
		terminBeschrieb.setCaption("Termin Bezeichnung");

		cal.setWidth("600px");
		cal.setHeight("300px");
		cal.setLocale(Locale.GERMANY);
		cal.setWeeklyCaptionFormat("dd.MM.yyyy");

		patients.setItemCaptionGenerator(Patient::getFirstname);

		addButton.addClickListener(e->{
			for(AppontmentViewListener listener : listeners) {
				listener.saveClick();
			}
		});

		monthView.addClickListener(e -> {
			for(AppontmentViewListener listener : listeners) {
				listener.monthViewSelect();
			}
		});
		weekView.addClickListener(e -> {
			for(AppontmentViewListener listener : listeners) {
				listener.weekViewSelect();
			}
		});
		dayView.addClickListener(e -> {
			for(AppontmentViewListener listener : listeners) {
				listener.dayViewSelect();
			}
		});

		startDate.addFocusListener(e->{
			this.setEnabled(true);
		});

		cal.setHandler(new CalendarComponentEvents.EventClickHandler() {
			@Override
			public void eventClick(CalendarComponentEvents.EventClick eventClick) {
				AppointmentEvent event = (AppointmentEvent)eventClick.getCalendarEvent();
				Appointment appointment = event.getAppointment();

				for(AppontmentViewListener listener : listeners) {
					listener.appointmentSelect(appointment);
				}
			}
		});

		calendarView.addComponents(monthView, weekView, dayView);
		layout.addComponents(startDate, endDate,terminBeschrieb, patients, addButton, calendarView, cal);
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
		container = new BeanItemContainer<AppointmentEvent>(AppointmentEvent.class);
		for(Appointment appointment : appointmentList) {
			container.addBean(new AppointmentEvent(appointment, Timestamp.valueOf(appointment.getDate()), Timestamp.valueOf(appointment.getEndDate()), appointment.getTitle(), appointment.getDescription()));
		}
		container.sort(new Object[]{"date"}, new boolean[]{true});

		cal.setContainerDataSource(container, "caption",
				"description", "start", "end", "styleName");
	}

	@Override
	public void setStartDate(Date startDate) {
		cal.setStartDate(startDate);
	}

	@Override
	public void setEndDate(Date endDate) {
		cal.setEndDate(endDate);
	}

	@Override
	public void enter(ViewChangeEvent viewChangeEvent) {
		for(AppontmentViewListener listener : listeners) {
			listener.viewEntered(VaadinSession.getCurrent().getAttribute("user").toString());
		}
	}
}

