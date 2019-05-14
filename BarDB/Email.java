package com.company;
/**
 * Created by chapm on 4/26/2019.
 * -- Bar Email --
 */

import java.sql.*;



public class Email {
    public static void executeCommand(String command) {
        if (command.equals("display")) {
            display();
        }

    }

    public static void display() {
        int maxLen = 25;
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("Bar ID  " + String.format("%1$" + (maxLen - 8) + "s| ", "   ") +
                "Bar Name  " + String.format("%1$" + (maxLen - 10) + "s| ", "   ") + "Email" + String.format("%1$" + (maxLen - 5) + "s| ", "   "));
        System.out.println("--------------------------------------------------------------------------------");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_id, bar_info.bar_name, bar_email.email\n" +
                    "FROM bar_email\n" +
                    "INNER JOIN bar_info ON bar_info.bar_id = bar_email.bar_id");

            while(rs.next())
                System.out.println(rs.getString(1) + String.format("%1$"+ (maxLen - rs.getString(1).length()) + "s| ", "") +
                        rs.getString(2) + String.format("%1$"+ (maxLen - rs.getString(2).length()) + "s| ", "") +
                        rs.getString(3) + String.format("%1$"+ (maxLen - rs.getString(3).length()) + "s| ", ""));


            rs.close();
            stmt.close();
            con.close();

        }
        catch(Exception e)
        {
            System.out.print("Error: ");
            System.out.println(e);
        }

        System.out.println("--------------------------------------------------------------------------------");

    }
}


