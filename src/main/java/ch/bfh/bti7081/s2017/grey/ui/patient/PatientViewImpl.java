package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannes on 17.05.2017
 * Split View into Pieces by joel on 15.06.2017
 */
public class PatientViewImpl extends HorizontalLayout implements PatientView, View {
    public static final String NAME = "PatientViewImpl";
    final GridLayout profileLayout = new GridLayout(2, 6);
    private Button editButton = new Button("Ändern", VaadinIcons.PENCIL);
    private Button saveButton = new Button("Speichern", VaadinIcons.STORAGE);
    private Button cancelButton = new Button("Abbrechen", VaadinIcons.WARNING);
    private Button editDrugButton = new Button("Medikation Bearbeiten", VaadinIcons.PILL);
    private Button editHabitButton = new Button("Gwohnheit Bearbeiten", VaadinIcons.BARCODE);
    private List<PatientViewListener> listeners = new ArrayList<>();
    private Binder<Patient> binder = new Binder<>(Patient.class);
    private Binder<EmergencyContact> emBinder = new Binder<>(EmergencyContact.class);
    private List<Habit> habitArray = new ArrayList<>();
    private List<Habit> habitOptions = new ArrayList<>();
    private List<Drug> drugArray = new ArrayList<>();
    private List<Drug> drugOptions = new ArrayList<>();
    private TextField firstname = new TextField();
    private TextField lastname = new TextField();
    private TextField created = new TextField();
    private TextField changed = new TextField();
    private FormLayout patientinfo = new FormLayout();
    private FormLayout patientFormControls = new FormLayout();
    private Grid<EmergencyContact> emcGrid = new Grid<>();
    private Grid<Drug> drugGrid = new Grid<>();
    private Grid<Habit> habitGrid = new Grid<>();
    private Button addNewEmcButon = new Button();
    private ThemeResource resource = new ThemeResource(
            "img/profile.png");
    private Image image = new Image("Image from file", resource);

    private PatientDrugWindow drugWindow = new PatientDrugWindow();
    private PatientHabitWindow habitWindow = new PatientHabitWindow();
    private PatientEmergencyContactForm emcWindow = new PatientEmergencyContactForm();

    public PatientViewImpl() {
        image.setId("profilbild");
        binder.forField(firstname).bind(Patient::getFirstname, Patient::setFirstname);
        binder.forField(lastname).bind(Patient::getLastname, Patient::setLastname);
        emcGrid.addColumn(EmergencyContact::getFirstname).setCaption("Vorname");
        emcGrid.addColumn(EmergencyContact::getLastname).setCaption("Nachname");
        emcGrid.addColumn(EmergencyContact::getPhonenumber).setCaption("Telefonnummer");

        drugGrid.addColumn(Drug::getName).setCaption("Bezeichnung");
        emcGrid.setWidth("100%");
        habitGrid.addColumn(Habit::getName).setCaption("Gewohnheiten");
        emcGrid.setCaptionAsHtml(true);
        habitGrid.setWidth("100%");
        firstname.setCaption("Vorname");
        lastname.setCaption("Nachname");
        created.setCaption("Erstellt am");
        this.editButton.setEnabled(true);
        changed.setCaption("Geändert am");
        this.patientinfo.addComponents(firstname, lastname, created, changed);
        this.patientinfo.setEnabled(false);
        patientFormControls.addComponents(editButton, saveButton);
        editButton.setVisible(true);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
        addNewEmcButon.setCaption("Notfalkontakt Hinzufügen");


        /*
         Putting everything inside the Gridlayout
         */
        profileLayout.addComponent(image, 0, 0);
        profileLayout.addComponent(patientinfo, 1, 0);
        profileLayout.addComponent(patientFormControls, 1, 1);
        profileLayout.addComponent(addNewEmcButon, 0, 2);
        profileLayout.addComponent(editDrugButton, 1, 2);
        profileLayout.addComponent(emcGrid, 0, 3);
        profileLayout.addComponent(drugGrid, 1, 3);
        profileLayout.addComponent(editHabitButton, 0, 4);
        profileLayout.addComponent(habitGrid, 0, 5);
        profileLayout.setSizeUndefined();
        profileLayout.setSpacing(true);
        setSizeUndefined();
        profileLayout.setWidth("100%");
        profileLayout.setId("profilelayout");

        editButton.addClickListener((Button.ClickEvent e) -> {
            editButton.setVisible(!editButton.isVisible());
            saveButton.setVisible(!saveButton.isVisible());
            cancelButton.setVisible(!cancelButton.isVisible());
            this.patientinfo.setEnabled(true);

        });

        saveButton.addClickListener((Button.ClickEvent e) -> {
            editButton.setVisible(!editButton.isVisible());
            saveButton.setVisible(!saveButton.isVisible());
            cancelButton.setVisible(!cancelButton.isVisible());
            this.patientinfo.setEnabled(false);
        });

        cancelButton.addClickListener((Button.ClickEvent e) -> {
            saveButton.setVisible(!saveButton.isVisible());
            cancelButton.setVisible(!cancelButton.isVisible());
            editButton.setVisible(!cancelButton.isVisible());
            firstname.setEnabled(false);
            lastname.setEnabled(false);
        });

        editDrugButton.addClickListener((Button.ClickEvent e) -> drugWindow.open(drugOptions, drugArray));
        editHabitButton.addClickListener((Button.ClickEvent e) -> habitWindow.open(habitOptions, habitArray));
        addNewEmcButon.addClickListener((Button.ClickEvent e) -> emcWindow.open());
        emcGrid.addItemClickListener(e -> {
            this.emBinder.setBean(e.getItem());
            EmergencyContact emc = e.getItem();
            emcWindow.open(emc.getFirstname(), emc.getLastname(), emc.getPhonenumber(), emc.getId());
        });

        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(profileLayout);
        this.addComponent(layout);
    }

    @Override
    public void addListener(PatientViewListener listener) {
        listeners.add(listener);
        drugWindow.addListener(listener);
        habitWindow.addListener(listener);
        emcWindow.addListener(listener);
    }

    @Override
    public void setDrugOptions(List<Drug> allDrugs) {
        drugOptions.clear();
        drugOptions.addAll(allDrugs);
    }

    @Override
    public void setHabitOptions(List<Habit> allHabits) {
        habitOptions.clear();
        habitOptions.addAll(allHabits);
    }

    public void setPatient(Patient patient) {
        this.binder.setBean(patient);
    }


    @Override
    public void setEmContact(List<EmergencyContact> emContact) {
        this.emcGrid.setItems(emContact);
        emcGrid.clearSortOrder();
    }

    public void setDrugList(List<Drug> drugList) {
        drugArray.clear();
        drugArray.addAll(drugList);
        drugGrid.setItems(drugArray);
    }

    @Override
    public void setHabitList(List<Habit> habitList) {
        habitArray.clear();
        habitArray.addAll(habitList);
        this.habitGrid.setItems(habitList);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        for (PatientViewListener listener : listeners) {
            listener.viewEntered(VaadinSession.getCurrent().getAttribute("user").toString());
        }
    }
}
