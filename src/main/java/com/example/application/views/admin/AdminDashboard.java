package com.example.application.views.admin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("admindashboard/")
public class AdminDashboard extends VerticalLayout implements HasUrlParameter<String> {
    AdminDashboard() {
        add (new AdminNavBar());
        add(new H3("You have successfully logged in as an admin!"));
        add(new H3("You can create polls and manage them as you wish!"));
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        add(String.format("Hello, %s!", parameter));
    }
}
