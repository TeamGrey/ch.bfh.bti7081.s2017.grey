package ch.bfh.bti7081.s2017.grey.view;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.listener.DrugTaskFormViewListener;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Joel
 */
public class DrugTaskFormViewImpl implements DrugTaskFormView {

    private Window window;
    private Long taskId = null;
    private TextField taskName;
    private NativeSelect<Drug> drugList;
    private TextField amount;
    private NativeSelect<String> unit;
    private Button createTask;
    private List<DrugTaskFormViewListener> listeners;

    public DrugTaskFormViewImpl() {
        listeners = new LinkedList<>();

        window = new Window("Neuer Task");
        window.setWidth(300.0f, Sizeable.Unit.PIXELS);
        window.setModal(true);
        FormLayout content = new FormLayout();
        content.setMargin(true);

        taskName = new TextField();
        taskName.setPlaceholder("Taskbeschreibung");
        content.addComponent(taskName);

        drugList = new NativeSelect<>();
        drugList.setItemCaptionGenerator(Drug::getName);
        content.addComponent(drugList);

        amount = new TextField();
        amount.setPlaceholder("Menge");
        content.addComponent(amount);

        unit = new NativeSelect<>();
        unit.setItems("Stk.", "ml", "mg", "Löffel");
        content.addComponent(unit);

        createTask = new Button("DrugTask erstellen");
        content.addComponent(createTask);

        createTask.addClickListener(event -> {
            if (getDrug() == null) {
                return;
            }
            if (getAmount() <= 0) {
                return;
            }
            window.close();
            onClose();
        });

        window.setContent(content);

        UI.getCurrent().addWindow(window);
    }

    private void onClose() {
        for (DrugTaskFormViewListener listener : listeners) {
            listener.onFormClosed();
        }
    }

    public void setDrugList(Collection<Drug> drugs) {
        drugList.setItems(drugs);
    }

    @Override
    public void open(Long taskId, String taskName, Drug drug, Integer amount, String unit) {
        this.taskId = taskId;
        this.taskName.setValue(taskName);
        this.drugList.setSelectedItem(drug);
        this.amount.setValue(amount.toString());
        this.unit.setSelectedItem(unit);
    }

    @Override
    public void open() {
        open(null, "", null, 0, "");
    }

    @Override
    public void addDrugTaskFormViewListener(DrugTaskFormViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public Long getTaskId() {
        return taskId;
    }

    @Override
    public String getTaskName() {
        return taskName.getValue();
    }

    @Override
    public Drug getDrug() {
        return drugList.getSelectedItem().get();
    }

    @Override
    public Integer getAmount() {
        return Integer.parseInt(amount.getValue());
    }

    @Override
    public String getAmountUnit() {
        return unit.getSelectedItem().get();
    }
}
