package com.example.application.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@PageTitle("Login")
@Route(value = "login")
public class LoginView extends HorizontalLayout {

    private TextField rollNo;
    private PasswordField password;
    private Button loginButton, adminLogin;

    public void validateLogin(String rollNo, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLA", "system", "1234");
            //System.out.println("connection is successful");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt1.executeQuery("select rollno from login where password = '" + password + "'");
            String check = "";
            while(rs.next()) {
                check = rs.getString(1);
            }
            if (rollNo.equals(check)) {
                Notification.show("Login successful");
                UI.getCurrent().navigate("homepage");
            }
            else {
                Notification.show("Roll number and password wrong");
            }
            stmt.close();
            con.close();
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public LoginView() {
        rollNo = new TextField("Roll number: ");
        password = new PasswordField("Password: ");
        loginButton = new Button("Login");
        loginButton.addClickListener(e -> validateLogin(rollNo.getValue(), password.getValue()));
        loginButton.addClickShortcut(Key.ENTER);
        adminLogin = new Button("Admin Login", Event -> UI.getCurrent().navigate("adminlogin"));
        adminLogin.addClickShortcut(Key.ENTER);
        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, rollNo, loginButton);

        add(rollNo, password, loginButton, adminLogin);
    }

}
