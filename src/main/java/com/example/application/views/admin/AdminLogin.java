package com.example.application.views.admin;

import com.example.application.views.main.NavBar;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@PageTitle("AdminLogin")
@Route(value = "adminlogin")
public class AdminLogin extends VerticalLayout {

    private TextField name;
    private PasswordField password;
    private Button loginButton;

    public void validateLogin(String name, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLA", "system", "1234");
            //System.out.println("connection is successful");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt1.executeQuery("select name from admin where password = '" + password + "'");
            String check = "";
            while(rs.next()) {
                check = rs.getString(1);
            }
            if (name.equals(check)) {
                Notification.show("Login successful");
                UI.getCurrent().navigate("admindashboard/" + name);
            }
            else {
                Notification.show("Name and password wrong");
            }
            stmt.close();
            con.close();
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public AdminLogin() {
        add(new NavBar());
        add(new H3("Login to continue"));
        name = new TextField("Name: ");
        password = new PasswordField("Password: ");
        loginButton = new Button("Admin Login");
        loginButton.addClickListener(e -> validateLogin(name.getValue(), password.getValue()));

        loginButton.addClickShortcut(Key.ENTER);

        setMargin(true);
        //setVerticalComponentAlignment(Alignment.END, name, loginButton);

        add(name, password, loginButton);
        setAlignItems(Alignment.CENTER);
    }

}
