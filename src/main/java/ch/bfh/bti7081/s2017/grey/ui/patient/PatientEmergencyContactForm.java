package ch.bfh.bti7081.s2017.grey.ui.patient;

import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This Window acts as a Modal for editing
 * the Selection of Habits
 */
public class PatientEmergencyContactForm {
    private List<PatientViewListener> listeners = new ArrayList<>();
    private Window emcWindow = new Window("Gewohnheiten");
    private TextField emcFirstname = new TextField();
    private TextField emcLastname = new TextField();
    private TextField emcPhone = new TextField();
    private long emcId = -1;
    private Button emcSaveButton;

    /**
     * Build Gui for Contact form
     */
    public PatientEmergencyContactForm() {
        emcWindow.setWidth("100%");
        emcWindow.setModal(true);

        emcFirstname.setCaption("Vorname");
        emcLastname.setCaption("Nachname");
        emcPhone.setCaption("Telefon");
        emcSaveButton = new Button();
        emcSaveButton.setCaption("Speichern");

        FormLayout emcInfo = new FormLayout();
        emcInfo.addComponents(emcFirstname, emcLastname, emcPhone, emcSaveButton);
        emcWindow.setContent(emcInfo);

        emcSaveButton.addClickListener((event) -> close());
    }

    void addListener(PatientViewListener listener) {
        listeners.add(listener);
    }

    /**
     * open an empty form
     */
    void open() {
        emcFirstname.setValue("");
        emcLastname.setValue("");
        emcPhone.setValue("");
        emcId = -1;
        UI.getCurrent().addWindow(emcWindow);
    }

    /**
     * open form filled with data
     * @param firstName firstname of contact
     * @param lastName lastname of contact
     * @param phone phonenumber
     * @param id conact id
     */
    public void open(String firstName, String lastName, String phone, long id) {
        emcFirstname.setValue(firstName);
        emcLastname.setValue(lastName);
        emcPhone.setValue(phone);
        emcId = id;
        UI.getCurrent().addWindow(emcWindow);
    }

    /**
     * close window and trigger change event
     */
    void close() {
        UI.getCurrent().removeWindow(emcWindow);
        for (PatientViewListener listener : listeners) {
            listener.onEMCAddition(this.emcFirstname.getValue(), this.emcLastname.getValue(), this.emcPhone.getValue(), emcId);
        }
    }
}