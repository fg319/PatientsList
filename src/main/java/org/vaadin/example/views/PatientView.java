package org.vaadin.example.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.vaadin.example.patients.PatientService;
import org.vaadin.example.patients.PatientInfo;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Optional;


@Route(value="Patients_List", layout=MainLayout.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@PermitAll
@PageTitle("Patients List")
public class PatientView extends VerticalLayout {

    Grid<PatientInfo> patientList = new Grid(PatientInfo.class, false);  //vaadin component that allows to create table of patients
    TextField searchInput = new TextField();
    PatientService patientService;
    List<PatientInfo> patients;



    public PatientView(PatientService patientService) throws Exception {
        this.patientService = patientService;
        patients = patientService.GetList(); //List of patients obtained from database

        setSizeFull();
        patientList.setItems(patients);
        ConfigureGrid();
        add(getToolbar(),patientList);
        getLivePlot();
        //searchPatient();

    }

    //When patient is selected we get redirected to Live Plot page
    private void getLivePlot() {  //From https://vaadin.com/docs/v14/ds/components/grid
        patientList.addSelectionListener(selectionEvent -> {
            Optional<PatientInfo> optionalPatient = selectionEvent.getFirstSelectedItem();
            if(optionalPatient.isPresent()){
                UI.getCurrent().getPage().executeJavaScript("window.open(\"http://localhost:8080/Live_Plots\", \"_self\");");
            }

        });
    }

    //Uses search function and displays the patient being searched
    private void searchPatient() throws Exception {
        patientList.setItems(patientService.search(searchInput.getValue()));
    }


    private void ConfigureGrid() {
        patientList.setSizeFull();
        patientList.addColumn(PatientInfo::getName).setHeader("Patient Name");
        patientList.addColumn(PatientInfo::getPatientId).setHeader("Patient ID");
        patientList.addColumn(PatientInfo::getDate).setHeader("Admission Date");
        patientList.addColumn(PatientInfo::getroom).setHeader("Room Number");
        patientList.addColumn(PatientInfo::getCondition).setHeader("Condition");
        patientList.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {  //Vaadin tutorial
        searchInput.setPlaceholder("search patient");
        searchInput.setClearButtonVisible(true);
        searchInput.setValueChangeMode(ValueChangeMode.LAZY);
        searchInput.addValueChangeListener(e -> {
            try {
                searchPatient();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HorizontalLayout toolbar = new HorizontalLayout(searchInput);
        toolbar.addClassName("toolbar");
        return toolbar;

    }

}




