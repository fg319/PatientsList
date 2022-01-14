package org.vaadin.example.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.vaadin.example.doctors.Doctors;

import java.sql.SQLException;

@PageTitle("Login")
@Route(value = "", layout = MainLayoutLogin.class)
public class LoginView extends HorizontalLayout {

    private TextField Username;
    private String u;
    private PasswordField Password;
    private String p;
    private Button Login;
    private Boolean login_verification;
    private Doctors doc; //object from Doctors class

    public LoginView() {

        doc = new Doctors();
        login_verification = false; //initializing boolean as false, wait for user to input correct username & password
        Username = new TextField("Username");
        Password =new PasswordField("Password");
        Login = new Button("Login");

        Login.addClickListener(e -> {

            u = Username.getValue();
            p = Password.getValue();
            doc.setDocUser(u,p); //setting input username and password strings

            try {
                doc.LoginVerification();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            login_verification= doc.getLoginVerification(); //verifying that username & password are correct

            if (login_verification == true) {
                Notification.show("Logging in "+ u );
                UI.getCurrent().getPage().executeJavaScript("window.open(\"http://localhost:8080/Patients_List\", \"_self\");");
            }
            else if (login_verification == false) //login failed try again
            {
                Notification.show("Incorrect Username or Password, Please Try Again" );

            }


        });
        //Formatting
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setMargin(true);
        add(createFormLayout());
    }
    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(Username, Password, Login);
        return formLayout;
    }
}