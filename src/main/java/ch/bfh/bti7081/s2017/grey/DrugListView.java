package ch.bfh.bti7081.s2017.grey;

import com.vaadin.ui.Component;

/**
 * @author Joel
 *
 * View for a list of Drug Tasks
 */
public interface DrugListView extends Component {
    void addDrug(DrugItemView drugItemView);

    void removeDrug(DrugItemView drugItemView);

    void clear();
}
