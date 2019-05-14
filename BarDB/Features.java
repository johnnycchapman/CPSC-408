package com.company;
/**
 * Created by chapm on 4/27/2019.
 * -- Bar Features --
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Features {
    public static void executeCommand(String command) {
        if (command.equals("display")) {
            display();
        }
    }

    public static void display() {
        int maxLen = 20;
        System.out.println("\n-------------------------------------------------------------------------------------------------------------");
        System.out.println("BarName" + String.format("%1$" + (maxLen - 7) + "s| ", "") + "Has DJ" + String.format("%1$" + (maxLen - 6) +
                "s| ", "") + "Has Food" + String.format("%1$" + (maxLen - 8) + "s| ", "") + "Has Outdoor Area " + String.format("%1$" + 3 +
                "s| ", "") + "Has Beer On Tap" + String.format("%1$" + 5 + "s| ", ""));
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_name,\n" +
                    "       bar_features.has_dj,\n" +
                    "       bar_features.has_food,\n" +
                    "       bar_features.has_outdoor_area,\n" +
                    "       bar_features.has_beer_on_tap\n" +
                    "FROM bar_features\n" +
                    "INNER JOIN bar_info ON bar_info.bar_id = bar_features.bar_id;");

            while(rs.next())
                // print table
                System.out.println(rs.getString(1) + String.format("%1$"+ (maxLen - rs.getString(1).length()) + "s| ", "") +
                        rs.getString(2) + String.format("%1$"+ (maxLen - rs.getString(2).length()) + "s| ", "") +
                        rs.getString(3) + String.format("%1$"+ (maxLen - rs.getString(3).length()) + "s| ", "") +
                        rs.getString(4) + String.format("%1$"+ (maxLen - rs.getString(4).length()) + "s| ", "") +
                        rs.getString(5) + String.format("%1$"+ (maxLen - rs.getString(5).length()) + "s| ", ""));

            rs.close();
            stmt.close();
            con.close();

        }
        catch(Exception e)
        {
            System.out.println("Error: ");
            System.out.println(e);
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------");

    }
}
