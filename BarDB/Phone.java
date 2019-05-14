package com.company;

/**
 * Created by chapm on 4/29/2019.
 * -- Bar Phone --
 */

import java.sql.*;
import java.util.*;



class Phone
{
    public static void executeCommand(String command) {
        if (command.equals("display")) {
            display();
        }
        else if (command.equals("update")) {
            update();
        }
    }

    public static void display() {
        int maxLen = 20;
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("Bar ID  " + String.format("%1$" + (maxLen - 8) + "s| ", "   ") +
                "Bar Name  " + String.format("%1$" + (maxLen - 10) + "s| ", "   ") + "Phone" + String.format("%1$" + (maxLen - 5) + "s| ", "   "));
        System.out.println("-----------------------------------------------------------------");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_id, bar_info.bar_name, bar_phone.phone\n" +
                    "FROM bar_phone\n" +
                    "INNER JOIN bar_info ON bar_info.bar_id = bar_phone.bar_id");

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
            System.out.println("Error: ");
            System.out.println(e);
        }

        System.out.println("-----------------------------------------------------------------");

    }

    // 5. Update records
    public static void update() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSelect ID of Bar you want to update: ");
        int bar_id = Integer.parseInt(sc.nextLine());

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Main.url,Main.user,Main.pass);

            System.out.println("\nEnter new bar phone number: ");
            String phone = sc.nextLine();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE bar_phone SET phone = ? WHERE bar_id in (?)");
            pstmt.setString(1, phone);
            pstmt.setInt(2, bar_id);
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("\nBar name has been updated.\n");

            conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
