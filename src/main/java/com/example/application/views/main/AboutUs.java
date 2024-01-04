package com.example.application.views.main;

import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("about")
public class AboutUs extends VerticalLayout {
    public AboutUs() {
        add(new NavBar());
        add(new H4("In the contemporary educational setting, especially in colleges, student engagement in campus activities and decision-making processes is crucial. However, traditional methods of organizing elections and polls face significant challenges, such as low turnout, lack of accessibility, and concerns over vote confidentiality and integrity. The manual process is often cumbersome, time-consuming, and prone to errors, which can lead to disenfranchisement and a sense of disconnection among the student body. Furthermore, ensuring the security and confidentiality of votes in a physical setting poses logistical and ethical challenges. The need for a more efficient, secure, and accessible voting system is evident, to foster a more inclusive and participatory campus environment. Our website aims to address these challenges by introducing an online voting platform specifically tailored for college environments."));
        add(new H4("This is an innovative online voting platform designed to revolutionize student elections and polling in college settings. The platform seeks to address the prevalent issues in traditional voting methods by providing a convenient, secure, and user-friendly online system that encourages greater participation among the student body. The primary objective is to enhance student engagement in campus governance by simplifying the voting process and making it more accessible to all students, regardless of their location or time constraints. Key features of the platform include the ability for administrators to create and manage polls, robust user authentication processes to ensure voter eligibility, and stringent data security measures to maintain vote confidentiality and election integrity. This website not only streamlines the voting process but also reinforces democratic values within the college community, paving the way for a more connected and participatory educational environment. Through this online portal, we aim to empower students, foster a sense of community, and uphold the highest standards of electoral fairness and transparency."));
    }
}
