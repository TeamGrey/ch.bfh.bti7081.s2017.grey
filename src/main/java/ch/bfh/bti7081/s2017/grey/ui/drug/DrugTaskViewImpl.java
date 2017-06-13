package ch.bfh.bti7081.s2017.grey.ui.drug;

import ch.bfh.bti7081.s2017.grey.listener.DrugTaskViewListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Joel
 */
public class DrugTaskViewImpl extends CustomLayout implements DrugTaskView {
    private List<DrugTaskViewListener> listeners;
    private Label description;
    private String name;
    private Integer amount;
    private String unit;
    private Button removeBtn = null;
    private CheckBox finishedCheck = null;

    public DrugTaskViewImpl() {
        super("drug-layout");
        listeners = new ArrayList<>();

        finishedCheck = new CheckBox();
        addComponent(finishedCheck, "drug-checkbox");
        description = new Label();
        addComponent(description, "drug-description");
        removeBtn = new Button("X");
        addComponent(removeBtn, "drug-remove");

        removeBtn.addClickListener(event -> onRemove());
        finishedCheck.addValueChangeListener(event -> onCechChange());
    }

    private void onRemove() {
        for (DrugTaskViewListener listener : listeners) {
            listener.onRemoveTask();
        }
    }

    private void onCechChange() {
        for (DrugTaskViewListener listener : listeners) {
            if (finishedCheck.getValue()) {
                listener.onFinishTask();
            } else {
                listener.onUnfinishTask();
            }
        }
    }

    private void setDescription() {
        description.setValue(name + " " + amount + " " + unit);
    }

    @Override
    public void setFinished(boolean finished) {
        finishedCheck.setValue(finished);
    }

    @Override
    public void setName(String name) {
        this.name = name;
        this.setDescription();
    }

    @Override
    public void setAmount(Integer amount) {
        this.amount = amount;
        this.setDescription();
    }

    @Override
    public void addDrugTaskViewListener(DrugTaskViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void setUnit(String unit) {
        this.unit = unit;
        this.setDescription();
    }
}
