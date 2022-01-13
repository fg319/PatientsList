package org.vaadin.example.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

@PageTitle("Login")
@Route(value = "")

public class LoginView extends HorizontalLayout{

    private TextField username;
    private String u;
    private PasswordField password;
    private String p;
    private Button Login;


    public LoginView() {
        username = new TextField("username");
        password =new PasswordField("password");
        Login = new Button("Login");

        Login.addClickListener(e -> {
            u = username.getValue();
            p = password.getValue();
            if ((u.equals("doctor1")) && (p.equals("password1"))){
                Notification.show("Logging in "+ u );
                UI.getCurrent().getPage().executeJavaScript("window.open(\"http://localhost:8080/Patients_List\", \"_self\");");
            } else{
                Notification.show("Error. This combination user and password doesn't work" );
            }
        });

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, username,password, Login);

        add(username,password, Login);
    }


}
