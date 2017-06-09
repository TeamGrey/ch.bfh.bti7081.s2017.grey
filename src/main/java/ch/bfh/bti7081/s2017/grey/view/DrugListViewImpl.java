package ch.bfh.bti7081.s2017.grey.view;

import com.vaadin.ui.VerticalLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Joel
 */
public class DrugListViewImpl extends VerticalLayout implements DrugListView {
    private List<DrugItemView> drugItemViews;

    public DrugListViewImpl() {
        drugItemViews = new LinkedList<>();
    }

    public void addDrug(DrugItemView drugItemView) {
        drugItemViews.add(drugItemView);
        addComponent(drugItemView);
    }

    public void removeDrug(DrugItemView drugItemView) {
        drugItemViews.remove(drugItemView);
        removeComponent(drugItemView);
    }

    @Override
    public void clear() {
        drugItemViews.clear();
        removeAllComponents();
    }
}
