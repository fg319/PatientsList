package org.vaadin.example.plots;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HR_data {

    public ResultSet getData(String query) throws Exception{
        String dbUrl = "jdbc:postgresql://ec2-54-73-68-39.eu-west-1.compute.amazonaws.com:5432/dctpppdsoogu5e";
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(dbUrl, "wtlubuspzbefzf", "6056c0cef2cfcbf15902982f17d7ba4a19158dd1087ecb110fce1aade0e0629b");
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);

    }
    public List<Integer> GetHR() throws Exception {
        ArrayList<Integer> HeartRate = new ArrayList();

        ResultSet rs = getData("Select * from hrlive");

        while(rs.next()) {
            HeartRate.add(rs.getInt("heartrate"));
        }
        return HeartRate;
    }

    public List<Time> getTime() throws Exception{
        ArrayList<Time> timerec = new ArrayList();

        ResultSet rs = getData("Select * from hrlive");

        while(rs.next()) {
            timerec.add(rs.getTime("timerec"));
        }
        return timerec;
    }

    public Integer LatestHR() throws Exception{
        ResultSet rs = getData("SELECT * FROM hrlive WHERE id=(SELECT max(id) FROM hrlive);");
        int HR = 0;
        while(rs.next()) {
            HR = rs.getInt("heartrate");
        }
        return HR;
    }

    public Time LatestTime() throws Exception{
        ResultSet rs = getData("SELECT * FROM hrlive WHERE id=(SELECT max(id) FROM hrlive);");
        Time ts = null;
        while (rs.next()) {
            ts = rs.getTime("timerec");
        }
        return ts;
    }
}
