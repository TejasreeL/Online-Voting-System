package com.example.application.views.admin;

import com.example.application.views.main.NavBar;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
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

@Route("admindashboard/addpoll")
public class AddPoll extends VerticalLayout {
    private TextField pollName;
    private H3 title;
    private TextField c1, c2, c3, c4, c5;
    private TextField r1, r2, r3, r4, r5;

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
        Notification.show("Poll Added Successfully");
    }

    public void createTable(String query) {
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
    }

    AddPoll() {
        add(new AdminNavBar());
        title = new H3("Add a poll");
        pollName = new TextField("Poll name");
        //number = new TextField("Enter number of candidates");
        add(title, pollName);
        class W1 extends HorizontalLayout {
            W1() {
                c1 = new TextField("Candidate 1 name");
                r1 = new TextField("Roll number of candidate 1");
                add(c1, r1);
                c2 = new TextField("Candidate 2 name");
                r2 = new TextField("Roll number of candidate 2");
                add(c2, r2);
            }
        }
        class W3 extends HorizontalLayout {
            W3() {
                c3 = new TextField("Candidate 3 name");
                r3 = new TextField("Roll number of candidate 3");
                add(c3, r3);
                c4 = new TextField("Candidate 4 name");
                r4 = new TextField("Roll number of candidate 4");
                add(c4, r4);
            }
        }
        class W5 extends HorizontalLayout {
            W5() {
                c5 = new TextField("Candidate 5 name");
                r5 = new TextField("Roll number of candidate 5");
                add(c5, r5);
            }
        }

        setAlignItems(Alignment.CENTER);
        add(new W1(), new W3(), new W5());

        submitButton = new Button("Add Poll", Event -> {
            insert("insert into polls values('" + pollName.getValue() + "', '" + c1.getValue() + "', '" + r1.getValue() + "', 0, '" + c2.getValue() + "', '" + r2.getValue() + "', 0, '" + c3.getValue() + "', '" + r3.getValue() + "', 0, '" + c4.getValue() + "', '" + r4.getValue() + "', 0, '" + c5.getValue() + "', '" + r5.getValue() + "', 0)");
            createTable("create table " + pollName.getValue() + " (rollno varchar2(10), constraint fk_rollno foreign key(rollno) references login(rollno))");
        });
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(submitButton);
    }
}
