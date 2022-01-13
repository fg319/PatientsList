package org.vaadin.example.views;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "Live_Plots", layout= MainLayout.class) //not sure about value part
@PageTitle("Live Plots | Doctor's Interface")

@PermitAll
public class LivePlots extends VerticalLayout{

    public LivePlots(){
        add(createHeader());
    }

    private Component createHeader() {
        H3 pageName = new H3("Live Plot");
        //appName.addClassName("text-l","m-m");

        //Button logout = new Button("Log out", e-> securityService.logout());

        HorizontalLayout header= new HorizontalLayout(pageName);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(pageName);
        header.setWidth("100%");
        //header.addClassName("py-0", "px-m");
    return header;
        //addToNavbar(header);
    }
}
