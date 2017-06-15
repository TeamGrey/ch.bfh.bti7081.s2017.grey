package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.*;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import java.util.ArrayList;
import java.util.List;

/**
 * This Window acts as a Modal for editing
 * the Selection of Drugs
 * @author Joel
 */
public class PatientDrugWindow {
    private List<Drug> addDrugs = new ArrayList<>();
    private List<Drug> removeDrugs = new ArrayList<>();
    private List<Drug> beforeSelection = new ArrayList<>();
    private List<Drug> afterSelection = new ArrayList<>();
    private List<PatientViewListener> listeners = new ArrayList<>();
    private Grid<Drug> drugSelector = new Grid<>();
    private boolean reactChanges = false;
    private Window drugWindow = new Window("Medikation");

    /**
     * setup GUI and register handlers
     */
    public PatientDrugWindow() {
        drugWindow.setWidth("100%");
        drugWindow.setModal(true);

        FormLayout drugInfo = new FormLayout();
        Button closeDrugWindow = new Button("Übernehmen");

        drugInfo.addComponents(drugSelector, closeDrugWindow);
        drugWindow.setContent(drugInfo);

        closeDrugWindow.addClickListener((event) -> close());

        MultiSelectionModel<Drug> drugMultiSelectionModel = (MultiSelectionModel<Drug>) drugSelector.setSelectionMode(Grid.SelectionMode.MULTI);
        drugSelector.addColumn(Drug::getName).setCaption("Medikament");

        drugMultiSelectionModel.addMultiSelectionListener(event -> {
            if (!reactChanges) return;
            afterSelection.clear();
            afterSelection.addAll(event.getNewSelection());

            // save added drug relations
            addDrugs.clear();
            for (Drug drug : afterSelection) {
                if (!beforeSelection.contains(drug)) {
                    addDrugs.add(drug);
                }
            }

            // delete removed drug relations
            removeDrugs.clear();
            for (Drug drug : beforeSelection) {
                if (!afterSelection.contains(drug)) {
                    removeDrugs.add(drug);
                }
            }
        });
    }

    void addListener(PatientViewListener listener) {
        listeners.add(listener);
    }

    /**
     * initialize grid and show window
     * @param drugOptions List of Drugs to select from
     * @param selected List of already selected Drugs
     */
    void open(List<Drug> drugOptions, List<Drug> selected) {
        beforeSelection.clear();
        beforeSelection.addAll(selected);
        afterSelection.clear();
        afterSelection.addAll(selected);

        drugSelector.setItems(drugOptions);
        for (Drug drug : selected) {
            drugSelector.select(drug);
        }

        UI.getCurrent().addWindow(drugWindow);
        reactChanges = true;
    }

    /**
     * close window and trigger change event
     */
    void close() {
        UI.getCurrent().removeWindow(drugWindow);
        reactChanges = false;

        for (PatientViewListener listener: listeners) {
            listener.onDrugSelectionChange(addDrugs, removeDrugs, afterSelection);
        }
    }
}