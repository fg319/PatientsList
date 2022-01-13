package org.vaadin.example.views;

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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.example.controller.PatientService;
import org.vaadin.example.data.Patient;

import javax.annotation.security.PermitAll;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Route(value="", layout=MainLayout.class)
@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application."
        )
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@PermitAll
@PageTitle("Patients List")
public class PatientView extends VerticalLayout {

    Grid<Patient> patientList = new Grid(Patient.class, false);
    TextField searchInput = new TextField();
    PatientService patientService;
    List<Patient> patients;



    public PatientView(PatientService patientService) throws Exception {
        this.patientService = patientService;
        patients = patientService.GetList();
        setSizeFull();
        patientList.setItems(patients);

        ConfigureGrid();
        add(getToolbar(),patientList);
        ///display();

    }


    /*private void display() throws Exception {
        patientList.setItems(patientService.GetList());
    }*/

    private void ConfigureGrid() {
        patientList.setSizeFull();
        patientList.addColumn(Patient::getId).setHeader("Patient ID");
        patientList.addColumn(Patient::getgName).setHeader("Given Name");
        patientList.addColumn(Patient::getfName).setHeader("Family Name");
        patientList.addColumn(Patient::getDate).setHeader("Admission Date");
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




