import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.sql.*;

public class CSVParser {

    public static void file_reader() {
        String csvFile = "src/test.csv";
        String line;
        String splitCSV = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {
            Connection con = DBConfig.getMySqlConnection();

            PreparedStatement csvBarEmailInsert = con.prepareStatement("INSERT INTO BarEmail(Bar_ID,Email)"
                    + "VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);

            PreparedStatement csvBarInfoInsert = con.prepareStatement("INSERT INTO BarInfo(Bar_ID,bar_name,Address,City,State,Zip) "
                    + "VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            PreparedStatement csvBarFeaturesInsert = con.prepareStatement("INSERT INTO BarInfo(Bar_ID,Has_DJ,Has_Food,Has_Outdoor_Area,Has_Beer_On_Tap) "
                    + "VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            PreparedStatement csvBarPhoneInsert = con.prepareStatement("INSERT INTO BarInfo(Bar_ID,Phone) "
                    + "VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);

            PreparedStatement csvBarRatingsInsert = con.prepareStatement("INSERT INTO BarInfo(Bar_ID,Overall_Rating,Food_Rating,Beer_Rating,Cocktail_Rating) "
                    + "VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            br.readLine();

            while((line = br.readLine()) != null) {
                String[] data = line.split(splitCSV);

                String Bar_ID = data[0];
                String bar_name = data[1];
                String Phone = data[2];
                String Email = data[3];
                String Address = data[4];
                String City = data[5];
                String State = data[5];
                String Zip = data[7];
                String Has_DJ = data[8];
                String Has_Food = data[9];
                String Has_Outdoor_Area = data[10];
                String Has_Beer_On_Tap = data[11];
                String Overall_Rating = data[12];
                String Food_Rating = data[13];
                String Beer_Rating = data[14];
                String Cocktail_Rating = data[15];

                ResultSet key_Bar = csvBarEmailInsert.getGeneratedKeys();


                int key_BarID = 0;
                if (key_Bar.next()) {
                    key_BarID = key_Bar.getInt(1);
                }

                // insert into BarEmail
                csvBarEmailInsert.setInt(key_BarID, Integer.parseInt(Bar_ID));
                csvBarEmailInsert.setString(2, Email);
                csvBarEmailInsert.executeUpdate();



                // insert into BarInfo
                csvBarInfoInsert.setInt(key_BarID, Integer.parseInt(Bar_ID));
                csvBarInfoInsert.setString(2, bar_name);
                csvBarInfoInsert.setString(3, Address);
                csvBarInfoInsert.setString(4, City);
                csvBarInfoInsert.setString(5, State);
                csvBarInfoInsert.setString(5, Zip);
                csvBarInfoInsert.executeUpdate();

                // insert into BarFeatures
                csvBarFeaturesInsert.setInt(key_BarID, Integer.parseInt(Bar_ID));
                csvBarFeaturesInsert.setString(2, Has_DJ);
                csvBarFeaturesInsert.setString(3, Has_Food);
                csvBarFeaturesInsert.setString(4, Has_Outdoor_Area);
                csvBarFeaturesInsert.setString(5, Has_Beer_On_Tap);
                csvBarFeaturesInsert.executeUpdate();

                // insert into BarPhone
                csvBarPhoneInsert.setInt(key_BarID, Integer.parseInt(Bar_ID));
                csvBarPhoneInsert.setString(2, Phone);
                csvBarPhoneInsert.executeUpdate();


                // insert into BarRatings
                csvBarRatingsInsert.setInt(key_BarID, Integer.parseInt(Bar_ID));
                csvBarRatingsInsert.setInt(2, Integer.parseInt(Overall_Rating));
                csvBarRatingsInsert.setInt(3, Integer.parseInt(Food_Rating));
                csvBarRatingsInsert.setInt(3, Integer.parseInt(Beer_Rating));
                csvBarRatingsInsert.setInt(3, Integer.parseInt(Cocktail_Rating));
                csvBarPhoneInsert.executeUpdate();
            }

        }


        catch (IOException ex) {
            System.out.println("Error @ insert level: " + ex );
        }

        catch (SQLException e) {
            System.out.println("Error @ insert level: " + e );
        }

    }
}
