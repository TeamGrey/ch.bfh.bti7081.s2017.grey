package ch.bfh.bti7081.s2017.grey;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Joel
 */
public class DrugScreenPresenter extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "DrugScreen";

    private DrugListView drugs = null;
    private Design design = null;

    public DrugScreenPresenter() {
        drugs = new DrugListView();

        design = new Design();
        design.insertContent(drugs);

        // TODO get drug list from DB
        for (int i = 0; i < 4; i++) {
            drugs.addDrug("Drug Nr:" + i);
        }

        addComponent(design);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        MyUI.loggedIn();
    }
}
