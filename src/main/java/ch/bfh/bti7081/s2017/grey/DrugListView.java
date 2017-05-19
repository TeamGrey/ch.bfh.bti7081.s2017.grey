package ch.bfh.bti7081.s2017.grey;

import com.vaadin.ui.VerticalLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Joel
 */
public class DrugListView extends VerticalLayout {
    private List<DrugView> drugs;

    public DrugListView() {
        super();
        drugs = new LinkedList<DrugView>();
    }

    public DrugView addDrug(String name) {
        DrugView drug = new DrugView();
        drug.setName(name);
        drugs.add(drug);
        addComponent(drug);
        return drug;
    }
}
