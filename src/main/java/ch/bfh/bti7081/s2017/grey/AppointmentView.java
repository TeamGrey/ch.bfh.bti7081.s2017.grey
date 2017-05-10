package ch.bfh.bti7081.s2017.grey;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class AppointmentView  extends HorizontalLayout implements View{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "AppointmentView";

	public AppointmentView(){
    	
        final Button editButton = new Button();
        editButton.setCaption("Bearbeiten");
        final VerticalLayout layout = new VerticalLayout();
        final VerticalLayout rightLayout = new VerticalLayout();
        final DateField startDate = new DateField();
        startDate.setCaption("Termin Beginn");
        final DateField endDate = new DateField();
        endDate.setCaption("Termin Ende");
        final TextField terminBeschrieb = new TextField();
        terminBeschrieb.setCaption("Termin Bezeichnung");
        final Button button = new Button();
        final Label label = new Label();
        button.setCaption("Termin hinzufügen");
        endDate.setEnabled(false);
        startDate.setEnabled(false);

        button.addClickListener(e->{
        Appointment thisappointment = new Appointment(startDate.getValue(),endDate.getValue());
            label.setCaption("Neuer Termin hinzugefügt am " +thisappointment.getStartTime()+" bis "+thisappointment.getEndTime());
            rightLayout.addComponent(label);
        });
        startDate.addFocusListener(e->{
            this.setEnabled(true);
        });
        startDate.setCaption("Startdatum");
        layout.addComponents(endDate,startDate,terminBeschrieb,button);
        this.addComponents(layout,rightLayout);
        
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}

