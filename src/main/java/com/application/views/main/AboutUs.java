package com.application.views.main;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("about")
public class AboutUs extends VerticalLayout {
    public AboutUs() {
        add(new NavBar());
        add(new H1("rgefsdfr5ynwht"));
    }
}
