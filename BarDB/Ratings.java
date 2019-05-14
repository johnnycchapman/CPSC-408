package com.company;
/**
 * Created by chapm on 4/29/2019.
 * -- Bar Ratings --
 */

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Ratings {
    public static void executeCommand(String command) {
        if (command.equals("display")) {
            display();
        }
        else if (command.equals("filter")) {
            filter();
        }
        else if (command.equals("average")) {
            average();
        }
        else if (command.equals("export")) {
            export();
        }
    }

    public static void display() {
        int maxLen = 20;
        System.out.println("\n-------------------------------------------------------------------------------------------------------------");
        System.out.println("BarName" + String.format("%1$" + (maxLen - 7) + "s| ", "") + "Overall Rating" + String.format("%1$" + (maxLen - 14) +
                "s| ", "") + "Food Rating" + String.format("%1$" + (maxLen - 11) + "s| ", "") + "Beer Rating" + String.format("%1$" + 9 +
                "s| ", "") + "Cocktail Rating" + String.format("%1$" + 5 + "s| ", ""));
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_name,\n" +
                    "       bar_ratings.overall_rating,\n" +
                    "       bar_ratings.food_rating,\n" +
                    "       bar_ratings.beer_rating,\n" +
                    "       bar_ratings.cocktail_rating\n" +
                    "FROM bar_ratings\n" +
                    "INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id;");

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

    // 9. One query must contain a sub-query. (Sub-query in case 4)
    public static void filter() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWhich field would you like to filter by?\n");
        System.out.println("(1) Overall Rating (2) Food Rating (3) Beer Rating (4) Cocktail Rating");
        int option = Integer.parseInt(sc.nextLine());
        int maxLen = 20;

        switch (option) {
            // return bars with 3+ overall ratings
            case 1:

                System.out.println("Bar Name  " + String.format("%1$" + (maxLen - 10) + "s| ", "") +
                        "Overall Rating" + String.format("%1$" + (maxLen - 14) + "s| ", "   "));
                System.out.println("-------------------------------------------");

                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_name,\n" +
                            "bar_ratings.overall_rating\n" +
                            "FROM bar_ratings\n" +
                            "INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id\n" +
                            "WHERE overall_rating >= '3'\n" +
                            "ORDER BY overall_rating DESC LIMIT 5;");

                    while(rs.next())
                        System.out.println(rs.getString(1) + String.format("%1$"+ (maxLen - rs.getString(1).length()) + "s| ", "") +
                                rs.getString(2) + String.format("%1$"+ (maxLen - rs.getString(2).length()) + "s| ", ""));

                    rs.close();
                    stmt.close();
                    con.close();

                }
                catch(Exception e)
                {
                    System.out.println("Error: ");
                    System.out.println(e);
                }

                System.out.println("-------------------------------------------");
                break;

            // return bars with 3+ food ratings
            case 2:
                System.out.println("Bar Name  " + String.format("%1$" + (maxLen - 10) + "s| ", "") +
                        "Food Rating" + String.format("%1$" + (maxLen -11) + "s| ", "   "));
                System.out.println("-------------------------------------------");

                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_name,\n" +
                            "bar_ratings.food_rating\n" +
                            "FROM bar_ratings\n" +
                            "INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id\n" +
                            "WHERE food_rating >= '3'\n" +
                            "ORDER BY food_rating DESC LIMIT 5;");

                    while(rs.next())
                        System.out.println(rs.getString(1) + String.format("%1$"+ (maxLen - rs.getString(1).length()) + "s| ", "") +
                                rs.getString(2) + String.format("%1$"+ (maxLen - rs.getString(2).length()) + "s| ", ""));
                    con.close();

                }
                catch(Exception e)
                {
                    System.out.println("Error: ");
                    System.out.println(e);
                }

                System.out.println("-------------------------------------------");
                break;

            // return bars with 3+ beer ratings
            case 3:
                System.out.println("Bar Name  " + String.format("%1$" + (maxLen - 10) + "s| ", "") +
                        "Beer Rating" + String.format("%1$" + (maxLen -11) + "s| ", "   "));
                System.out.println("-------------------------------------------");

                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_name,\n" +
                            "bar_ratings.beer_rating\n" +
                            "FROM bar_ratings\n" +
                            "INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id\n" +
                            "WHERE beer_rating >= '3'\n" +
                            "ORDER BY beer_rating DESC LIMIT 5;");

                    while(rs.next())
                        System.out.println(rs.getString(1) + String.format("%1$"+ (maxLen - rs.getString(1).length()) + "s| ", "") +
                                rs.getString(2) + String.format("%1$"+ (maxLen - rs.getString(2).length()) + "s| ", ""));
                    con.close();

                }
                catch(Exception e)
                {
                    System.out.println("Error: ");
                    System.out.println(e);
                }

                System.out.println("-------------------------------------------");
                break;

            // return bars with 3+ cocktail ratings
            case 4:
                System.out.println("Bar Name  " + String.format("%1$" + (maxLen - 10) + "s| ", "") +
                        "Cocktail Rating" + String.format("%1$" + (maxLen - 15) + "s| ", "   "));
                System.out.println("-------------------------------------------");

                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
                    Statement stmt = con.createStatement();

                    // 9. One query must contain a sub-query.
                    ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_name,\n" +
                            "       bar_ratings.cocktail_rating\n" +
                            "FROM bar_ratings\n" +
                            "INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id\n" +
                            "WHERE bar_info.bar_name IN (SELECT bar_info.bar_name\n" +
                            "  FROM bar_info WHERE cocktail_rating >= '3')\n" +
                            "ORDER BY cocktail_rating DESC LIMIT 5;");

                    while(rs.next())
                        System.out.println(rs.getString(1) + String.format("%1$"+ (maxLen - rs.getString(1).length()) + "s| ", "") +
                                rs.getString(2) + String.format("%1$"+ (maxLen - rs.getString(2).length()) + "s| ", ""));
                    con.close();

                }
                catch(Exception e)
                {
                    System.out.println("Error: ");
                    System.out.println(e);
                }

                System.out.println("-------------------------------------------");
                break;
        }
    }

    // 8. One query must perform an aggregation/group-by clause
    public static void average()
    {
        int maxLen = 20;
        System.out.println("\nOverall Rating" + String.format("%1$" + (maxLen - 14) +
                "s| ", "") + "Food Rating" + String.format("%1$" + (maxLen - 11) +
                "s| ", "") + "Beer Rating" + String.format("%1$" + 9 +
                "s| ", "") + "Cocktail Rating" + String.format("%1$" + 5 + "s| ", ""));
        System.out.println("---------------------------------------------------------------------------------------");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            Statement stmt = con.createStatement();

            // 8. One query must perform an aggregate/group by function
            ResultSet rs = stmt.executeQuery("SELECT AVG(overall_rating) AS Overall,\n" +
                    "       AVG(food_rating) AS Food,\n" +
                    "       AVG(beer_rating) AS Beer,\n" +
                    "       AVG(cocktail_rating) AS Cocktail\n" +
                    "FROM bar_ratings;");

            while(rs.next())
                // print table
                System.out.println(rs.getString(1) + String.format("%1$"+ (maxLen - rs.getString(1).length()) + "s| ", "") +
                        rs.getString(2) + String.format("%1$"+ (maxLen - rs.getString(2).length()) + "s| ", "") +
                        rs.getString(3) + String.format("%1$"+ (maxLen - rs.getString(3).length()) + "s| ", "") +
                        rs.getString(4) + String.format("%1$"+ (maxLen - rs.getString(4).length()) + "s| ", ""));

            rs.close();
            stmt.close();
            con.close();

        }
        catch(Exception e)
        {
            System.out.println("Error: ");
            System.out.println(e);
        }

        System.out.println("---------------------------------------------------------------------------------------");

    }

    // 7. Generate reports that can be exported (excel or csv format)
    public static void export()
    {
        Scanner sc = new Scanner(System.in);


        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            System.out.println("\nWhich Top 5 report would you like to export?");
            System.out.println("(1) Overall Rating (2) Food Rating (3) Beer Rating (4) Cocktail Rating");
            int option = Integer.parseInt(sc.nextLine());


            switch (option) {
                // Top 5 Overall Ratings
                case 1:
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_name,\n" +
                            "bar_ratings.overall_rating\n" +
                            "FROM bar_ratings\n" +
                            "INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id\n" +
                            "WHERE overall_rating >= '3'\n" +
                            "ORDER BY overall_rating DESC LIMIT 5;");
                    String filename ="overall_rating.csv";
                    FileWriter fw = new FileWriter(filename);

                    while (rs.next()) {
                        fw.append(rs.getString(1));
                        fw.append(',');
                        fw.append(rs.getString(2));
                        fw.append('\n');
                    }

                    fw.flush();
                    fw.close();
                    rs.close();
                    stmt.close();
                    con.close();
                    System.out.println("CSV File was created successfully.");
                    break;

                // Top 5 Food Ratings
                case 2:
                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("SELECT bar_info.bar_name,\n" +
                            "bar_ratings.food_rating\n" +
                            "FROM bar_ratings\n" +
                            "INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id\n" +
                            "WHERE food_rating >= '3'\n" +
                            "ORDER BY food_rating DESC LIMIT 5;");
                    String filename2 ="food_rating.csv";
                    FileWriter fw2 = new FileWriter(filename2);

                    while (rs2.next()) {
                        fw2.append(rs2.getString(1));
                        fw2.append(',');
                        fw2.append(rs2.getString(2));
                        fw2.append('\n');
                    }

                    fw2.flush();
                    fw2.close();
                    rs2.close();
                    stmt2.close();
                    con.close();
                    System.out.println("CSV File is created successfully.");
                    break;

                // Top 5 Beer Ratings
                case 3:
                    Statement stmt3 = con.createStatement();
                    ResultSet rs3 = stmt3.executeQuery("SELECT bar_info.bar_name,\n" +
                            "bar_ratings.beer_rating\n" +
                            "FROM bar_ratings\n" +
                            "INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id\n" +
                            "WHERE beer_rating >= '3'\n" +
                            "ORDER BY beer_rating DESC LIMIT 5;");
                    String filename3 ="beer_rating.csv";
                    FileWriter fw3 = new FileWriter(filename3);

                    while (rs3.next()) {
                        fw3.append(rs3.getString(1));
                        fw3.append(',');
                        fw3.append(rs3.getString(2));
                        fw3.append('\n');
                    }

                    fw3.flush();
                    fw3.close();
                    rs3.close();
                    stmt3.close();
                    con.close();
                    System.out.println("CSV File was created successfully.");
                    break;

                // Top 5 Cocktail Ratings
                case 4:
                    Statement stmt4 = con.createStatement();
                    ResultSet rs4 = stmt4.executeQuery("SELECT bar_info.bar_name,\n" +
                            "bar_ratings.cocktail_rating\n" +
                            "FROM bar_ratings\n" +
                            "INNER JOIN bar_info ON bar_info.bar_id = bar_ratings.bar_id\n" +
                            "WHERE cocktail_rating >= '3'\n" +
                            "ORDER BY cocktail_rating DESC LIMIT 5;");
                    String filename4 ="cocktail_rating.csv";
                    FileWriter fw4 = new FileWriter(filename4);

                    while (rs4.next()) {
                        fw4.append(rs4.getString(1));
                        fw4.append(',');
                        fw4.append(rs4.getString(2));
                        fw4.append('\n');
                    }

                    fw4.flush();
                    fw4.close();
                    rs4.close();
                    stmt4.close();
                    con.close();
                    System.out.println("CSV File was created successfully.");
                    break;

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
