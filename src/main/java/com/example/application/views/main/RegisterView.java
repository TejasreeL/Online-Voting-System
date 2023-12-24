package com.example.application.views.main;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.stream.Stream;


@Route(value = "register")
public class RegisterView extends FormLayout {

    private H3 title;

    private TextField name;
    private TextField rollNo;

    private PasswordField password;
    private PasswordField passwordConfirm;


    private Span errorMessageField;

    private Button submitButton, submitButton1;


    public void insert(String query) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLA", "system", "1234");
            //System.out.println("connection is successful");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt1.executeQuery(query);
            stmt.close();
            con.close();
        } catch (Exception se) {
            System.out.println(se);
        }
        Notification.show("Register successful");
        UI.getCurrent().navigate("login");
    }

    public RegisterView() {
        title = new H3("Signup form");
        rollNo = new TextField("Roll number");
        name = new TextField("Name");
        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");
        setRequiredIndicatorVisible(rollNo, name, password, passwordConfirm);
        errorMessageField = new Span();
        submitButton = new Button("Join the community", Event -> insert("insert into login values('" + rollNo.getValue() +"', '" + name.getValue() + "', '" + password.getValue() + "')"));
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(title, name, rollNo, password, passwordConfirm, errorMessageField, submitButton);
        //String query = "insert into login values('" + rollNo.getValue() +"', '" + name.getValue() + "', '" + password.getValue() + "')";

        setMaxWidth("500px");

        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

        setColspan(title, 2);
        setColspan(errorMessageField, 2);
        setColspan(submitButton, 2);

    }

    public PasswordField getPasswordField() { return password; }

    public PasswordField getPasswordConfirmField() { return passwordConfirm; }

    public Span getErrorMessageField() { return errorMessageField; }

    public Button getSubmitButton() { return submitButton; }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }

}