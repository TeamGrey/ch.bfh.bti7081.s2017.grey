package ch.bfh.bti7081.s2017.grey.view;

import ch.bfh.bti7081.s2017.grey.listener.DrugTaskListViewListener;
import com.vaadin.ui.Component;

/**
 * @author Joel
 *
 * View for a list of Drug Tasks
 */
public interface DrugTaskListView extends Component {
    void addDrugTaskListViewListener(DrugTaskListViewListener listener);

    void addDrug(DrugTaskView drugTaskView);

    void removeDrug(DrugTaskView drugTaskView);

    void clear();
}
