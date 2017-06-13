package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.*;
import ch.bfh.bti7081.s2017.grey.service.DrugService;
import ch.bfh.bti7081.s2017.grey.service.impl.DrugServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.EmergencyContactServiceImpl;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;

import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientViewImpl extends HorizontalLayout implements PatientView, View {
    public static final String NAME = "PatientViewImpl";
    private List<PatientViewListener> listeners = new ArrayList<PatientViewListener>();
    private Binder<Patient> binder = new Binder<>(Patient.class);
    private Binder<EmergencyContact> emBinder = new Binder<>(EmergencyContact.class);
    private TextField firstname = new TextField();
    private TextField lastname = new TextField();
    private TextField created = new TextField();
    private TextField changed = new TextField();
    private TextField emcFirstname = new TextField();
    private TextField emcLastname = new TextField();
    private TextField emcPhone = new TextField();
    private FormLayout patientinfo = new FormLayout();
    private FormLayout emcinfo = new FormLayout();
    private FormLayout drugInfo = new FormLayout();
    private EmergencyContactServiceImpl emergencyContactService = new EmergencyContactServiceImpl();
    private Label emLabel = new Label();
    private Button emSaveButton;
    private Grid <EmergencyContact> grid = new Grid<>();
    private Grid <Drug> drugGrid = new Grid<>();
    final GridLayout profileLayout = new GridLayout(3,3);

    private Window emergencyContactWindow = new Window("Notfallkontakt");
    private Window drugWindow = new Window("Medikation");
    private Button addNewEmcButon = new Button();
    public Button emcAddButton = new Button();
    private TextField drugName = new TextField();
    public Button editButton = new Button("Ändern",VaadinIcons.PENCIL);
    public Button saveButton = new Button("Speichern", VaadinIcons.STORAGE);
    public Button cancelButton = new Button("Abbrechen", VaadinIcons.WARNING);
    public Button addDrugButton = new Button("Medikation Hinzufügen", VaadinIcons.PILL);
    public Button addNewDrugButton = new Button("Hinzufügen");
    private ThemeResource resource = new ThemeResource(
            "img/profile.png");
    private Image image = new Image("Image from file", resource);

    public PatientViewImpl() {
        image.setId("profilbild");
        binder.forField(firstname).bind(Patient::getFirstname, Patient::setFirstname);
        binder.forField(lastname).bind(Patient::getLastname, Patient::setLastname);
        emBinder.forField(emcFirstname).bind(EmergencyContact::getFirstname,EmergencyContact::setFirstname);
        emBinder.forField(emcLastname).bind(EmergencyContact::getLastname,EmergencyContact::setLastname);
        emBinder.forField(emcPhone).bind(EmergencyContact::getPhonenumber,EmergencyContact::setPhonenumber);
        grid.addColumn(EmergencyContact::getFirstname).setCaption("Vorname");
        grid.addColumn(EmergencyContact::getLastname).setCaption("Nachname");
        grid.addColumn(EmergencyContact::getPhonenumber).setCaption("Telefonnummer");
        drugGrid.addColumn(Drug::getName).setCaption("Bezeichnung");
        grid.setWidth("100%");

        grid.setCaptionAsHtml(true);
        emergencyContactWindow.setWidth("100%");
        emergencyContactWindow.setModal(true);
        drugWindow.setWidth("100%");
        drugWindow.setModal(true);
       // binder.forField(changed).bind(Patient::getChanged,Patient::setChanged);
       // binder.forField(created).getField(Patient::getCreated,Patient::setChanged);
        firstname.setCaption("Vorname");
        lastname.setCaption("Nachname");
        created.setCaption("Erstellt am");
        this.editButton.setEnabled(true);
        changed.setCaption("Geändert am");
        emLabel.setCaption("Notfallkontakt");
        emcFirstname.setCaption("Vorname");
        emcLastname.setCaption("Nachname");
        emcPhone.setCaption("Telefon");
        emSaveButton = new Button();
        emSaveButton.setCaption("OK");
        addNewEmcButon.setCaption("Notfalkontakt Hinzufügen");
        emcinfo.addComponents(emLabel,emcFirstname,emcLastname,emcPhone, emSaveButton);
        emergencyContactWindow.setContent(emcinfo);
        drugWindow.setContent(drugName);
        this.patientinfo.addComponents(firstname,lastname,created,changed);
        this.patientinfo.setEnabled(false);
        editButton.setVisible(true);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);

        profileLayout.addComponent(image,0,0);
        profileLayout.addComponent(patientinfo,1,0);
        profileLayout.addComponent(addNewEmcButon,0,1);
        profileLayout.addComponent(addDrugButton,1,1);
        profileLayout.addComponent(grid,0,2);
        profileLayout.addComponent(drugGrid,1,2);
        profileLayout.setSizeUndefined();
        profileLayout.setSpacing(true);
        setSizeUndefined();
        profileLayout.setWidth("100%");
        profileLayout.setId("profilelayout");

        editButton.addClickListener((Button.ClickEvent e) -> {
            for (PatientViewListener listener : listeners) {
                listener.editClick();
                editButton.setVisible(!editButton.isVisible());
                saveButton.setVisible(!saveButton.isVisible());
                cancelButton.setVisible(!cancelButton.isVisible());
                this.patientinfo.setEnabled(true);
            }

        });

        emSaveButton.addClickListener((Button.ClickEvent e)->{
            UI.getCurrent().removeWindow(emergencyContactWindow);
            grid.clearSortOrder();
        });

        saveButton.addClickListener((Button.ClickEvent e)->{
            for (PatientViewListener listener : listeners){
                listener.saveClick();
                editButton.setVisible(!editButton.isVisible());
                saveButton.setVisible(!saveButton.isVisible());
                cancelButton.setVisible(!cancelButton.isVisible());
                this.patientinfo.setEnabled(false);
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

        addNewEmcButon.addClickListener((Button.ClickEvent e)->{
            emcinfo.removeAllComponents();
            emcAddButton.setCaption("hinzufügen");

            emcinfo.addComponents(emcFirstname,emcLastname,emcPhone,emcAddButton);
            emergencyContactWindow.setContent(emcinfo);
            UI.getCurrent().addWindow(emergencyContactWindow);
        });

        addNewDrugButton.addClickListener((Button.ClickEvent e)->{
            DrugServiceImpl drugService = new DrugServiceImpl();
            drugService.createDrug(drugName.getValue());

        });

        addDrugButton.addClickListener((Button.ClickEvent e)->{
            drugInfo.addComponents(drugName,addNewDrugButton);
           drugWindow.setContent(drugInfo);
           UI.getCurrent().addWindow(drugWindow);
        });
        emcAddButton.addClickListener((Button.ClickEvent e)->{
            emergencyContactService.createEmergencyContact(this.emcFirstname.getValue(),this.emcLastname.getValue(),this.emcPhone.getValue(),binder.getBean());
            UI.getCurrent().removeWindow(emergencyContactWindow);
            this.grid.clearSortOrder();
        });

        grid.addItemClickListener(e-> {
            this.emBinder.setBean(e.getItem());
        UI.getCurrent().addWindow(emergencyContactWindow);
        } );
        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(profileLayout);
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
    public void setEmContact(List<EmergencyContact> emContact) {
        //EmergencyContactServiceImpl emergencyContactService = new EmergencyContactServiceImpl();
        //emergencyContactService.createEmergencyContact("Stephan","Dor","031354773",this.binder.getBean());
        this.grid.setItems(emContact);

    }

    @Override
    public void setDrugList(List<Drug> drugList) {
        this.drugGrid.setItems(drugList);
    }


    @Override
	public void enter(ViewChangeEvent event) {
	for (PatientViewListener listener : listeners){
	    listener.viewEntered(VaadinSession.getCurrent().getAttribute("user").toString());
    }
	}
}