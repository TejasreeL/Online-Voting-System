package com.example.application.views.main;

import com.example.application.service.AuthService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@PageTitle("Login")
@Route(value = "login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private TextField rollNo;
    private PasswordField password;
    private Button loginButton, adminLogin;

    public void validateLogin(String rollNo, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLA", "system", "1234");
            System.out.println("connection is successful");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from login where password = '" + password + "'");
            String check = "";
            String name = "";
            while(rs.next()) {
                check = rs.getString("rollno");
                name = rs.getString("name");
            }
            if (rollNo.equals(check)) {
                Notification.show("Login successful");
                AuthService.authenticate(rollNo); // Authenticating the user
                UI.getCurrent().navigate("userdashboard/" + name);
            }
//            else if (rollNo.equals(null) || password.equals(null)) {
//                Notification.show("Enter roll number and password");
//            }
            else {
                Notification.show("Roll number and password wrong");
            }
            stmt.close();
            con.close();
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // If the user is already authenticated, redirect to userdashboard
        if (AuthService.isAuthenticated()) {
            event.forwardTo("userdashboard");
        }
    }

    public LoginView() {
        add(new NavBar());
        rollNo = new TextField("Roll number: ");
        password = new PasswordField("Password: ");
        rollNo.setRequired(true);
        password.setRequired(true);
        loginButton = new Button("Login");
        loginButton.addClickListener(e -> validateLogin(rollNo.getValue(), password.getValue()));
        loginButton.addClickShortcut(Key.ENTER);
        adminLogin = new Button("Admin Login", Event -> UI.getCurrent().navigate("adminlogin"));
        adminLogin.addClickShortcut(Key.ENTER);
        setMargin(true);
        setAlignItems(Alignment.CENTER);
        add(rollNo, password, loginButton, adminLogin);
    }
}
