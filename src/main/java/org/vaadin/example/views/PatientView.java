package org.vaadin.example.views;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.vaadin.example.controller.PatientService;
import org.vaadin.example.data.Patient;

import javax.annotation.security.PermitAll;
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
        patientList.addColumn(Patient::getName).setHeader("Patient Name");
        patientList.addColumn(Patient::getPatientId).setHeader("Patient ID");
        patientList.addColumn(Patient::getDate).setHeader("Admission Date");
        patientList.addColumn(Patient::getroom).setHeader("Room Number");
        patientList.addColumn(Patient::getCondition).setHeader("Condition");
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




