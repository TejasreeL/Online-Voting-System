package com.example.application.views.main;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;

import java.sql.*;

@Route("result")
public class Result extends VerticalLayout {
  String name;
  int cnt1, cnt2, cnt3, cnt4, cnt5;

  public Result() {
    String query = "select * from polls";
    add(new NavBar());
    add(new H3("Select a poll to view the result of the voting!"));
    ResultSet rs;
    try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLA", "system", "1234");
        Statement stmt = con.createStatement();) {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      rs = stmt.executeQuery(query);
      MenuBar menuBar = new MenuBar();
      MenuItem item = menuBar.addItem("Select poll");
      SubMenu subMenu = item.getSubMenu();
      while (rs.next()) {
        String pollname = rs.getString("Pollname");
        subMenu.addItem(pollname, event -> {
          showResult(pollname);
        });
      }
      add(menuBar);
    } catch (Exception se) {
      System.out.println(se);
    }
    setAlignItems(Alignment.CENTER);
  }

  void showResult(String pollname) {
    try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLA", "system", "1234");
      Statement stmt = con.createStatement();) {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    String query = "select * from polls where pollname = '" + pollname + "'";
    ResultSet rs = stmt.executeQuery(query);
    if(rs.next()) {
      cnt1 = rs.getInt("cnt1");
      cnt2 = rs.getInt("cnt2");
      cnt3 = rs.getInt("cnt3");
      cnt4 = rs.getInt("cnt4");
      cnt5 = rs.getInt("cnt5");
      if (cnt1 > cnt2 && cnt1 > cnt3 && cnt1 > cnt4 && cnt1 > cnt5)
        add(new H1("The Winner is " + rs.getString("c1") + " - " + rs.getString("r1") + " in " + pollname));
      else if (cnt2 > cnt1 && cnt2 > cnt3 && cnt2 > cnt4 && cnt2 > cnt5)
        add(new H1("The Winner is " + rs.getString("c2") + " - " + rs.getString("r2") + " in " + pollname));
      else if (cnt3 > cnt2 && cnt3 > cnt1 && cnt3 > cnt4 && cnt3 > cnt5)
        add(new H1("The Winner is " + rs.getString("c3") + " - " + rs.getString("r3") + " in " + pollname));
      else if (cnt4 > cnt2 && cnt4 > cnt3 && cnt4 > cnt1 && cnt4 > cnt5)
        add(new H1("The Winner is " + rs.getString("c4") + " - " + rs.getString("r4") + " in " + pollname));
      else if (cnt5 > cnt2 && cnt5 > cnt3 && cnt5 > cnt4 && cnt5 > cnt1)
        add(new H1("The Winner is " + rs.getString("c5") + " - " + rs.getString("r5") + " in " + pollname));
      else 
        add(new H1("There is no winner yet"));
    }
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    } 
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}