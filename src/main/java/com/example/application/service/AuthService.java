package com.example.application.service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;

public class AuthService {
    private static final String SESSION_ATTR_USER = "authenticatedUser";

    public static boolean isAuthenticated() {
        return VaadinSession.getCurrent().getSession().getAttribute(SESSION_ATTR_USER) != null;
    }

    public static void authenticate(String username) {
        VaadinSession.getCurrent().getSession().setAttribute(SESSION_ATTR_USER, username);
    }

    public static void logout() {
        VaadinSession.getCurrent().getSession().removeAttribute(SESSION_ATTR_USER);
        UI.getCurrent().navigate("login");
    }
}
