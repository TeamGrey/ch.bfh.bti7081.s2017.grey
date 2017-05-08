package ch.bfh.bti7081.s2017.grey;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification;
import javax.servlet.annotation.WebServlet;
import java.time.format.DateTimeFormatter;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class AppointmentView extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout doublelayout = new HorizontalLayout();
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
        doublelayout.addComponents(layout,rightLayout);
        setContent(doublelayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AppointmentView.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

