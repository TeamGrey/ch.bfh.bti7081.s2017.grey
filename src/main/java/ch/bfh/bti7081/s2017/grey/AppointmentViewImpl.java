package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.v7.ui.Calendar;

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

	private Button editButton = new Button();
	private Button addButton = new Button();
	private DateField startDate = new DateField();
	private DateField endDate = new DateField();
	private TextField terminBeschrieb = new TextField();
	private Label label = new Label();
	private Calendar cal = new Calendar("My Calendar");
	private VerticalLayout layout = new VerticalLayout();
	private VerticalLayout rightLayout = new VerticalLayout();


	public AppointmentViewImpl() {
		editButton.setCaption("Bearbeiten");
		startDate.setCaption("Termin Beginn");
		endDate.setCaption("Termin Ende");
		terminBeschrieb.setCaption("Termin Bezeichnung");
		addButton.setCaption("Termin hinzufügen");
		endDate.setEnabled(false);
		startDate.setEnabled(false);

		cal.setWidth("600px");
		cal.setHeight("300px");

		addButton.addClickListener(e->{
			for(AppontmentViewListener listener : listeners) {
				listener.saveClick();
			}
		});
		startDate.addFocusListener(e->{
			this.setEnabled(true);
		});
		startDate.setCaption("Startdatum");
		layout.addComponents(endDate,startDate,terminBeschrieb,addButton, cal);
		this.addComponents(layout,rightLayout);
		Design design = new Design();
		addComponent(design.insertContent(layout));
	}

	@Override
	public void addListener(AppontmentViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void enter(ViewChangeEvent viewChangeEvent) {

	}
}

