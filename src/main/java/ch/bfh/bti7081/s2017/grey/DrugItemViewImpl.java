package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.listener.CheckListener;
import ch.bfh.bti7081.s2017.grey.listener.DeleteListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;

import java.util.LinkedList;
import java.util.List;


/**
 * @author Joel
 */
public class DrugItemViewImpl extends CustomLayout implements DrugItemView {
    private Label name = null;
    private Label amount = null;
    private Label unit = null;
    private Button removeBtn = null;
    private CheckBox finishedCheck = null;
    private List<DeleteListener> deleteListenerList;
    private List<CheckListener> checkListenerList;

    public DrugItemViewImpl() {
        super("drug-layout");
        finishedCheck = new CheckBox();
        addComponent(finishedCheck, "drug-checkbox");
        name = new Label();
        addComponent(name, "drug-name");
        amount = new Label();
        addComponent(amount, "drug-amount");
        unit = new Label();
        addComponent(unit, "drug-unit");
        removeBtn = new Button("X");
        addComponent(removeBtn, "drug-remove");
        deleteListenerList = new LinkedList<>();
        checkListenerList = new LinkedList<>();

        removeBtn.addClickListener(event -> {
            for (DeleteListener listener : deleteListenerList) {
                listener.delete();
            }
        });
        finishedCheck.addValueChangeListener(event -> {
            if (finishedCheck.getValue()) {
                for (CheckListener listener : checkListenerList) {
                    listener.check();
                }
            } else {
                for (CheckListener listener : checkListenerList) {
                    listener.uncheck();
                }
            }
        });
    }

    @Override
    public void setName(String name) {
        this.name.setValue(name);
    }

    @Override
    public void setAmount(Integer amount) {
        this.amount.setValue(amount.toString());
    }

    @Override
    public void setUnit(String unit) {
        this.unit.setValue(unit);
    }

    @Override
    public void addDeleteListener(DeleteListener listener) {
        deleteListenerList.add(listener);
    }

    @Override
    public void addCheckListener(CheckListener listener) {
        checkListenerList.add(listener);
    }
}
