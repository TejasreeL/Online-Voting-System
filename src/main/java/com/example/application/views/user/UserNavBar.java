package com.example.application.views.user;

import com.example.application.service.AuthService;
import com.example.application.views.main.HomePage;
import com.example.application.views.main.Result;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class UserNavBar extends AppLayout {

    public UserNavBar() {
        H1 title = new H1("Online Voting System");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)")
                .set("margin", "0")
                .set("position", "absolute");
        Tabs tabs = getTabs();
        addToNavbar(title, tabs);
        Button logoutButton = new Button("Logout", e -> {
            AuthService.logout();
            Notification.show("Logout successful");
            UI.getCurrent().navigate("login");
        });
        addToNavbar(logoutButton);
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.getStyle().set("margin", "auto");
        tabs.add(createTab("Home", HomePage.class), createTab("Results", Result.class));
        return tabs;
    }

    private Tab createTab(String viewName, Class<? extends Component> targetView) {
        RouterLink link = new RouterLink(viewName, targetView);
        return new Tab(link);
    }
}