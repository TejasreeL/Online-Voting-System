package com.example.application.views.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class NavBar extends AppLayout {

    public NavBar() {
        H1 title = new H1("Online Voting System");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)")
                .set("margin", "0")
                .set("position", "absolute");
        Tabs tabs = getTabs();
        addToNavbar(title, tabs);
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.getStyle().set("margin", "auto");
        tabs.add(createTab("Home", HomePage.class), createTab("Register", RegisterView.class), createTab("Login", LoginView.class), createTab("Results", Result.class), createTab("About Us", AboutUs.class));
        return tabs;
    }

    private Tab createTab(String viewName, Class<? extends Component> targetView) {
        RouterLink link = new RouterLink(viewName, targetView);
        return new Tab(link);
    }
}