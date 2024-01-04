package com.example.application.views.user;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Vote extends VerticalLayout {
    private Button voteButton;
    private String name, pollname;

    Vote(String pollname, String name) {
        this.name = name;
        this.pollname = pollname;
        String query = "select * from polls where Pollname = '" + pollname + "'";
        ResultSet rs, rsvalidate;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLA", "system", "1234");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            rs = stmt.executeQuery(query);
            rsvalidate = stmt1.executeQuery("select * from " + pollname + " where rollno = '" + name + "'");
            if (!rsvalidate.next()) {
                if (rs.next()) {
                    voteButton = new Button("Vote for " + rs.getString("C1"));
                    voteButton.addClickListener(e -> voteCandidate("CNT1", pollname));
                    add(voteButton);
                    voteButton = new Button("Vote for " + rs.getString("C2"));
                    voteButton.addClickListener(e -> voteCandidate("CNT2", pollname));
                    add(voteButton);
                    voteButton = new Button("Vote for " + rs.getString("C3"));
                    voteButton.addClickListener(e -> voteCandidate("CNT3", pollname));
                    add(voteButton);
                    if (rs.getString("C4") != null) {
                        voteButton = new Button("Vote for " + rs.getString("C4"));
                        voteButton.addClickListener(e -> voteCandidate("CNT4", pollname));
                        add(voteButton);
                    }
                    if (rs.getString("C5") != null) {
                        voteButton = new Button("Vote for " + rs.getString("C5"));
                        voteButton.addClickListener(e -> voteCandidate("CNT5", pollname));
                        add(voteButton);
                    }
                }
            }
            else {
                Notification.show("You have already voted on this poll!");
            }
            stmt.close();
            stmt1.close();
            con.close();
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public void voteCandidate(String candidateCount, String pollname) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLA", "system", "1234");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("update polls set " + candidateCount + " = " + candidateCount + " + 1 where pollname = '" + pollname + "'");
            System.out.println("insert into " + pollname + " values('" + name + "'");
            stmt.executeUpdate("insert into " + pollname + " values('" + name + "')");
            UI.getCurrent().navigate("castedvote");
            stmt.close();
            con.close();
        } catch (Exception se) {
            System.out.println(se);
        }
    }
}
