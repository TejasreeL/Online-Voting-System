package com.example.application.views.main;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("castedvote")
public class CastedVote extends VerticalLayout {
    CastedVote() {
        add(new H1("Your vote has been casted"));
    }
}
