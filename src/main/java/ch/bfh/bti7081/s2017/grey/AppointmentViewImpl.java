package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Calendar;
import com.vaadin.v7.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.v7.ui.components.calendar.handler.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using . This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SuppressWarnings("ALL")
@Theme("mytheme")
public class AppointmentViewImpl extends HorizontalLayout implements AppointmentView, View{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "AppointmentViewImpl";

	private List<AppontmentViewListener> listeners = new ArrayList<AppontmentViewListener>();

	private Binder<Appointment> binder = new Binder<>(Appointment.class);
	private BeanItemContainer<AppointmentEvent> container;

	private Button newButton = new Button();
	private Button editButton = new Button();
	private Button deleteButton = new Button();
	private Button okButton = new Button();

	private Button monthView = new Button();
	private Button weekView = new Button();
	private Button dayView = new Button();

	private DateTimeField startDate = new DateTimeField();
	private DateTimeField endDate = new DateTimeField();
	private TextField terminTitel = new TextField();
	private TextField terminBeschrieb = new TextField();
	private ComboBox<Patient> patients = new ComboBox<>("Patients");

	private Calendar cal = new Calendar("Termine");

	private Window appointmentWindow = new Window("Termin");
	private FormLayout appointmentLayout = new FormLayout();
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout buttonLayout = new HorizontalLayout();
	private HorizontalLayout calendarLayout = new HorizontalLayout();


	public AppointmentViewImpl() {
		binder.forField(startDate).bind(Appointment::getDate, Appointment::setDate);
		binder.forField(endDate).bind(Appointment::getEndDate, Appointment::setEndDate);
		binder.forField(terminTitel).bind(Appointment::getTitle, Appointment::setTitle);
		binder.forField(terminBeschrieb).bind(Appointment::getDescription, Appointment::setDescription);
		binder.forField(patients).bind(Appointment::getPatient, Appointment::setPatient);

		newButton.setCaption("Neuer Termin");
		editButton.setCaption("Termin bearbeiten");
		deleteButton.setCaption("Termin löschen");
		okButton.setCaption("OK");
		editButton.setEnabled(false);

		monthView.setCaption("Monat");
		weekView.setCaption("Woche");
		dayView.setCaption("Tag");

		startDate.setCaption("Termin Beginn");
		endDate.setCaption("Termin Ende");
		terminTitel.setCaption("Termin Bezeichnung");
		terminBeschrieb.setCaption("Termin Beschreibung");

		cal.setWidth("1000px");
		cal.setHeight("600px");
		cal.setLocale(Locale.GERMANY);
		cal.setWeeklyCaptionFormat("dd.MM.yyyy");
		cal.setReadOnly(false);

		patients.setItemCaptionGenerator(Patient::getFirstname);

		appointmentWindow.setWidth("500px");
		appointmentWindow.setModal(true);

		newButton.addClickListener(e -> {
            for(AppontmentViewListener listener : listeners) {
                listener.newClick();
            }
			UI.getCurrent().addWindow(appointmentWindow);
        });
		editButton.addClickListener(e -> {
			UI.getCurrent().addWindow(appointmentWindow);
		});
		deleteButton.addClickListener(e -> {
			for(AppontmentViewListener listener : listeners) {
				listener.deleteClick();
			}
		});
		okButton.addClickListener(e -> {
			for(AppontmentViewListener listener : listeners) {
				listener.saveClick();
			}
			appointmentWindow.close();
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

		cal.setHandler((CalendarComponentEvents.EventClickHandler) eventClick -> {
            AppointmentEvent event = (AppointmentEvent)eventClick.getCalendarEvent();

            for(AppontmentViewListener listener : listeners) {
                listener.appointmentSelect(event.getAppointment());
            }
        });
		cal.setHandler((CalendarComponentEvents.EventResizeHandler) eventResize -> {
            AppointmentEvent event = (AppointmentEvent)eventResize.getCalendarEvent();

            for(AppontmentViewListener listener : listeners) {
                listener.appointmentSelect(event.getAppointment());
                listener.appointmentReisize(eventResize.getNewStart(), eventResize.getNewEnd());
            }
        });
		cal.setHandler((CalendarComponentEvents.EventMoveHandler) eventMove -> {
            AppointmentEvent event = (AppointmentEvent)eventMove.getCalendarEvent();

            for(AppontmentViewListener listener : listeners) {
                listener.appointmentSelect(event.getAppointment());
                listener.appointmentMove(eventMove.getNewStart());
            }
        });
		cal.setHandler(new BasicWeekClickHandler() {
            protected void setDates(CalendarComponentEvents.WeekClick event,
                                    Date start, Date end) {
                for(AppontmentViewListener listener : listeners) {
                    listener.dateRangeSelect(start, end);
                }
			}
		});
		cal.setHandler(new BasicDateClickHandler() {
			public void dateClick(CalendarComponentEvents.DateClickEvent dateClickEvent) {
				Date date = dateClickEvent.getDate();

				for(AppontmentViewListener listener : listeners) {
					listener.dateSelect(date);
				}
			}
		});
		cal.setHandler(new BasicBackwardHandler() {
            protected void setDates(CalendarComponentEvents.BackwardEvent event,
                                    Date start, Date end) {
                for (AppontmentViewListener listener : listeners) {
                    listener.dateRangeSelect(start, end);
                }
            }
        });
        cal.setHandler(new BasicForwardHandler() {
            protected void setDates(CalendarComponentEvents.ForwardEvent event,
                                    Date start, Date end) {
                for(AppontmentViewListener listener : listeners) {
                    listener.dateRangeSelect(start, end);
                }
            }
        });

		buttonLayout.addComponents(newButton, editButton, deleteButton);
		calendarLayout.addComponents(monthView, weekView, dayView);
		appointmentLayout.addComponents(startDate, endDate, terminTitel, terminBeschrieb, patients, okButton);
		layout.addComponents(buttonLayout, calendarLayout, cal);
		appointmentWindow.setContent(appointmentLayout);
		this.addComponents(layout);
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
	public void setAppointment(Appointment appointment, boolean isEditMode) {
		binder.setBean(appointment);
		this.editButton.setEnabled(isEditMode);
		this.deleteButton.setEnabled(isEditMode);
	}

	@Override
	public void setAppointmentList(List<Appointment> appointmentList) {
		container = new BeanItemContainer<AppointmentEvent>(AppointmentEvent.class);
		for(Appointment appointment : appointmentList) {
			container.addBean(new AppointmentEvent(appointment, Timestamp.valueOf(appointment.getDate()), Timestamp.valueOf(appointment.getEndDate()), appointment.getTitle(), appointment.getDescription()));
		}
		container.sort(new Object[]{"start"}, new boolean[]{true});

		cal.setContainerDataSource(container, "caption",
				"description", "start", "end", "styleName");
		cal.markAsDirty();
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