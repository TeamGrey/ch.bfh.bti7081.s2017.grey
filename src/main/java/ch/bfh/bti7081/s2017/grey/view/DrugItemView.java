package ch.bfh.bti7081.s2017.grey.view;

import ch.bfh.bti7081.s2017.grey.listener.CheckListener;
import ch.bfh.bti7081.s2017.grey.listener.DeleteListener;
import com.vaadin.ui.Component;

/**
 * @author Joel
 *         <p>
 *         View for a single Drug Task
 */
public interface DrugItemView extends Component {
    void setName(String name);

    void setUnit(String unit);

    void setAmount(Integer amount);

    void addDeleteListener(DeleteListener listener);

    void addCheckListener(CheckListener listener);
}
