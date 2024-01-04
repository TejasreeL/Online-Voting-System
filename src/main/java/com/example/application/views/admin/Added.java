package com.example.application.views.admin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("addedpoll")
public class Added extends VerticalLayout {
    Added() {
        add(new H1("The poll has been created!"));
    }
}
