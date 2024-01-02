package com.example.application.views.admin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("admindashboard/")
public class AdminDashboard extends VerticalLayout implements HasUrlParameter<String> {
    AdminDashboard() {
        add (new AdminNavBar());
        add(new H1("gdfsa"));
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        add(String.format("Hello, %s!", parameter));
    }
}
