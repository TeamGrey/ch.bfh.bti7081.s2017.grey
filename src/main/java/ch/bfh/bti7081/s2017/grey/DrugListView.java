package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import com.vaadin.ui.VerticalLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Joel
 */
public class DrugListView extends VerticalLayout {
    private List<DrugView> drugViews;
    private List<Drug> drugs;

    public DrugListView() {
        super();
        drugViews = new LinkedList<DrugView>();
        drugs = new LinkedList<Drug>();
    }

    public DrugView addDrug(Drug drug) {
        DrugView drugView = new DrugView();
        drugView.setName(drug.getName());
        drugViews.add(drugView);
        drugs.add(drug);
        addComponent(drugView);
        return drugView;
    }
}
