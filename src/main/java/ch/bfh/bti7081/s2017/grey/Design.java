package ch.bfh.bti7081.s2017.grey;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class Design extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Design";
	private CustomLayout mainlayout = new CustomLayout("mainlayout");
	private Component lHeader = new Label("Header");
	private Component l2Header = new Label("Header2");
	private Component lFooter = new Label("Footer");

	public Design(){
		mainlayout.setSizeFull();
		lHeader.setSizeFull();
		lFooter.setSizeFull();
	}
	
	public Design insertContent(Component content){
		HorizontalLayout test = new HorizontalLayout();
		test.addComponents(lHeader, l2Header);
		
		mainlayout.addComponent(test, "top");
		mainlayout.addComponent(content, "center");
		mainlayout.addComponent(lFooter, "bottom");
		
		this.addComponent(mainlayout);
		return this;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}