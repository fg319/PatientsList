package org.vaadin.example.views;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "Live Plots", layout= MainLayout.class) //not sure about value part
@PageTitle("Live Plots | Doctor's Interface")
@PermitAll
public class LivePlots extends VerticalLayout {

}
