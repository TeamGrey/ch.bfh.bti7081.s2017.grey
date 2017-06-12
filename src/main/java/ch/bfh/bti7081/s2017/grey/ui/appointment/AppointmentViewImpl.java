package ch.bfh.bti7081.s2017.grey.ui.appointment;

import ch.bfh.bti7081.s2017.grey.ui.Design;
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
@SuppressWarnings("deprecation")
@Theme("mytheme")
public class AppointmentViewImpl extends HorizontalLayout implements AppointmentView, View{
	public static final String NAME = "AppointmentViewImpl";

	private List<AppointmentViewListener> listeners = new ArrayList<AppointmentViewListener>();

	private Binder<Appointment> binder = new Binder<>(Appointment.class);
	private BeanItemContainer<AppointmentEvent> container;

	private Button startButton = new Button();
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
	private AppointmentEvent clickedEvent = new AppointmentEvent(null, null, null, "", "");

	private Window appointmentWindow = new Window("Termin");
	private FormLayout appointmentLayout = new FormLayout();
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout buttonLayout = new HorizontalLayout();
	private HorizontalLayout calendarLayout = new HorizontalLayout();

	public AppointmentViewImpl() {
		binder.forField(startDate)
				.asRequired("Muss ausgefüllt werden")
				.withValidator(date -> date.isBefore(binder.getBean().getEndDate()), "Start Datum muss vor end Datum sein.")
				.bind(Appointment::getDate, Appointment::setDate);
		binder.forField(endDate)
				.asRequired("Muss ausgefüllt werden")
				.withValidator(date -> date.isAfter(binder.getBean().getDate()), "End Datum muss nach start Datum sein.")
				.bind(Appointment::getEndDate, Appointment::setEndDate);
		binder.forField(terminTitel)
				.asRequired("Muss ausgefüllt werden")
				.bind(Appointment::getTitle, Appointment::setTitle);
		binder.forField(terminBeschrieb)
				.bind(Appointment::getDescription, Appointment::setDescription);
		binder.forField(patients)
				.asRequired("Muss ausgefüllt werden")
				.bind(Appointment::getPatient, Appointment::setPatient);

		startButton.setCaption("Termin starten");
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

		cal.setWidth("100%");
		cal.setHeight("600px");
		cal.setLocale(Locale.GERMANY);
		cal.setWeeklyCaptionFormat("dd.MM.yyyy");
		cal.setReadOnly(false);

		patients.setItemCaptionGenerator(Patient::getFirstname);

		appointmentWindow.setWidth("100%");
		appointmentWindow.setModal(true);

		startButton.addClickListener(e -> {
			for(AppointmentViewListener listener : listeners) {
				listener.startClick();
			}
		});
		newButton.addClickListener(e -> {
            for(AppointmentViewListener listener : listeners) {
                listener.newClick();
            }
			UI.getCurrent().addWindow(appointmentWindow);
        });
		editButton.addClickListener(e -> {
			UI.getCurrent().addWindow(appointmentWindow);
		});
		deleteButton.addClickListener(e -> {
			for(AppointmentViewListener listener : listeners) {
				listener.deleteClick();
			}
		});
		okButton.addClickListener(e -> {
			for(AppointmentViewListener listener : listeners) {
				listener.saveClick();
			}
			appointmentWindow.close();
		});

		monthView.addClickListener(e -> {
			for(AppointmentViewListener listener : listeners) {
				listener.monthViewSelect();
			}
		});
		weekView.addClickListener(e -> {
			for(AppointmentViewListener listener : listeners) {
				listener.weekViewSelect();
			}
		});
		dayView.addClickListener(e -> {
			for(AppointmentViewListener listener : listeners) {
				listener.dayViewSelect();
			}
		});

		startDate.addFocusListener(e->{
			this.setEnabled(true);
		});

		cal.setHandler((CalendarComponentEvents.EventClickHandler) eventClick -> {
            AppointmentEvent event = (AppointmentEvent)eventClick.getCalendarEvent();

            clickedEvent.setStyleName("");
            clickedEvent = event;
            event.setStyleName("selected");
            cal.markAsDirty();

            for(AppointmentViewListener listener : listeners) {
                listener.appointmentSelect(event.getAppointment());
            }
        });
		cal.setHandler((CalendarComponentEvents.EventResizeHandler) eventResize -> {
            AppointmentEvent event = (AppointmentEvent)eventResize.getCalendarEvent();

            for(AppointmentViewListener listener : listeners) {
                listener.appointmentSelect(event.getAppointment());
                listener.appointmentReisize(eventResize.getNewStart(), eventResize.getNewEnd());
            }
        });
		cal.setHandler((CalendarComponentEvents.EventMoveHandler) eventMove -> {
            AppointmentEvent event = (AppointmentEvent)eventMove.getCalendarEvent();

            for(AppointmentViewListener listener : listeners) {
                listener.appointmentSelect(event.getAppointment());
                listener.appointmentMove(eventMove.getNewStart());
            }
        });
		cal.setHandler(new BasicWeekClickHandler() {
            protected void setDates(CalendarComponentEvents.WeekClick event,
                                    Date start, Date end) {
                for(AppointmentViewListener listener : listeners) {
                    listener.dateRangeSelect(start, end);
                }
			}
		});
		cal.setHandler(new BasicDateClickHandler() {
			public void dateClick(CalendarComponentEvents.DateClickEvent dateClickEvent) {
				Date date = dateClickEvent.getDate();

				for(AppointmentViewListener listener : listeners) {
					listener.dateSelect(date);
				}
			}
		});
		cal.setHandler(new BasicBackwardHandler() {
            protected void setDates(CalendarComponentEvents.BackwardEvent event,
                                    Date start, Date end) {
                for (AppointmentViewListener listener : listeners) {
                    listener.dateRangeSelect(start, end);
                }
            }
        });
        cal.setHandler(new BasicForwardHandler() {
            protected void setDates(CalendarComponentEvents.ForwardEvent event,
                                    Date start, Date end) {
                for(AppointmentViewListener listener : listeners) {
                    listener.dateRangeSelect(start, end);
                }
            }
        });

		buttonLayout.addComponents(startButton, newButton, editButton, deleteButton);
		calendarLayout.addComponents(monthView, weekView, dayView);
		appointmentLayout.addComponents(startDate, endDate, terminTitel, terminBeschrieb, patients, okButton);
		layout.addComponents(buttonLayout, calendarLayout, cal);
		appointmentWindow.setContent(appointmentLayout);
		this.addComponents(layout);
	}

	/**
	 * Adds a event listener
	 * @param listener new event listener
	 */
	@Override
	public void addListener(AppointmentViewListener listener) {
		listeners.add(listener);
	}

	/**
	 * Sets the list of patients that is shown in the combo-box
	 * @param patients list of patients
	 */
	@Override
	public void setPatients(List<Patient> patients) {
		this.patients.setItems(patients);
	}

	/**
	 * Set a new appointment that is displayed in the editor window
	 * @param appointment appointment that will be displayed
	 * @param isEditMode true if the edit buttons are enabled
	 */
	@Override
	public void setAppointment(Appointment appointment, boolean isEditMode) {
		binder.setBean(appointment);
		this.startButton.setEnabled(isEditMode);
		this.editButton.setEnabled(isEditMode);
		this.deleteButton.setEnabled(isEditMode);
	}

	/**
	 * Set the list of appointments that is displayed in the calendar
	 * @param appointmentList list of appointments
	 */
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

	/**
	 * Called when the ui is entered. Send an event to the presenter.
	 * @param viewChangeEvent event object
	 */
	@Override
	public void enter(ViewChangeEvent viewChangeEvent) {
		for(AppointmentViewListener listener : listeners) {
			listener.viewEntered(VaadinSession.getCurrent().getAttribute("user").toString());
		}

		Design design = new Design();
		this.addComponent(design.insertContent(layout, false));
	}
}