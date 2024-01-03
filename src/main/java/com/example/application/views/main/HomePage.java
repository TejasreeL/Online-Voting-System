package com.example.application.views.main;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("homepage")
@Route(" ")
public class HomePage extends VerticalLayout {

    public HomePage() {
        // VerticalLayout

//        Image image = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3x4e9gl7wtAmNsGBU-opPRQsseeWScX6u9XY0LQ9s9EWlauvykRtxHeMNp7IicJ7izRc&usqp=CAU", "My Alt Image");
        //image.setWidth("100%");
        add(new NavBar());
        add(new H3("Hello and welcome to VNR Psephologists! Here, you can securely and easily participate in various online polls. Our platform is designed to be user-friendly, ensuring a smooth experience for everyone. Join the community of those who trust us for transparent and convenient online voting. Your security and satisfaction are our top priorities, making VNR Psephologists the perfect place for your online engagement."));
        Anchor link = new Anchor("http://localhost:8080/register", "registering here");
        Anchor loginlink = new Anchor("http://localhost:8080/login", "Login");
        class Hor extends HorizontalLayout {
            Hor() {
                add(new H3("Don't have an account? Create an account by"));
                add(new H3(link));
            }
        }
        class Hor1 extends HorizontalLayout {
            Hor1() {
                add(new H3(loginlink));
                add(new H3("and start voting on polls which are left unattended!"));
            }
        }
        add(new Hor1());
        add(new Hor());
        add(new H4("Note: for the time being, this is open only for VNR VJIET students"));
    }
}