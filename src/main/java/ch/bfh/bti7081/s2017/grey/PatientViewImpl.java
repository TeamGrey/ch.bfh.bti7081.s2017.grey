package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientViewImpl extends VerticalLayout implements PatientView, View{
    public static final String NAME = "PatientViewImpl";
    private List <PatientViewListener> listeners = new ArrayList<PatientViewListener>();
    private Binder <Patient> binder = new Binder<>(Patient.class);
    private TextField firstname = new TextField();
private TextField lastname = new TextField();
private TextField created = new TextField();
private TextField changed = new TextField();
public Button editButton = new Button();
public Button saveButton = new Button();
public PatientViewImpl(){
    binder.forField(firstname).bind(Patient::getFirstname,Patient::setFirstname);
    binder.forField(lastname).bind(Patient::getLastname,Patient::setLastname);

    firstname.setCaption("Vorname");
    lastname.setCaption("Nachname");
    created.setEnabled(false);
    created.setCaption("Erstellt am");
    changed.setEnabled(false);
    changed.setCaption("Geändert am");
    editButton.setVisible(true);
    saveButton.setVisible(false);
    editButton.setCaption("Ändern");
    saveButton.setCaption("Speichern");
    editButton.addClickListener((Button.ClickEvent e) ->{
    for(PatientViewListener listener: listeners){
        listener.editClick();
        editButton.setVisible(!editButton.isVisible());
        saveButton.setVisible(!saveButton.isVisible());
        }
    });
    this.addComponents(firstname,lastname,created,changed,editButton,saveButton);
}

    @Override
    public void addListener(PatientViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void enter(ViewChangeEvent viewChangeEvent) {

    }
}
