package com.example.application.views.main;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
@PageTitle("HomePage")
@Route(" ")
public class HomePage extends VerticalLayout {

    public HomePage() {
        // VerticalLayout

//        Image image = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3x4e9gl7wtAmNsGBU-opPRQsseeWScX6u9XY0LQ9s9EWlauvykRtxHeMNp7IicJ7izRc&usqp=CAU", "My Alt Image");
        //image.setWidth("100%");
        add(new NavBar());
        add(new H2("fdwk"));
    }
}