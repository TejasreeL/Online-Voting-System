package com.example.application.views.admin;

import com.example.application.views.main.NavBar;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.stream.Stream;


@Route(value = "adminregister")
public class AdminRegister extends VerticalLayout {

    private H3 title;

    private TextField name;

    private PasswordField password;
    private PasswordField passwordConfirm;


    private Span errorMessageField;

    private Button submitButton;


    public void insert(String query) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLA", "system", "1234");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            con.close();
        } catch (Exception se) {
            System.out.println(se);
        }
        Notification.show("Register successful");
        UI.getCurrent().navigate("adminlogin");
    }

    public AdminRegister() {
        add(new NavBar());
        title = new H3("Signup form");
        name = new TextField("Name");
        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");
        setRequiredIndicatorVisible(name, password, passwordConfirm);
        errorMessageField = new Span();
        submitButton = new Button("Register", Event -> insert("insert into admin values('" + name.getValue() + "', '" + password.getValue() + "')"));
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(title, name, password, passwordConfirm, errorMessageField, submitButton);
        setAlignItems(Alignment.CENTER);
    }

    public PasswordField getPasswordField() { return password; }

    public PasswordField getPasswordConfirmField() { return passwordConfirm; }

    public Span getErrorMessageField() { return errorMessageField; }

    public Button getSubmitButton() { return submitButton; }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }


}