package ch.bfh.bti7081.s2017.grey.ui.drug;

import ch.bfh.bti7081.s2017.grey.listener.DrugTaskViewListener;
import com.vaadin.ui.Component;

/**
 * @author Joel
 *         <p>
 *         View for a single Drug Task
 */
public interface DrugTaskView extends Component {
    void setFinished(boolean finished);

    void setName(String name);

    void setUnit(String unit);

    void setAmount(Integer amount);

    void addDrugTaskViewListener(DrugTaskViewListener listener);
}
