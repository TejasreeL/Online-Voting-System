package com.example.application.views.main;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.stream.Stream;


@Route(value = "register")
public class RegisterView extends VerticalLayout {

    private H3 title;

    private TextField name;
    private TextField rollNo;

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
        UI.getCurrent().navigate("login");
    }

    public RegisterView() {
        add(new NavBar());
        title = new H3("Registration form");
        class Ho extends HorizontalLayout {
            Ho() {
                rollNo = new TextField("Roll number");
                name = new TextField("Name");
                add(rollNo, name);
                setRequiredIndicatorVisible(rollNo, name);
            }
        }
        class Ho1 extends HorizontalLayout {
            Ho1() {
                password = new PasswordField("Password");
                passwordConfirm = new PasswordField("Confirm password");
                add(password, passwordConfirm);
                setRequiredIndicatorVisible(password, passwordConfirm);
            }
        }
        errorMessageField = new Span();
        submitButton = new Button("Register", Event -> insert("insert into login values('" + rollNo.getValue() +"', '" + name.getValue() + "', '" + password.getValue() + "')"));
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(title, new Ho(), new Ho1(), errorMessageField, submitButton);
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