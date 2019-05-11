package com.company;
/**
 * -- Bar_Info --
 * Display
 * Create
 * Update
 * Delete
 * Contact
 * Search
 * Export
 */


import java.sql.*;
import java.io.*;
import java.util.*;



public class Info {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static Connection con = null;
    public static void executeCommand(String command) {
        if (command.equals("display")) {
            display();
        }
        else if (command.equals("create")) {
            create();
        }
        else if (command.equals("update")) {
            update();
        }
        else if (command.equals("delete")) {
            delete();
        }
        else if (command.equals("contact")) {
            contact();
        }
        else if (command.equals("search")) {
            search();
        }
        else if (command.equals("export")) {
            export();
        }
        else if (command.equals("overview")) {
            overview();
        }
    }

    // 1. Print/Display records from your database/tables
    public static void display() {
        int maxLen = 25;

        // print headers
        System.out.println("\nBarID  " + String.format("%1$" + (maxLen - 7) + "s| ", "   ") + "BarName" + String.format("%1$" + (maxLen - 7) + "s| ", "   ") +
                "Address" + String.format("%1$" + (maxLen - 7) + "s| ", "   ") + "City   " + String.format("%1$" + (maxLen - 7) + "s| ", "   ") +
                "State  " + String.format("%1$" + (maxLen - 7) + "s| ", "   ") + "ZIP" + String.format("%1$" + (maxLen - 7) + "s| ", "   "));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            Statement stmt = con.createStatement();

            // 6. Make use of transactions (commit & rollback)
            ResultSet rs = stmt.executeQuery("SELECT *\n" +
                    "FROM bar_info;");

            while(rs.next())
                // print table
                System.out.println(rs.getString(1) + String.format("%1$"+ (maxLen - rs.getString(1).length()) + "s| ", "") +
                        rs.getString(2) + String.format("%1$"+ (maxLen - rs.getString(2).length()) + "s| ", "") +
            rs.getString(3) + String.format("%1$"+ (maxLen - rs.getString(3).length()) + "s| ", "") +
            rs.getString(4) + String.format("%1$"+ (maxLen - rs.getString(4).length()) + "s| ", "") +
            rs.getString(5) + String.format("%1$"+ (maxLen - rs.getString(5).length()) + "s| ", "") +
            rs.getString(6) + String.format("%1$"+ (maxLen - (rs.getString(6).length() + 4)) + "s| ", ""));
            con.close();

        }
        catch(Exception e)
                {
                System.out.println("Error: ");
                System.out.println(e);
                }

                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    // 3. Create a new record
    public static void create() {
        Scanner sc = new Scanner(System.in);

        // Ask user to input values
        System.out.println("\nInsert an entry by filling out the information: ");
        System.out.println("Bar Id: ");
        int bar_id = Integer.parseInt(sc.nextLine());
        System.out.println("\nBar Name: ");
        String bar_name = sc.nextLine();
        System.out.println("\nAddress: ");
        String bar_address = sc.nextLine();
        System.out.println("\nCity: ");
        String bar_city = sc.nextLine();
        System.out.println("\nState: ");
        String bar_state = sc.nextLine();
        System.out.println("\nZip: ");
        String bar_zip = sc.nextLine();


        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(Main.url,Main.user,Main.pass);

            // Set auto commit to false
            con.setAutoCommit(false);

            // Add inputted values to database
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO `bar_info`(bar_id,bar_name,address,city,state,zip) VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, bar_id);
            pstmt.setString(2, bar_name);
            pstmt.setString(3, bar_address);
            pstmt.setString(4, bar_city);
            pstmt.setString(5, bar_state);
            pstmt.setString(6, bar_zip);
            pstmt.executeUpdate();
            System.out.println("\nIt's been added to the archive.\n");

            // COMMIT
            con.commit();

            pstmt.close();
            con.close();


        }
        catch (SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
            // If there is an error then rollback the changes.
            System.out.println("Rolling back data here....");
            try
            {
                if(con!=null)
                    // ROLLBACK
                    con.rollback();
            }
            catch(SQLException se2)
            {
                se2.printStackTrace();
            }
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // 5. Update records
    public static void update() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSelect ID of Bar you want to update: ");
        int bar_id = Integer.parseInt(sc.nextLine());

        System.out.println("Select which attibute you would like to update: \n(1) Bar name \n(2) Address: ");
        int option = Integer.parseInt(sc.nextLine());

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Main.url,Main.user,Main.pass);

            switch (option) {
                case 1:
                    System.out.println("\nEnter new bar name: ");
                    String bar_name = sc.nextLine();
                    PreparedStatement pstmt = conn.prepareStatement("UPDATE bar_info SET bar_name = ? WHERE bar_id in (?)");
                    pstmt.setString(1, bar_name);
                    pstmt.setInt(2, bar_id);
                    pstmt.executeUpdate();
                    pstmt.close();
                    System.out.println("\nBar name has been updated.\n");
                    break;
                case 2:
                    System.out.println("\nEnter new bar address: ");
                    String bar_address = sc.nextLine();
                    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE bar_info SET address = ? WHERE bar_id in (?)");
                    pstmt2.setString(1, bar_address);
                    pstmt2.setInt(2, bar_id);
                    pstmt2.executeUpdate();
                    pstmt2.close();
                    System.out.println("\nBar address has been updated.\n");
                    break;
            }
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // 4. Delete records
    public static void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nChoose ID of the Bar you want to delete: ");
        int bar_id = Integer.parseInt(sc.nextLine());
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Main.url,Main.user,Main.pass);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM bar_info WHERE bar_id = ?");
            pstmt.setInt(1, bar_id);
            pstmt.executeUpdate();
            System.out.println("\nBar has been deleted.\n");
            pstmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // 10. First query involving two join across 3 tables
    public static void contact() {
        int maxLen = 25;

        // print headers
        System.out.println("\nBarName" + String.format("%1$" + (maxLen - 7) + "s| ", "   ") +
                "Phone  " + String.format("%1$" + (maxLen - 7) + "s| ", "   ") +
                "Email  " + String.format("%1$" + (maxLen - 7) + "s| ", ""));
        System.out.println("--------------------------------------------------------------------------------");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_name, bar_phone.phone, bar_email.email\n" +
                    "FROM bar_info\n" +
                    "INNER JOIN bar_phone ON bar_phone.bar_id = bar_info.bar_id\n" +
                    "INNER JOIN bar_email ON bar_email.bar_id = bar_info.bar_id;");

            while(rs.next())
                // print table
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

        System.out.println("--------------------------------------------------------------------------------");

    }

    // Search for bar names with either "pub" or "bar" in the name
    public static void search() {
        int maxLen = 25;
        Scanner sc = new Scanner(System.in);
        System.out.println("Search bars in (1) Orange or (2) Newport Beach:");
        int option = Integer.parseInt(sc.nextLine());




        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);

            switch (option) {
                case 1:
                    System.out.println("\nSearching for bars in Orange...");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT bar_name, address, city, state, zip\n" +
                                                        "FROM bar_info\n" +
                                                        "WHERE city = 'Orange';");


                    // print headers
                    System.out.println("\nBarName" + String.format("%1$" + (maxLen - 7) + "s| ", "   ") +
                            "Address" + String.format("%1$" + (maxLen - 7) + "s| ", "   ") + "City   " + String.format("%1$" + (maxLen - 7) + "s| ", "   ") +
                            "State  " + String.format("%1$" + (maxLen - 7) + "s| ", "   ") + "ZIP" + String.format("%1$" + (maxLen - 3) + "s| ", "   "));
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

                    while(rs.next())
                        // print table
                        System.out.println(rs.getString(1) + String.format("%1$"+ (maxLen - rs.getString(1).length()) + "s| ", "") +
                                rs.getString(2) + String.format("%1$"+ (maxLen - rs.getString(2).length()) + "s| ", "") +
                                rs.getString(3) + String.format("%1$"+ (maxLen - rs.getString(3).length()) + "s| ", "") +
                                rs.getString(4) + String.format("%1$"+ (maxLen - rs.getString(4).length()) + "s| ", "") +
                                rs.getString(5) + String.format("%1$"+ (maxLen - rs.getString(5).length()) + "s| ", ""));
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------\n");

                    rs.close();
                    stmt.close();
                    con.close();
                    break;

                case 2:
                    System.out.println("\nSearching for bars in Newport Beach...");
                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("SELECT bar_name, address, city, state, zip\n" +
                                                            "FROM bar_info\n" +
                                                            "WHERE city = 'Newport Beach';");


                    // print headers
                    System.out.println("\nBarName" + String.format("%1$" + (maxLen - 7) + "s| ", "   ") +
                            "Address" + String.format("%1$" + (maxLen - 7) + "s| ", "   ") + "City   " + String.format("%1$" + (maxLen - 7) + "s| ", "   ") +
                            "State  " + String.format("%1$" + (maxLen - 7) + "s| ", "   ") + "ZIP" + String.format("%1$" + (maxLen - 3) + "s| ", "   "));
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

                    while(rs2.next())
                        // print table
                        System.out.println(rs2.getString(1) + String.format("%1$"+ (maxLen - rs2.getString(1).length()) + "s| ", "") +
                                rs2.getString(2) + String.format("%1$"+ (maxLen - rs2.getString(2).length()) + "s| ", "") +
                                rs2.getString(3) + String.format("%1$"+ (maxLen - rs2.getString(3).length()) + "s| ", "") +
                                rs2.getString(4) + String.format("%1$"+ (maxLen - rs2.getString(4).length()) + "s| ", "") +
                                rs2.getString(5) + String.format("%1$"+ (maxLen - rs2.getString(5).length()) + "s| ", ""));
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------\n");

                    rs2.close();
                    stmt2.close();
                    con.close();
                    break;

            }



        }
        catch(Exception e)
        {
            System.out.println("Error: ");
            System.out.println(e);
        }


    }

    // Export main table to CSV file
    public static void export()
    {
        String filename ="bar_info.csv";
        try {
            FileWriter fw = new FileWriter(filename);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bar_info");

            while (rs.next()) {
                fw.append(Integer.toString(rs.getInt(1)));
                fw.append(',');
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append(',');
                fw.append(rs.getString(4));
                fw.append(',');
                fw.append(rs.getString(5));
                fw.append(',');
                fw.append(rs.getString(6));
                fw.append('\n');
            }

            fw.flush();
            fw.close();
            rs.close();
            stmt.close();
            con.close();
            System.out.println("CSV File was created successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 10. Second query involving two join across 3 tables
    public static void overview()
    {
        int maxLen = 25;

        // print headers
        System.out.println("\nBarName" + String.format("%1$" + (maxLen - 7) + "s| ", "   ") +
                "Overall Rating " + String.format("%1$" + (maxLen - 15) + "s| ", "   ") +
                "Beer On Tap " + String.format("%1$" + (maxLen - 12) + "s| ", ""));
        System.out.println("--------------------------------------------------------------------------------");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            Statement stmt = con.createStatement();

            // 10. Involve joins across at least 3 tables (bar_info, bar_phone, bar_email)
            ResultSet rs = stmt.executeQuery("SELECT bar_info.bar_name, " +
                    "bar_ratings.overall_rating, bar_features.has_beer_on_tap\n" +
                    "FROM bar_info\n" +
                    "INNER JOIN bar_ratings ON bar_ratings.bar_id = bar_info.bar_id\n" +
                    "INNER JOIN bar_features ON bar_features.bar_id = bar_info.bar_id\n" +
                    "ORDER BY bar_ratings.overall_rating DESC;");

            while(rs.next())
                // print table
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

        System.out.println("--------------------------------------------------------------------------------");
    }
}
