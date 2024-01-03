package com.example.application.views.user;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;

import java.sql.*;

@Route("userdashboard/")
public class UserDashboard extends VerticalLayout implements HasUrlParameter<String> {
    String name;
    public UserDashboard() {
        String query = "select * from polls";
        ResultSet rs;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLA", "system", "1234");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            add(new UserNavBar());
            MenuBar menuBar = new MenuBar();
            MenuItem item = menuBar.addItem("Select poll");
            SubMenu subMenu = item.getSubMenu();
            while(rs.next()) {
                String pollname = rs.getString("Pollname");
                subMenu.addItem(pollname, event -> {
                    add(new Vote(pollname, name));
                        }
                );
            }
            add(menuBar);
            stmt.close();
            con.close();
        } catch (Exception se) {
            System.out.println(se);
        }

    }

    @Override
    public void setParameter(BeforeEvent event, String name) {
        this.name = name;
    }
}
