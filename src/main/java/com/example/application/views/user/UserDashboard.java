package com.example.application.views.user;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("userdashboard")
public class UserDashboard extends VerticalLayout {
    public UserDashboard() {
        add(new UserNavBar());
        add(new H1("bfsjdk"));
    }
}
