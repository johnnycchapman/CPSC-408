import java.sql.*;

public class Main {

    public static void main(String[] args) {

        DBConfig db = new DBConfig();
        CSVParser file = new CSVParser();

        Connection con = DBConfig.getMySqlConnection();
        file.file_reader();

    }
}