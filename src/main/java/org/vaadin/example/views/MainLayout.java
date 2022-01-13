package org.vaadin.example.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
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

        addToDrawer(new VerticalLayout(listLink, new RouterLink("Live Plots", LivePlots.class)));
    }

    private void createHeader() {
        H1 appName = new H1("Doctor's Interface");
        //appName.addClassName("text-l","m-m");

        //Button logout = new Button("Log out", e-> securityService.logout());

        HorizontalLayout header= new HorizontalLayout(new DrawerToggle(),appName);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(appName);
        header.setWidth("100%");
        //header.addClassName("py-0", "px-m");

        addToNavbar(header);

    }

}
