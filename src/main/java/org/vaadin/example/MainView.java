package org.vaadin.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.avatar.AvatarGroup;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.controller.PatientService;
import org.vaadin.example.data.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Route
@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")

public class MainView extends VerticalLayout {

    Grid<Patient> patientList = new Grid(Patient.class);
    TextField searchInput = new TextField();
    PatientService patientService;
    Patient patient;
    ArrayList<String> givenName = new ArrayList<String>();



    public MainView(PatientService patientService) throws Exception {

        setSizeFull();
        ConfigureGrid();
        //patientList.addColumn(patient->patient.GetFName()).setHeader("FamilyName");
        //patientList.addColumn(patient -> patient.GetDate()).setHeader("AdmissionDate");}

        add(getToolbar());
    }

    private void ConfigureGrid() {
        patientList.setSizeFull();
        patientList.setColumns("givenname", "familyname", "admissiondate");
        //patientList.addColumn(patient -> patient.getgName()).setHeader("givenname");
        //patientList.addColumn(patient -> patient.getfName()).setHeader("Company");
        patientList.getColumns().forEach(col -> col.setAutoWidth(true));

    }


    private HorizontalLayout getToolbar() {
        searchInput.setPlaceholder("search patient");
        searchInput.setClearButtonVisible(true);

        HorizontalLayout toolbar = new HorizontalLayout(searchInput);
        toolbar.addClassName("toolbar");
        return toolbar;

    }



}




