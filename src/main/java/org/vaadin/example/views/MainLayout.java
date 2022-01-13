package org.vaadin.example.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout(){
        createHeader();
        createDrawer();
    }


    private void createDrawer() {
        RouterLink listLink = new RouterLink("Patient List", PatientView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(listLink));
    }

    private void createHeader() {
        H2 appName = new H2("Doctor's Interface" );
        //appName.addClassName("m-m");

        Button logout = new Button("Log out", e-> UI.getCurrent().getPage().executeJavaScript("window.open(\"http://localhost:8080\", \"_self\");"));

        HorizontalLayout header= new HorizontalLayout(new DrawerToggle(),appName, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(appName);
        header.setWidth("95%");
        //header.addClassName("py-0");

        addToNavbar(header);

    }

}
