package com.example.application.views.user;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("userdashboard/")
public class UserDashboard extends VerticalLayout implements HasUrlParameter<String> {
    public UserDashboard() {
        add(new UserNavBar());
        add(new H1("bfsjdk"));
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        add(String.format("Hello, %s!", parameter));
    }
}
