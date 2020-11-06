package org.example;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Calendar;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    private static final String INSERT_NEW = "INSERT INTO dish  VALUES(?,?,?,?,?,?,?)";
    private static final String GET_ALL = "SELECT * FROM dish";
    private static final String DELETE = "DELETE FROM dish WHERE id=?";

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //String query = "select * from users";

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

              connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,1);
            preparedStatement.executeUpdate();




//            preparedStatement = connection.prepareStatement(INSERT_NEW);
//            preparedStatement.setInt(1,1);
//            preparedStatement.setString(2,"tarelka");
//            preparedStatement.setString(3, "v nee kladut edu");
//            preparedStatement.setFloat(4,0.2f);
//            preparedStatement.setBoolean(5,true);
//            preparedStatement.setDate(6, new Date(Calendar.getInstance().getTimeInMillis()));
//            preparedStatement.setBlob(7, new FileInputStream("hm.jpg"));
//
//            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(GET_ALL);

            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {
                int id = res.getInt("id");
                String title = res.getString("title");
                String desc = res.getString("description");
                float rating = res.getFloat("rating");
                boolean published = res.getBoolean("published");
                Date date = res.getDate("created");
                byte[] icon = res.getBytes("icon");

                System.out.println("id: "+id+", title: "+title+", description: "+desc+", rating: "+rating+
                        ", published: "+published+", date: "+date+", icon length: "+icon.length);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }


//        } catch (SQLException | FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
