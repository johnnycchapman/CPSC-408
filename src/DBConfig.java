import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {

    private Connection pgSqlConnection = null;

    public static Connection getMyConnection()
    {
        Connection mysqlConnection = null;
        try
        {
            //returns the Class object associated with the class or interface with the given
            Class.forName("com.mysql.jdbc.Driver");
            String connectionURL = "jdbc:mysql://35.233.129.12:3306/cpsc408";
            mysqlConnection = DriverManager.getConnection(connectionURL, "johnnychapman", "Turkeyboy1");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return mysqlConnection;
    }

    public static Connection getMySqlConnection()
    {
        Connection mysqlConnection = null;
        try
        {
            //returns the Class object associated with the class or interface with the given string name
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost:3306/Assign3";
            mysqlConnection = DriverManager.getConnection(connectionUrl, "root", "k0>.^@~hT~(.0cj");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return mysqlConnection;
    }
}