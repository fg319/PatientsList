package org.vaadin.example.doctors;


import java.sql.*;
import java.sql.ResultSet;

public class Doctors {
    private String password;
    private String DocUserName;
    private Boolean verification = false;

    public String getPassword() {
        return password;
    }

    public String getDocUserName() {
        return DocUserName;
    }
///Sets username and password for each doctor
    public void setDocUser(String DocUserName, String password) {
        this.DocUserName = DocUserName;
        this.password = password;
    }

    public Boolean getLoginVerification() {
        return verification;
    }


    //the method below checks that there is correct pairing of the Doctor's username and password
    public void LoginVerification() throws ClassNotFoundException, SQLException {
        try {
            //Extracting items from SQL doctors database
            String dbUrl = "jdbc:postgresql://ec2-54-73-68-39.eu-west-1.compute.amazonaws.com:5432/dctpppdsoogu5e";
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, "wtlubuspzbefzf", "6056c0cef2cfcbf15902982f17d7ba4a19158dd1087ecb110fce1aade0e0629b");
            Statement s = conn.createStatement();
            String query = "SELECT * FROM doctors;";
            ResultSet rs = s.executeQuery(query);
             //Checking that column elements correspond to desired password & username pair
            while (rs.next()) {
                if (rs.getString("username").equals(DocUserName) && (rs.getString("password").equals(password)))
                {
                    verification = true; //when verification is user should be able to login (see LoginView.java)
                }
            }
            rs.close();
            s.close();
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }
        }


}
