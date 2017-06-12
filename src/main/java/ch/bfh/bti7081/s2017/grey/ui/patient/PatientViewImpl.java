package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import javassist.expr.Cast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientViewImpl extends HorizontalLayout implements PatientView, View {
    public static final String NAME = "PatientViewImpl";
    private List<PatientViewListener> listeners = new ArrayList<PatientViewListener>();
    private Binder<Patient> binder = new Binder<>(Patient.class);
    private TextField firstname = new TextField();
    private TextField lastname = new TextField();
    private TextField created = new TextField();
    private TextField changed = new TextField();
    public Button editButton = new Button("Ändern",VaadinIcons.PENCIL);
    public Button saveButton = new Button("Speichern", VaadinIcons.STORAGE);
    public Button cancelButton = new Button("Abbrechen", VaadinIcons.WARNING);
    public PatientViewImpl() {
        binder.forField(firstname).bind(Patient::getFirstname, Patient::setFirstname);
        binder.forField(lastname).bind(Patient::getLastname, Patient::setLastname);
       // binder.forField(changed).bind(Patient::getChanged,Patient::setChanged);
       // binder.forField(created).getField(Patient::getCreated,Patient::setChanged);
        firstname.setCaption("Vorname");
        firstname.setEnabled(false);
        lastname.setCaption("Nachname");
        lastname.setEnabled(false);
        created.setEnabled(false);
        created.setCaption("Erstellt am");
        changed.setEnabled(false);
        changed.setCaption("Geändert am");
        editButton.setVisible(true);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
        editButton.addClickListener((Button.ClickEvent e) -> {
            for (PatientViewListener listener : listeners) {
                listener.editClick();
                editButton.setVisible(!editButton.isVisible());
                saveButton.setVisible(!saveButton.isVisible());
                cancelButton.setVisible(!cancelButton.isVisible());
                firstname.setEnabled(true);
                lastname.setEnabled(true);
            }

        });

        saveButton.addClickListener((Button.ClickEvent e)->{
            for (PatientViewListener listener : listeners){
                listener.saveClick();
                editButton.setVisible(!editButton.isVisible());
                saveButton.setVisible(!saveButton.isVisible());
                cancelButton.setVisible(!cancelButton.isVisible());
                firstname.setEnabled(false);
                lastname.setEnabled(false);
            }
        });

        cancelButton.addClickListener((Button.ClickEvent e)->{
            for (PatientViewListener listener: listeners){
                listener.cancelClick();
                saveButton.setVisible(!saveButton.isVisible());
                cancelButton.setVisible(!cancelButton.isVisible());
                editButton.setVisible(!cancelButton.isVisible());
                firstname.setEnabled(false);
                lastname.setEnabled(false);
            }

        });

        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(firstname, lastname, created, changed, editButton, saveButton, cancelButton);
        this.addComponent(layout);
       //Design design = new Design();
       // addComponent(design.insertContent(layout));
    }

    @Override
    public void addListener(PatientViewListener listener) {
        listeners.add(listener);
    }


    public void setPatient(Patient patient){
        this.binder.setBean(patient);
    }

	@Override
	public void enter(ViewChangeEvent event) {
	for (PatientViewListener listener : listeners){
	    listener.viewEntered(VaadinSession.getCurrent().getAttribute("user").toString());
    }
	}
}